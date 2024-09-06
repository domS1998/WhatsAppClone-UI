package org.main.gui.mainwindow.v2.components.root.menubar;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import org.main.gui.mainwindow.v2.MainWindowController;
import org.main.gui.mainwindow.v2.components.Buttons.svgbutton.ProfileButton;
import org.main.gui.mainwindow.v2.components.Buttons.svgbutton.SettingsButton;
import org.main.gui.mainwindow.v2.components.root.Root;
import org.main.model.User;


public class MenuBar extends VBox {

    private VBox vboxTop    = new VBox();
    private VBox vboxBottom = new VBox();
    private Root root;

    public VBox getVboxBottom ()          { return vboxBottom; }
    public VBox getVboxTop    ()          { return vboxTop;    }
    public void setVboxTop    (VBox vbox) { vboxTop = vbox;    }
    public void setVboxBottom (VBox vbox) { vboxBottom = vbox; }

    public MenuBar (Root root, User user) {

        this.root = root;

        // äußere Box
        this.setId("menuBar");
        this.getStyleClass().add("menuBar");
        this.setMinWidth(60);
        this.getChildren().addAll(vboxTop, vboxBottom);

        // obere Vbox
        this.vboxTop.setAlignment(Pos.TOP_CENTER);
        this.vboxTop.setMinHeight(300);
        this.vboxTop.setPrefHeight(1000);
        this.vboxTop.setMaxHeight(540);

        // untere VBox
        this.vboxBottom.setAlignment(Pos.BOTTOM_CENTER);
        this.vboxBottom.setMinHeight(200);
        this.vboxBottom.setPrefHeight(1000);

        this.vboxBottom.getChildren().addAll(/*new SettingsButton(this.root),*/new ProfileButton(this.root));

    }
}
