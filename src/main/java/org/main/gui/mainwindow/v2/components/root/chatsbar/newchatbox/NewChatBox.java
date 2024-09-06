package org.main.gui.mainwindow.v2.components.root.chatsbar.newchatbox;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import org.json.JSONObject;
import org.main.exceptions.ChatWithUserException;
import org.main.fileio.cache.CacheUserstate;
import org.main.gui.mainwindow.v2.MainWindowController;
import org.main.gui.mainwindow.v2.components.root.Root;
import org.main.gui.mainwindow.v2.components.root.chatsbar.AbstractChatsBar;
import org.main.gui.mainwindow.v2.components.root.chatsbar.ChatsBar;
import org.main.gui.mainwindow.v2.components.root.chatsbar.chatsview.chatbox.ChatBox;
import org.main.model.Chat;
import org.main.net.api.transaction.CreateChatTransaction;
import org.main.net.api.transaction.messages.MessageType;
import org.main.net.api.transaction.messages.result.status.TransactionFailedMessage;
import org.main.net.api.transaction.messages.result.status.TransactionStatusMessage;
import org.main.sound.SoundPlayer;

import java.io.IOException;

public class NewChatBox extends AbstractChatsBar {


    private Root root;
    private HBox row1;
    private HBox row2;
    private HBox row3;


    public NewChatBox (Root root) {

        super(root,"newChatBox");

        this.root = root;

        this.setStyle("-fx-background-color: #ffffff");

        // Box für Zeile 1 mit Pfeilbutton und Text
        this.row1 = new HBox();
        this.getChildren().add(this.row1);

        StackPane arrowPane = new StackPane();
//                            arrowPane.setStyle("-fx-background-color: purple;");
        this.row1.getChildren().add(arrowPane);
        ImageView arrow = new ImageView(new Image("pics/arrow-icon.png"));
        arrowPane.getChildren().add(arrow);
        arrowPane.setMinHeight(50);
        arrowPane.setMinWidth(40);
        arrowPane.setPrefHeight(50);
        arrowPane.setPrefWidth(50);
        arrowPane.setMinHeight(50);
        arrowPane.setMinWidth(50);
        arrow.setFitHeight(20);
        arrow.setPreserveRatio(true);
        arrow.setRotate(180.0);

        // senden button scalen
        arrowPane.setOnMouseEntered((e) -> {
            // scale up on hover
            arrowPane.setScaleX(arrowPane.getScaleX() * 1.15);
            arrowPane.setScaleY(arrow.getScaleY() * 1.15);
        });

        arrowPane.setOnMouseExited((e) -> {
            // scale up on hover
            arrowPane.setScaleX(arrowPane.getScaleX() / 1.15);
            arrowPane.setScaleY(arrow.getScaleY() / 1.15);
        });

        // Zurück zur Chatliste
        arrowPane.setOnMouseClicked((e) -> {
            this.removeErrorMessage();
            this.root.displayChatList();
        });


        this.row1.setMaxHeight(USE_COMPUTED_SIZE);
        this.row1.setMaxWidth(USE_COMPUTED_SIZE);
        this.row1.setPrefHeight(USE_COMPUTED_SIZE);
        this.row1.setPrefWidth(USE_COMPUTED_SIZE);
        this.row1.setMinHeight(USE_COMPUTED_SIZE);
        this.row1.setMinWidth(USE_COMPUTED_SIZE);
        Label newChatLabel = new Label();
        HBox.setMargin(newChatLabel, new Insets(10, 10, 10, 10));
        this.row1.getChildren().add(newChatLabel);
        newChatLabel.setText("New chat");
        newChatLabel.setFont(new Font("System", 22));

        // Box für Zeile 2 mit Textfeld und Pfeilbutton
        this.row2 = new HBox();
        this.getChildren().add(this.row2);

        StackPane textPane = new StackPane();
        this.row2.getChildren().add(textPane);
        textPane.setPrefHeight(USE_COMPUTED_SIZE);
        textPane.setPrefWidth(USE_COMPUTED_SIZE);
        textPane.setMaxHeight(USE_COMPUTED_SIZE);
        textPane.setMaxWidth(USE_COMPUTED_SIZE);
        textPane.setMinHeight(USE_COMPUTED_SIZE);
        textPane.setMinWidth(USE_COMPUTED_SIZE);

        TextField newChatTextField = new TextField();
        textPane.getChildren().add(newChatTextField);
        StackPane.setMargin(newChatTextField, new Insets(10, 10, 10, 10));

        // Rand wegmachen und direkt in angeklickten Zustand, damit Text weggeht
        newChatTextField.setStyle("-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ; -fx-background-color: #f0f2f5;");
        newChatTextField.setFocusTraversable(false);
        newChatTextField.setPromptText("enter username or phone number");
        newChatTextField.getParent().requestFocus();
        newChatTextField.setMinWidth(330);

        StackPane arrowPane2 = new StackPane();
        this.row2.getChildren().add(arrowPane2);
        ImageView arrow2 = new ImageView(new Image("pics/arrow-icon.png"));
        arrowPane2.getChildren().add(arrow2);
        arrowPane2.setMinHeight(50);
        arrowPane2.setMinWidth(40);
        arrowPane2.setPrefHeight(50);
        arrowPane2.setPrefWidth(50);
        arrowPane2.setMinHeight(50);
        arrowPane2.setMinWidth(50);
        arrow2.setFitHeight(20);
        arrow2.setPreserveRatio(true);

        // senden button scalen
        arrowPane2.setOnMouseEntered((e) -> {
             // scale up on hover
            arrowPane2.setScaleX(arrowPane2.getScaleX() * 1.15);
            arrowPane2.setScaleY(arrow2.getScaleY() * 1.15);
        });

        arrowPane2.setOnMouseExited((e) -> {
            // scale up on hover
            arrowPane2.setScaleX(arrowPane2.getScaleX() / 1.15);
            arrowPane2.setScaleY(arrow2.getScaleY() / 1.15);
        });

        arrowPane2.setOnMouseClicked((e) -> {

            // Abbruch, wenn keine Verbindung zu Server
            if ( ! MainWindowController.isLoggedIn() ){
                System.out.println("No Connection to Server");
                SoundPlayer.getInstance().play("no-connection");
                return;
            }

            // neuer Chat mit eingegebenem User
            Chat chat = null;

            try { chat = new Chat(MainWindowController.getUser().getUsername(), newChatTextField.getText());}
            catch (ChatWithUserException ex) {System.out.println(ex.getMessage());}

            CreateChatTransaction createChat = new CreateChatTransaction(MainWindowController.getUser().getUsername(), MainWindowController.getUser().getPassword(),chat);
            createChat.start();
            TransactionStatusMessage response = createChat.getResult();

            JSONObject json = response.toJson();
            System.out.println(json.get("MESSAGE_TYPE").equals("TRANSACTION_COMPLETE"));
            System.out.println("MainWindowController: message type: " + response.getMESSAGE_TYPE());

            if (response.getMESSAGE_TYPE() == MessageType.TRANSACTION_COMPLETE || (response.getMESSAGE_TYPE() == MessageType.TRANSACTION_FAIL && ((TransactionFailedMessage)response).getError().equals("chat does already exist"))) {
                // neuen Chat hinzufügen
                // chat zu user
                if (response.getMESSAGE_TYPE() == MessageType.TRANSACTION_COMPLETE) {
                    MainWindowController.getUser().getChats().add(chat);
                    // chat zu chat pane hinzufügen
                    ((ChatsBar) AbstractChatsBar.getChatsBarPanes().get("chatsBar")).getChatsView().addChat(chat);
                }
                // alten Chat weiß färben
                if (MainWindowController.getCurChat() != null) {
                    ((ChatsBar) AbstractChatsBar.getChatsBarPanes().get("chatsBar")).getChatsView().getChatBox(MainWindowController.getCurChat()).setStyle("fx-background-color: #ffffff;");
                }
                // neues ChatPane holen und aktuellen chat einfärben
                ChatBox pane = ((ChatsBar)AbstractChatsBar.getChatsBarPanes().get("chatsBar")).getChatsView().getChatBox(chat);
                pane.setStyle("-fx-background-color: #f5f7fa;");
                // aktuellen Chat auf Angeklickten aktualisieren
                MainWindowController.setCurChat(chat);
                // neuer Nachrichtenbereich anzeigen
                this.root.displayMessagesBar(chat);
                // cache updaten
                try { CacheUserstate.getInstance().update(MainWindowController.getUser());}
                catch (ClassNotFoundException | IOException ex) { System.out.println(ex.getMessage());}
                // chat liste wieder anzeigen
                this.root.displayChatList();
                this.removeErrorMessage();
            }
            else {
                // Fehlernachricht anzeigen
                this.displayErrorMessage(chat.getOtherUser(MainWindowController.getUser().getUsername()), ((TransactionFailedMessage)response).getError());
            }
        });

        // Zeile 3 für Fehlernachricht
        this.row3 = new HBox();
        this.getChildren().add(this.row3);
        this.row3.setAlignment(Pos.CENTER_LEFT);


        Label errorLabel = new Label();
        errorLabel.setStyle("-fx-background-color: black");
        this.row3.getChildren().add(errorLabel);
        errorLabel.setFont(new Font("System Bold", 15));
        errorLabel.setStyle("-fx-text-fill: red;");
        HBox.setMargin(errorLabel, new Insets(0, 0, 0, 20));
        errorLabel.setVisible(false);
    }
    void displayErrorMessage(String username, String error){
        System.out.println("displaying error message for chat with user: " + username);
        ((Label)this.row3.getChildren().get(0)).setText(error);
        this.row3.getChildren().get(0).setVisible(true);
    }

    void removeErrorMessage(){
        this.row3.getChildren().get(0).setVisible(false);
    }

}
