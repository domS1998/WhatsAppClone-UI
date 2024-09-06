package org.main.gui.mainwindow.v2.components.root.messagesbar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.main.gui.mainwindow.v2.MainWindowController;
import org.main.gui.mainwindow.v2.components.root.Root;
import org.main.gui.mainwindow.v2.components.root.messagesbar.bottombar.BottomBar;
import org.main.gui.mainwindow.v2.components.root.messagesbar.messagesview.MessagesView;
import org.main.gui.mainwindow.v2.components.root.messagesbar.topbar.TopBar;
import org.main.model.Chat;
import org.main.model.User;
import org.main.net.api.transaction.Transaction;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class MessagesBar extends AbstractMessagesBar {

    private User user;
    private Root root;
    Chat chat;

    private TopBar topBar;
    private MessagesView messagesView;
    private BottomBar bottomBar;

    // Warteschlange für updates, die erst bei erneuter Anzeige des Chats ausgeführt werden sollen
    private Queue<Thread> pendingTransactionThreads = new ConcurrentLinkedQueue<>();

    public TopBar getTopBar() {return this.topBar;}
    public MessagesView getMessagesView() {return this.messagesView;}
    public BottomBar getBottomBar() {return this.bottomBar;}
    public void setTopBar (TopBar topBar) {this.topBar = topBar;}
    public void setMessagesView (MessagesView messagesView) {this.messagesView = messagesView;}
    public void setBottomBar (BottomBar bottomBar) {this.bottomBar = bottomBar;}

    public Queue<Thread> getPendingTransactionThreads() {return this.pendingTransactionThreads;}


    // gebufferte Update Transactionen ausführen
    public void performPendingTransactions() {
        Thread thread = new Thread(()->{
        for (var transactionThread : this.pendingTransactionThreads){
                pendingTransactionThreads.poll().start();
                try {
                    transactionThread.join();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
        }
        });
        thread.start();
    }

    public MessagesBar (Root root, User user, Chat chat) {

        super(root, chat.getOtherUser(user.getUsername()));

        this.root = root;
        this.user = user;
        this.chat = chat;

        this.topBar = new TopBar(root, chat.getOtherUser(user.getUsername()));
        this.bottomBar = new BottomBar(root);

        this.messagesView = new MessagesView(root, chat);

        this.setId("messagesBar");
        this.getStyleClass().add("messagesBar");
        this.getChildren().addAll(topBar, messagesView, bottomBar);
    }
}
