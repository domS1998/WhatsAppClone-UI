package org.main.gui.mainwindow.v2.components.root;

import javafx.scene.layout.HBox;
import org.main.gui.mainwindow.v2.MainWindowController;
import org.main.gui.mainwindow.v2.components.root.chatsbar.AbstractChatsBar;
import org.main.gui.mainwindow.v2.components.root.chatsbar.ChatsBar;
import org.main.gui.mainwindow.v2.components.root.chatsbar.chatsview.chatbox.ChatBox;
import org.main.gui.mainwindow.v2.components.root.chatsbar.newchatbox.NewChatBox;
import org.main.gui.mainwindow.v2.components.root.menubar.MenuBar;
import org.main.gui.mainwindow.v2.components.root.messagesbar.AbstractMessagesBar;
import org.main.gui.mainwindow.v2.components.root.messagesbar.MessagesBar;
import org.main.gui.mainwindow.v2.components.root.messagesbar.StartBGBar;
import org.main.model.Chat;
import org.main.model.User;

public class Root extends HBox {

    // Tabelle für alle Rootelemente (Login, Main) zum vorinitialisieren und wechseln
//    Hashtable<String, AbstractRoot> roots = new Hashtable<>();

    private MainWindowController controller;

    // UI Komponenten
    private MenuBar menuBar;
    private AbstractChatsBar chatsBar;
    private AbstractMessagesBar messagesBar;

    public MenuBar getMenuBar() {return this.menuBar;}
    public AbstractChatsBar getChatsBar() {return this.chatsBar;}
    public AbstractMessagesBar getMessagesBar() {return this.messagesBar;}
    public void setMenuBar(MenuBar menuBar) {this.menuBar = menuBar;}
    public void setChatsBar(ChatsBar chatsBar) {this.chatsBar = chatsBar;}
    public void setMessagesBar(MessagesBar messagesBar) {
        this.messagesBar = messagesBar;
        this.getChildren().set(2, messagesBar);
    }

    public Root(User user, Chat curChat) {

//        // Staten mit Menüleiste
        this.menuBar = new MenuBar(this, user);
//        // Starten mit Chatliste
        this.chatsBar = new ChatsBar(this);
//        // andere Boxen vorinitialisieren und implizit in Tabelle einhägne
        this.messagesBar = new StartBGBar(this);




        this.setId("root"); // schon automatisch gesetzt bei Initialisierung in MainWindow
        this.getStyleClass().add("root");
        this.getChildren().addAll(this.menuBar, this.chatsBar, this.messagesBar);

    }

    public void displayNewChatBox(){
        // kein Effekt, wenn breits gesetzt
        if (this.chatsBar instanceof NewChatBox) { return; }
        // wechseln zur Anzeige der Chatliste
        if (AbstractChatsBar.getChatsPane("newChatBox") == null) {
            System.out.println("creating new chatsbar pane 'newChatBox'");
            this.getChildren().set(1,new NewChatBox(this));
        }
        else {
            // altes, breits erstelltes pane aus tabelle wieder einhängen
            System.out.println("using existing chatsbar pane 'newChatBox'");
            this.getChildren().set(1, AbstractChatsBar.getChatsPane("newChatBox"));
        }
        // als aktuelles attribut setzen
        this.chatsBar = AbstractChatsBar.getChatsPane("newChatBox");

    }

    public void displayChatList(){
        // kein Effekt, wenn breits gesetzt
        if (this.chatsBar instanceof ChatsBar) { return; }
        // wechseln zur Anzeige der Chatliste
        if (AbstractChatsBar.getChatsPane("chatsBar") == null) {
            System.out.println("creating new chatsbar pane 'chatsBar'");
            this.getChildren().set(1,new ChatsBar(this));
        }
        else {
            // altes, breits erstelltes pane aus tabelle wieder einhängen
            System.out.println("using existing chatsbar pane 'chatsPane'");
            this.getChildren().set(1, AbstractChatsBar.getChatsPane("chatsBar"));
        }
        // als aktuelles attribut setzen
        this.chatsBar = AbstractChatsBar.getChatsPane("chatsBar");
    }

    public void displayMessagesBarStartBG(){
        // kein Effekt, wenn breits gesetzt
        if (this.messagesBar instanceof StartBGBar) { return; }
        // wechseln zur Anzeige der Chatliste
        if (AbstractChatsBar.getChatsPane("startBgBar") == null) {
            // Neue erstellen, wenn noch nicht vorher erstellt
            System.out.println("creating new messagesbar pane 'startBgBar'");
            this.getChildren().set(2,new StartBGBar(this));
        }
        else {
            // altes, breits erstelltes pane aus tabelle wieder einhängen
            System.out.println("using existing messagesbar pane 'startBgBar'");
            this.getChildren().set(2, AbstractMessagesBar.getMessagesPane("startBgBar"));
        }
        // als aktuelles attribut setzen
        this.messagesBar = AbstractMessagesBar.getMessagesPane("startBgBar");
    }

    public void displayMessagesBar(Chat chat){

        System.out.println("displaying messages bar");

        // kein Effekt, wenn breits gesetzt
        if (this.messagesBar.equals(AbstractMessagesBar.getMessagesPane(chat.getOtherUser(MainWindowController.getUser().getUsername())))) { return; }

        System.out.println("switching messages bar");

        // wechseln zur Anzeige der Chatliste
        this.messagesBar = AbstractMessagesBar.getMessagesPane(chat.getOtherUser(MainWindowController.getUser().getUsername()));
        // Messages Pane neu erzeugen und einhängen, wenn noch nicht in tabelle
        if (AbstractMessagesBar.getMessagesPane(chat.getOtherUser(MainWindowController.getUser().getUsername())) == null) {
            System.out.println("creating new messagesbar pane '"+chat.getOtherUser(MainWindowController.getUser().getUsername())+"'");
            this.getChildren().set(2, new MessagesBar(this, MainWindowController.getUser(), chat));
        }
        else {
            // altes, breits erstelltes pane aus tabelle wieder einhängen
            System.out.println("using existing messagesbar pane '"+chat.getOtherUser(MainWindowController.getUser().getUsername())+"'");
            this.getChildren().set(2, AbstractMessagesBar.getMessagesPane(chat.getOtherUser(MainWindowController.getUser().getUsername())));
        }
        // als aktuelles attribut setzen
        this.messagesBar = AbstractMessagesBar.getMessagesPane(chat.getOtherUser(MainWindowController.getUser().getUsername()));


        // Gespeicherte Transaktions-Threads starten
        ((MessagesBar)this.messagesBar).performPendingTransactions();

        // ungelesene Nachrichten in Chatpane zurücksetzen
        ChatBox pane = ((ChatsBar)AbstractChatsBar.getChatsPane("chatsBar")).getChatsView().getChatBox(chat);

        System.out.println("chatpane with chat: " + pane.getChat());

        System.out.println("resetting label for unread messages");
        pane.setRead();

    }

    public void initAllMessagePane(){
        for (var chat : MainWindowController.getUser().getChats()){
            new MessagesBar(this, MainWindowController.getUser(), chat);
        }
    }



}
