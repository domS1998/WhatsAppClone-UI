package org.main.gui.mainwindow.v2.components.root.messagesbar.bottombar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import org.main.fileio.cache.CacheUserstate;
import org.main.gui.mainwindow.v2.MainWindowController;
import org.main.gui.mainwindow.v2.components.Buttons.svgbutton.MicButton;
import org.main.gui.mainwindow.v2.components.Buttons.svgbutton.PlusIcon;
import org.main.gui.mainwindow.v2.components.Buttons.svgbutton.SendButton;
import org.main.gui.mainwindow.v2.components.root.Root;
import org.main.gui.mainwindow.v2.components.root.messagesbar.AbstractMessagesBar;
import org.main.gui.mainwindow.v2.components.root.messagesbar.MessagesBar;
import org.main.model.Message;
import org.main.net.api.transaction.NewMessageTransaction;
import org.main.sound.SoundPlayer;
import java.io.IOException;


public class BottomBar extends HBox {

    private Root root;
    private TextArea messageTextArea = new TextArea();

    public BottomBar (Root root) {

        this.root = root;

        this.setId("messagesBar_bottomBar");
        this.getStyleClass().add("messagesBar_bottomBar");

        this.setMinWidth(400);
        this.setMinHeight(60);
        this.setPrefWidth(1920-60-400);
//        this.setPrefHeight(60);
        this.setMaxWidth(1920-60-400);
//        this.setMaxHeight(60);
        this.setAlignment(Pos.CENTER_LEFT);

//        // Smiley Button
//        ImageView smileyIcon = new ImageView(new Image("pics/smiley.png"));
//        smileyIcon.setFitHeight(25);
//        smileyIcon.setPreserveRatio(true);
//        HBox.setMargin(smileyIcon, new Insets(0, 0, 0, 30));

        // PLus Icon

//        ImageView plusIcon = new ImageView(new Image("pics/plus-icon.png"));
//        plusIcon.setFitHeight(25);
//        plusIcon.setPreserveRatio(true);
//        plusIcon.setSmooth(true);
//        plusIcon.setCache(true);
//        HBox.setMargin(plusIcon, new Insets(0, 0, 0, 30));

        PlusIcon plusIcon = new PlusIcon(this.root);

        // Container
        HBox box = new HBox(/*smileyIcon,*/ plusIcon);
        box.setAlignment(Pos.CENTER_LEFT);
//        box.setMinWidtht(120);

        this.getChildren().addAll(box, messageTextArea);

        // Textfeld
        this.messageTextArea.setStyle("-fx-background-color: #ffffff;-fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ;");
//        this.messageTextArea.setStyle("");
//        this.messageTextField.setFocusTraversable(false);
        this.messageTextArea.setPromptText("Type a message");
        this.messageTextArea.getParent().requestFocus();
//        this.messageTextArea.setPrefHeight(40);
        this.messageTextArea.setPrefWidth(10000);
        HBox.setMargin(this.messageTextArea, new Insets(10, 10, 10, 20));

        this.messageTextArea.setOnKeyTyped((e)->{

//            if (e.getCharacter().equals("\n")) {
                System.out.print("Key typed into textbox: "+e.getCharacter());
//            }
        });

        SendButton sendButton = new SendButton(this.root, this.messageTextArea);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setMinWidth(120);
        hbox.setPrefWidth(120);
        hbox.getChildren().add(sendButton);

        // Mikrophone
        MicButton micButton = new MicButton(this.root);
        HBox.setMargin(micButton, new Insets(0, 0, 0, 20));

        hbox.getChildren().add(micButton);
        this.getChildren().add(hbox);


    }
}
