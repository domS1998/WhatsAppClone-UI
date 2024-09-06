package org.main.gui.mainwindow.v2;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.main.gui.mainwindow.v2.components.root.Root;
import org.main.model.Chat;
import org.main.model.User;
import org.main.net.api.transaction.LoginTransaction;
import org.main.net.api.transaction.messages.result.LoginResponse;
import org.main.net.threads.ResponseListenerThread;


public class MainWindowController{

    // FX Gui Objekte
    private Stage mainStage = null; // Hauptfenster
    private Scene scene = null; // aktuelle Scene bzw. Fensterinhalt von mainScene
    private Root root = null; // Wurzelobjekt des Guis, HBox

    // Controller Variablen
    private static User user = null;
    private static Chat curChat = null;      // aktuelle im Nachrichtenfenster geladener chat
    private static boolean loggedIn = false;

    public Stage getStage() {
        return this.mainStage;
    }
    public Scene getScene() {
        return this.scene;
    }
    public Root getRoot() {
        return root;
    }
    public static User getUser() {
        return user;
    }
    public static Chat getCurChat() {
        return curChat;
    }
    public static void setUser(User user_) {
        user = user_;
    }
    public static void setCurChat(Chat chat) {
        curChat = chat;
    }
    public static boolean isLoggedIn() {
        return loggedIn;
    }
    public static void setLoggedIn(boolean loggedIn_) {
        loggedIn = loggedIn_;
    }
    public void setMainStage(Stage mainStage) {this.mainStage = mainStage;}
    public void setRoot(Root root) {this.root = root;}

    public MainWindowController (Stage mainStage, User user, boolean loggedIn) {

        this.setLoggedIn(loggedIn);
        setUser(user);

        connectToServer();

        // CSS laden
        try {
            String styleSheet = getClass()
                                 .getClassLoader()
                                  .getResource("style.css")
                                   .toExternalForm();

            mainStage.getScene().getStylesheets().add(styleSheet);
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        setUser(user);
        this.mainStage = mainStage;
        this.scene = mainStage.getScene();
        this.mainStage.getScene().setRoot(new Root(user, curChat)); // Gui aubauen
        root  = (Root) mainStage.getScene().getRoot();

        // alle Nachrichtenboxen vorinitialisieren
        this.root.initAllMessagePane();

    }


    void connectToServer(){

        if (! loggedIn) {

            LoginTransaction login = new LoginTransaction(user.getUsername(), user.getPassword());
            login.start();
            LoginResponse loginResponse = login.getResult();

            if (loginResponse != null) {
                if (loginResponse.isLoggedIn()) {

                    System.out.println("MainWindowController: login success");
                    loggedIn = true;

                } else {
                    System.out.println("MainWindowController: login failed");
                }
            } else {
                System.out.println("MainWindowController: Server not reachable");
            }
        }
        // Wenn anfangs oder jetzt eingeloggt
        if (loggedIn) {
            System.out.println("MainWindowController: starting listener thread ...");
            ResponseListenerThread listenerThread = new ResponseListenerThread(user.getUsername(), user.getPassword(), this);
            listenerThread.start();
//            setConnectedDot(true);
        }
        else {
            System.out.println("MainWindowController: Not logged in. Cannot start listener thread");
        }

    }




}