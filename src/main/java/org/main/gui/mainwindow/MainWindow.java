package org.main.gui.mainwindow;


import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.main.gui.AbstractWindow;

import javafx.scene.input.KeyCombination;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.main.model.User;
import java.io.IOException;

import org.main.gui.mainwindow.v2.MainWindowController;


public class MainWindow extends AbstractWindow{

    public MainWindow(Stage stage, User user, boolean loggedIn) throws IOException {
        super(stage);

//        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getClassLoader().getResource("fxml/MainWindow.fxml"));
//        Parent root = fxmlLoader.load();
//        MainWindowController controller = fxmlLoader.<MainWindowController>getController();

        Parent root = new HBox();
        Scene scene = new Scene(root, Color.BLACK);

        this.stage.setMinWidth(950);
        this.stage.setMinHeight(525);

        this.stage.setWidth(1300);
        this.stage.setHeight(870);

        this.stage.setMaxWidth(1920);
        this.stage.setMaxHeight(1080);

        this.stage.setTitle("WhatsAppClone");

//        this.stage.setX(0);
//        this.stage.setY(0);

        this.center();


        this.stage.toFront();
        this.stage.setScene(scene);
        //this.stage.setFullScreenExitKeyCombination(KeyCombination.keyCombination("esc"));
        //this.stage.setFullScreen(true);
        this.stage.toFront();


        MainWindowController controller = new MainWindowController(stage, user, loggedIn);

        // Daten weiterreichen
//        controller.setUser(user);
//        controller.setLoggedIn(loggedIn);

    }

}
