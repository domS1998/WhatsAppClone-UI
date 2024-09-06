package org.main.gui;

import org.main.fileio.cache.CacheUserstate;
import org.main.gui.loginwindow.LoginWindow;
import javafx.application.Application;
import javafx.stage.Stage;
import org.main.gui.mainwindow.MainWindow;
import org.main.model.User;

import org.main.gui.mainwindow.v2.MainWindowController;


public class LaunchGui extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {






//        CacheUserstate.getInstance().clear();

        // Startfenster anzeigen

        // Hauptfenster, wenn Benutzerdaten im Cache
        if ( ! CacheUserstate.getInstance().isEmpty()) {
            System.out.println("Cache not empty, pulling user data from cache");
            CacheUserstate.getInstance().print();
            User user = CacheUserstate.getInstance().getUser();
            MainWindow mainWindow = new MainWindow(primaryStage, user, false);
            mainWindow.show();
            mainWindow.center();
        }
        // ansonsten zum login Fenster
        else {
            System.out.println("cache empty, logging into server and loading userdata ...");
            LoginWindow loginWindow = new LoginWindow(primaryStage);
            loginWindow.show();
            loginWindow.center();
        }

    }

}
