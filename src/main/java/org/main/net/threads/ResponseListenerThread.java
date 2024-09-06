package org.main.net.threads;

import javafx.application.Platform;
import org.aspectj.bridge.IMessage;
import org.json.JSONObject;
import org.main.fileio.cache.CacheUserstate;
import org.main.gui.mainwindow.v2.MainWindowController;
import org.main.gui.mainwindow.v2.components.root.chatsbar.AbstractChatsBar;
import org.main.gui.mainwindow.v2.components.root.chatsbar.ChatsBar;
import org.main.gui.mainwindow.v2.components.root.messagesbar.AbstractMessagesBar;
import org.main.gui.mainwindow.v2.components.root.messagesbar.MessagesBar;
import org.main.gui.mainwindow.v2.components.root.messagesbar.messagesview.message.SendMessagePane;
import org.main.model.Message;
import org.main.net.api.NoSuchApiMessageException;
import org.main.net.api.transaction.ConnectListenerTransaction;
import org.main.net.api.transaction.messages.init.UpdateMsgMessage;
import org.main.net.api.transaction.messages.result.NewMessageServerResponse;
import org.main.net.api.transaction.messages.result.status.TransactionCompleteMessage;
import org.main.net.api.transaction.messages.result.status.TransactionStatusMessage;
import org.main.sound.SoundPlayer;
import java.io.IOException;
import java.text.ParseException;

// Thread, um erhaltene Nachrichten von dem Server, die nicht zu
//  einer Transaktion zu gehören, zu bearbeiten
public class ResponseListenerThread extends NetworkThread {

    // Konstruktor
    public ResponseListenerThread(String username, String password, MainWindowController controller) {
        super(username, password);
    }

    @Override
    public void run() {

        System.out.println("Thread " + Thread.currentThread().getId() + " starting ResponseListenerThread ...");

        this.connect();

        if ( ! isConnected() ) {
            System.out.println("ResponseListenerThread: terminating");
            return;
        }
        System.out.println("ResponseListenerThread: listening to server " + this.getClient().getSocket().getRemoteSocketAddress());


        // Bei Server als ListenerThread für Benutzer anmelden
        ConnectListenerTransaction transaction = new ConnectListenerTransaction(this.clientSocket, this.username, this.password);
        transaction.start();
        TransactionStatusMessage response = transaction.getResult();

        System.out.println("ResponseListenerThread: message received from server: \n" + response);


        // Endlosschleife für eingehende Transaktionen zu bearbeiten
        while (true) {
            try {
                // versuchen, API Nachrichten von Server aus Socket zu lesen
                System.out.println("ResponseListenerThread: waiting for new messages to arrive in socket buffer ...");

                if ( ! this.clientSocket.getSocket().isConnected() ){
                    System.out.println("ResponseListenerThread: connection with server lost, terminating");
                    return;
                }

                JSONObject received = new JSONObject(this.getClient().read());


                System.out.println("ResponseListenerThread: message received from server: \n" + received);
                String type = received.getString("MESSAGE_TYPE");
                switch (type) {

                    case "UPDATE_MESSAGE" -> {

                        UpdateMsgMessage apiMsg = new UpdateMsgMessage(received);
                        Message msg = apiMsg.getMessage();

                        // in Message Liste des passende Chats Änderung eintragen
                        Message oldMsg = MainWindowController.getUser().getChat(msg.getOtherUser(MainWindowController.getUser().getUsername())).getMessage(msg.getId());
                        oldMsg.replace(msg);

                        // in bereits geladenen Panes Zustände ändern
                        try {
                            SendMessagePane pane = ((MessagesBar) AbstractMessagesBar.getMessagesPane(msg.getOtherUser(MainWindowController.getUser().getUsername()))).getMessagesView().getSentMsgPane(msg.getId());
                            pane.update(msg);
                        }
                        catch (NullPointerException e){}

                        // Rückanwort senden: Nachricht erhalten
                        this.clientSocket.send(new TransactionCompleteMessage(apiMsg.getTransactionID(), this.username).toString());

                        CacheUserstate.getInstance().update(MainWindowController.getUser());
                    }

                    case "NEW_MESSAGE_FROM_SERVER" -> {

                        SoundPlayer.getInstance().play("receive");

                        NewMessageServerResponse apiMsg = new NewMessageServerResponse(received);

                        Message msg = apiMsg.getMessage();
                        msg.setReceived(true);
                        String username = MainWindowController.getUser().getUsername();
                        String sender = msg.getSender();

                        // Nachricht zu Liste in passendem Chat
                        for (var chat : MainWindowController.getUser().getChats()){
                            if ((chat.getUser1().equals(username) && chat.getUser2().equals(sender)) ||
                                    (chat.getUser1().equals(sender) && chat.getUser2().equals(username))  ) {

                                chat.addMessage(msg);

                                // Wenn MessagesView bereits geladen bzw. vorher angesehen
                                MessagesBar bar = (MessagesBar) AbstractMessagesBar.getMessagesPane(msg.getSender());
                                if (bar != null) {
                                    Platform.runLater(()->{
                                        synchronized (bar.getMessagesView()) {
                                            bar.getMessagesView().addMessage(msg);
                                        }
                                    });
                                }
                            }
                        }

                        // Rückanwort senden: Nachricht erhalten
                        this.clientSocket.send(new TransactionCompleteMessage(apiMsg.getTransactionID(), this.username).toString());

                        // Cache aktualisieren
                        CacheUserstate.getInstance().update(MainWindowController.getUser());

                    }
                    case "IS_CONNECTED_REQUEST" -> {

                        this.clientSocket.send("ok");
                    }
                    default -> {throw new NoSuchApiMessageException(type);}
                }
            }
            catch (IOException e) {
                System.out.println("ResponseListenerThread: connection lost with server socket. Terminating");
                MainWindowController.setLoggedIn(false);
//                this.controller.setConnectedDot(false);
                SoundPlayer.getInstance().play("no-connection");
                this.connected = false;
                return;
            }
            catch (NoSuchApiMessageException|ClassNotFoundException|ParseException e) {
                System.out.println("ResponseListenerThread: invalid api response message received: \n" + e.getMessage());
            }
        }
    }





}
