package org.main.gui.mainwindow.v2.components.root.messagesbar.messagesview.message;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class DatePane extends HBox {

    protected Label datetimeLabel = new Label();

    public DatePane (String datetime) {

        this.getChildren().add(datetimeLabel);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: green");

        this.datetimeLabel.setText(datetime);
        this.datetimeLabel.setStyle("-fx-background-color: #ffffff ;-fx-background-radius: 5 5 5 5;");

    }
}
