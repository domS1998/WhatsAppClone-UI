package org.main.gui.mainwindow.v2.components.root.messagesbar.messagesview.message;

import javafx.scene.layout.HBox;
import org.main.gui.mainwindow.v2.components.root.Root;

abstract public class AbstractMessagesViewPane extends HBox {

    protected Root root;

    public AbstractMessagesViewPane(Root root) {

        this.root = root;

        this.setMaxHeight(USE_COMPUTED_SIZE);
        this.setMaxWidth(USE_COMPUTED_SIZE);
        this.setPrefHeight(USE_COMPUTED_SIZE);
        this.setPrefWidth(USE_COMPUTED_SIZE);
//        this.setStyle("-fx-background-color: purple;");
    }
}
