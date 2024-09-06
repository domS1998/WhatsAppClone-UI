package org.main.gui.mainwindow.v2.components.root.messagesbar.messagesview;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.main.fileio.cache.CacheUserstate;
import org.main.gui.mainwindow.v2.MainWindowController;
import org.main.gui.mainwindow.v2.components.root.Root;
import org.main.gui.mainwindow.v2.components.root.chatsbar.AbstractChatsBar;
import org.main.gui.mainwindow.v2.components.root.chatsbar.ChatsBar;
import org.main.gui.mainwindow.v2.components.root.chatsbar.chatsview.chatbox.ChatBox;
import org.main.gui.mainwindow.v2.components.root.messagesbar.AbstractMessagesBar;
import org.main.gui.mainwindow.v2.components.root.messagesbar.MessagesBar;
import org.main.gui.mainwindow.v2.components.root.messagesbar.messagesview.message.ReceivedMessagePane;
import org.main.gui.mainwindow.v2.components.root.messagesbar.messagesview.message.SendMessagePane;
import org.main.model.Chat;
import org.main.model.Message;
import org.main.model.User;
import org.main.net.api.transaction.UpdateMessageTransaction;
import org.main.net.api.transaction.messages.MessageType;
import org.main.net.api.transaction.messages.result.status.TransactionStatusMessage;

import java.io.IOException;


public class MessagesView extends StackPane {

    private Root root;
    private Chat chat;

    private VBox vbox = new VBox();
    ScrollPane scrollPane = new ScrollPane();

    public VBox getVbox() {return this.vbox;}
    public void setVbox(VBox hbox) {this.vbox = hbox;}
    public ScrollPane getScrollPane() {return this.scrollPane;}
    public void setScrollPane(ScrollPane scrollPane) {this.scrollPane = scrollPane;}

    public MessagesView(Root root, Chat chat) {

        this.root = root;
        this.chat = chat;

        this.setId("messagesView");
        this.getStyleClass().add("messagesView");

        this.setMinWidth(400);
        this.setMinHeight(485-2*60);
        this.setPrefWidth(1920-60-400);
        this.setPrefHeight(1080-2*60);
        this.setMaxWidth(1920-60-400);
        this.getChildren().addAll(scrollPane);

        // scrollpane
        this.scrollPane.setId("messagesView_scrollPane");
        this.getStyleClass().add("messagesView_scrollPane");
        this.scrollPane.setMinWidth(780-60-400);
        this.scrollPane.setMinHeight(10);
        this.scrollPane.setPrefWidth(1920-60-400);
        this.scrollPane.setPrefHeight(1080-2*60);
        this.scrollPane.setMaxWidth(1920-60-400);
        // kein horizontales Scrollen, Inhalt genauso breit wie ScrollPane
        this.scrollPane.setFitToWidth(true);
        this.scrollPane.setStyle("-fx-background-color: -fx-box-border, -fx-control-inner-background;-fx-background-insets: 0, 0; -fx-padding: 0;");
        this.scrollPane.setContent(vbox);

        // scroll geschwindigkeit anpassen mit scrollevent listener
        final double SPEED = 0.01;
        this.scrollPane.getContent().setOnScroll(scrollEvent -> {
            double deltaY = scrollEvent.getDeltaY() * SPEED;
            this.scrollPane.setVvalue(this.scrollPane.getVvalue() - deltaY);

        });

        // Hbox
        this.vbox.setId("messagesView_vbox");
        this.vbox.getStyleClass().add("messagesView_vbox");
        this.vbox.setMinWidth(400);
        this.vbox.setMinHeight(1080-2*60);
        this.vbox.setPrefWidth(1920-60-400);
        // keine Max Height für VBox im Scrollpane
        this.vbox.prefHeight(1080-2*60);

        initMessages(this.chat);
    }

    public void initMessages(Chat chat) {

        // Panes für alle Nachrichten aufbauen und in VBox einfügen
        synchronized (this) {
            for (Message message : chat.getMessages()) {
                System.out.println("## messages view ##");
                addMessage(message);
                ChatBox pane = ((ChatsBar)AbstractChatsBar.getChatsPane("chatsBar")).getChatsView().getChatBox(this.chat);

                // Wenn Send Msg
                if (message.getSender().equals(MainWindowController.getUser().getUsername())) {

                    pane.setSingleGreyTick();
                    if (message.isReceived()) { pane.setDoubleGreyTick(); }
                    if (message.isRead())     { pane.setTicked();         }
                }
            }
        }
    }

    // Message Pane zu VBox
    public void addMessage(Message message) {

        /// Gesendete Nachrichtenfelder
        if (message.getSender().equals(MainWindowController.getUser().getUsername())) {
            this.vbox.getChildren().add(new SendMessagePane(this.root, message));
            ChatBox pane = ((ChatsBar)AbstractChatsBar.getChatsPane("chatsBar")).getChatsView().getChatBox(this.chat);
            pane.setSendMsg(message);
        }

        /// Empfangene Nachrichtenfelder
        else {

            User tmp = new User();
            tmp.setUsername(message.getReceiver());
            tmp.setTel("+49 000 00000000");
            this.vbox.getChildren().add(new ReceivedMessagePane(this.root, message,tmp));

                // noch nicht synchonisierte Nachrichten als gelesen markieren
                // update an server schicken
                if ( ! message.isRead() ) {
                    message.setReceived(true);
//                    message.setRead(true);
                }

                if ( ! message.isSynchronized()) {

                    Thread thread = new Thread(()->{

                        message.setRead(true);

                        UpdateMessageTransaction update = new UpdateMessageTransaction(MainWindowController.getUser().getUsername(), MainWindowController.getUser().getPassword(), message);
                        update.start();
                        TransactionStatusMessage response = (TransactionStatusMessage) update.getResult();

                        if (response.getMESSAGE_TYPE() == MessageType.TRANSACTION_COMPLETE) {

                            message.setSynchronized(true);

                            // Cache aktualisieren
                            try {
                                CacheUserstate.getInstance().update(MainWindowController.getUser());
                            } catch (IOException | ClassNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    });

                    // Chat mit neuer Nachricht nach oben verschieben
                    ((ChatsBar) AbstractChatsBar.getChatsPane("chatsBar")).bubbleUp(this.chat);

                    ChatBox pane = ((ChatsBar)AbstractChatsBar.getChatsPane("chatsBar")).getChatsView().getChatBox(this.chat);
                    pane.setRcvMsg(message);


                    // als gelesen markieren, wenn aktuell angezeigter Chat
                    if (this.chat == MainWindowController.getCurChat()) {
                        thread.start();
                    }
                    else {
                        // wenn chat nicht aktuell angezeigt, Thread nicht starten und
                        //  in MessagesBar für den Chat zwischenspeichern
                        ((MessagesBar)AbstractMessagesBar.getMessagesPane(chat.getOtherUser(MainWindowController.getUser().getUsername()))).getPendingTransactionThreads().add(thread);

                        // ungelesene Nachrichten in chatpane markieren
                        pane = ((ChatsBar)AbstractChatsBar.getChatsPane("chatsBar")).getChatsView().getChatBox(this.chat);
                        pane.setUnreadMessages(message);
                    }
            }
        }

        // scrollpane runter zur neuen Nachricht scrollen
        scrollDown();
    }

    void scrollDown(){
        this.scrollPane.applyCss();
        this.scrollPane.layout();
        this.scrollPane.setVvalue(1.0);
    }



    public SendMessagePane getLastSendMsgPane(){
        for (int i = this.getVbox().getChildren().size() - 1; i >= 0; i--) {
            if (this.getVbox().getChildren().get(i) instanceof SendMessagePane) {
                return (SendMessagePane) this.getVbox().getChildren().get(i);
            }
        }
        return null;
    }

    public SendMessagePane getSentMsgPane(String id){
        for (int i = this.getVbox().getChildren().size() - 1; i >= 0; i--) {
            if (this.getVbox().getChildren().get(i) instanceof SendMessagePane) {
                if (((SendMessagePane) this.getVbox().getChildren().get(i)).getMessage().getId().equals(id)) {
                    return (SendMessagePane) this.getVbox().getChildren().get(i);
                }
            }
        }
        return null;
    }


}
