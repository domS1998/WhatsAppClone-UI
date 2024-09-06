package org.main.gui.mainwindow.v2.components.root.chatsbar.chatsview;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.main.gui.mainwindow.v2.MainWindowController;
import org.main.gui.mainwindow.v2.components.root.Root;
import org.main.gui.mainwindow.v2.components.root.chatsbar.chatsview.chatbox.ChatBox;
import org.main.model.Chat;


public class ChatsView extends StackPane {

    private Root root;
    private ScrollPane scrollPane = new ScrollPane();
    private VBox vBox = new VBox();
    private ChatBox curChatBox;

    public VBox getVbox () { return vBox; }

    public void setVbox (VBox vBox) {
        this.getChildren().clear();
        this.vBox = vBox;
        this.getChildren().add(vBox);
    }
    public ScrollPane getScrollPane () { return scrollPane; }
    public void setScrollPane (ScrollPane scrollPane) { this.scrollPane = scrollPane; }
    public ChatBox getCurChatBox () { return curChatBox; }
    public void setCurChatBox (ChatBox box) { curChatBox = box; }

    public ChatsView(Root root) {

        this.root = root;
        this.scrollPane.setContent(vBox);
        this.scrollPane.setFitToWidth(true);
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPane.setStyle("-fx-background-color: -fx-box-border, -fx-control-inner-background;-fx-background-insets: 0, 0; -fx-padding: 0;");

        // scroll geschwindigkeit anpassen mit scrollevent listener
        final double SPEED = 0.01;
        this.scrollPane.getContent().setOnScroll(scrollEvent -> {
            double deltaY = scrollEvent.getDeltaY() * SPEED;
            this.scrollPane.setVvalue(this.scrollPane.getVvalue() - deltaY);
        });
        this.getChildren().add(scrollPane);

        if (MainWindowController.getUser().getChats().isEmpty()) {
            this.scrollPane.setMinHeight(0);
            this.scrollPane.setPrefHeight(0);
        }
        initChats();
    }

    void initChats () {
        for (Chat chat :MainWindowController.getUser().getChats()) {
            addChat(chat);
        }
    }

    public void addChat (Chat chat) {
        this.vBox.getChildren().add(new ChatBox(this.root, chat, MainWindowController.getUser()));

        this.scrollPane.setMinHeight(USE_COMPUTED_SIZE);
        this.scrollPane.setPrefHeight(USE_COMPUTED_SIZE);
    }

    public ChatBox getChatBox (Chat chat) {

        System.out.println("getting chat box ");

        for (var pane : this.vBox.getChildren()) {
            if (    (((ChatBox) pane).getChat().getUser1().equals(chat.getUser1()) && ((ChatBox) pane).getChat().getUser2().equals(chat.getUser2()))
                 || (((ChatBox) pane).getChat().getUser2().equals(chat.getUser1()) && ((ChatBox) pane).getChat().getUser1().equals(chat.getUser2()))
               ) {



                System.out.println("chat box found");
                return (ChatBox) pane;
            }
        }
        System.out.println("chat box not found");
        return null;
    }


}
