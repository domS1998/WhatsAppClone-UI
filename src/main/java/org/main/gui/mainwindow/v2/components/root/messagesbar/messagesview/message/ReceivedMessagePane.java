package org.main.gui.mainwindow.v2.components.root.messagesbar.messagesview.message;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import org.main.gui.mainwindow.v2.components.root.Root;
import org.main.model.Message;
import org.main.model.User;
import org.main.util.Util;

public class ReceivedMessagePane extends AbstractMessagePane {

    public ReceivedMessagePane(Root root, Message message, User user) {

        super(root, message, user);

        this.setPadding(new Insets(3, 10, 3, 50));

        HBox container = new HBox();
        container.setPadding(new Insets(0, 0, 0, 0));
        container.setStyle("-fx-background-color: #ffffff; -fx-border-radius: 10 10 10 10;  -fx-background-radius: 7 7 7 7; -fx-effect: dropshadow(three-pass-box, rgba(209,206,200,0.8), 1, 0, 1, 1);");


        // Spalte 1
        HBox pictureBox = new HBox();
        pictureBox.setAlignment(Pos.CENTER);
        pictureBox.setMinWidth(40);
        pictureBox.setPrefWidth(40);
        pictureBox.setMaxWidth(40);
        pictureBox.setPrefHeight(USE_COMPUTED_SIZE);
        pictureBox.setPadding(new Insets(0, 30, 0, 0));
//        pictureBox.setStyle("-fx-background-color: yellow;");

        Circle picture = new Circle();
        pictureBox.getChildren().add(picture);
        picture.setFill(Color.GREY);
        picture.setRadius(15);
        picture.setTranslateY(-15);

        this.getChildren().addAll(pictureBox, container);

        // Spalte 2
        VBox vBox = new VBox();

        Polygon triangle = new Polygon(0, 0, 0, 15, -15, 0);
        triangle.setFill(Color.web("#ffffff"));
        triangle.setStyle("-fx-border-radius: 10 10 10 10;  -fx-background-radius: 7 7 7 7; -fx-effect: dropshadow(three-pass-box, rgba(209,206,200,0.8), 1, 0, 0.5, 0.5);");
        triangle.setTranslateX(-10);
        triangle.setTranslateY(0);


        container.getChildren().addAll(triangle, vBox);
        this.setAlignment(Pos.CENTER_LEFT);

        HBox hBoxUpper = new HBox();
//        hBoxUpper.setStyle("-fx-background-color: red");
        HBox hBoxLower = new HBox();
//        hBoxLower.setStyle("-fx-background-color: blue");
        vBox.getChildren().addAll(hBoxUpper, hBoxLower);
        vBox.setPadding(new Insets(5, 5, 5, 0));
//        this.setStyle("-fx-background-color: orange");

        // Zeile 1
        hBoxUpper.setAlignment(Pos.CENTER_LEFT);
        hBoxUpper.getChildren().addAll(this.phoneLabel, this.userLabel);

        // Handynummer label
        this.phoneLabel.setText(user.getTel());
        this.phoneLabel.setStyle("-fx-text-fill: #4cd7b1");
        HBox.setMargin(phoneLabel, new Insets(0, 5, 0, 0));

        // username label
        this.userLabel.setText("~ "+user.getUsername());
        this.userLabel.setStyle("-fx-text-fill: #acafb1");


        // Zeile 2
        hBoxLower.setAlignment(Pos.CENTER_LEFT);
        this.messageLabel.setText(message.getContent());
        this.messageLabel.setFont(new Font("System", 14));

        this.timeLabel.setText(Util.timestampToMessageTime(message.getTimestamp()));
        this.timeLabel.setStyle("-fx-text-fill: #6e8187");
        this.timeLabel.setFont(new Font("System", 11));
        HBox.setMargin(this.timeLabel, new Insets(15, 5, 0, 10));

        StackPane timeBox = new StackPane();
        timeBox.setPadding(new Insets(5, 5, 5, 10));
        timeBox.setAlignment(Pos.BOTTOM_RIGHT);
        timeBox.getChildren().addAll(this.timeLabel);

        container.getChildren().add(timeBox);
        hBoxLower.getChildren().addAll(this.messageLabel);


    }
}
