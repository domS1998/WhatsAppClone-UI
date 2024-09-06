package org.main.gui.loginwindow;

import org.main.gui.AbstractWindow;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginWindow extends AbstractWindow {

    public LoginWindow(Stage stage) throws IOException {

        super(stage);

        // get.ClassLoader !! wichtig, um .fxml zu finden
        Parent root = (Parent)FXMLLoader.load(this.getClass().getClassLoader().getResource("fxml/LoginWindow.fxml"));
        Scene scene = new Scene(root, Color.BLACK);
        this.stage.setTitle("WhatsAppClone::Login");
//        this.stage.setWidth(this.resolutionX);
//        this.stage.setHeight(870);
//        this.stage.setMinWidth(this.resolutionX);
//        this.stage.setMinHeight(this.resolutionY);
//        this.stage.setMaxWidth(this.resolutionX);
//        this.stage.setMaxHeight(this.resolutionY);

        this.stage.setMinWidth(950);
        this.stage.setMinHeight(525);

        this.stage.setWidth(1300);
        this.stage.setHeight(870);

        this.stage.setMaxWidth(1920);
        this.stage.setMaxHeight(1080);

        //this.stage.setFullScreen(true);
        this.stage.toFront();
        this.stage.setScene(scene);

        this.stage.getScene().getRoot().setStyle("-fx-background-color: #f0f2f5;");

        this.center();

    }

}
