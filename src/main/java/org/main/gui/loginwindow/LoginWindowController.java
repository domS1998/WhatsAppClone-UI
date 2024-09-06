package org.main.gui.loginwindow;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.main.fileio.cache.CacheUserstate;
import org.main.model.User;
import org.main.gui.mainwindow.MainWindow;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.main.net.api.transaction.LoadUserTransaction;
import org.main.net.api.transaction.LoginTransaction;
import org.main.net.api.transaction.messages.result.LoadUserResponseMessage;
import org.main.net.api.transaction.messages.result.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginWindowController  implements Initializable {

    private static final Logger log = LoggerFactory.getLogger(LoginWindowController.class);
    // FX Gui Objekt
    private Stage stage; // aktuelles fenster
    private Scene scene; // aktuelle Scene bzw. Fensterinhalt
    private Parent root; // Wurzelobjekt des Node-Objekt der aktuellen Szene

    // Gui Elemente als Objekt-Attribtue
    @FXML StackPane rootStackpane;
    @FXML Button okButton;
    @FXML TextField usernameTextField;
    @FXML TextField passwordTextField;
    @FXML Label usernameTextFieldLabel;
    @FXML Label passwordTextFieldLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}


    @FXML public void login (ActionEvent e) throws IOException, ClassNotFoundException {


        Platform.runLater(()->{

            this.usernameTextFieldLabel.setFont(new Font("System", 25));
            this.passwordTextFieldLabel.setFont(new Font("System", 25));


            this.usernameTextField.setStyle("-fx-background-color: #ffffff; -fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ;");
            this.usernameTextField.setFocusTraversable(false);
            this.usernameTextField.setPromptText("enter username");

            this.usernameTextField.setStyle("-fx-background-color: #ffffff; -fx-focus-color: -fx-control-inner-background ; -fx-faint-focus-color: -fx-control-inner-background ;");
            this.usernameTextField.setFocusTraversable(false);
            this.usernameTextField.setPromptText("enter username");

            if ( ! this.usernameTextFieldLabel.getText().equals("")){
                System.out.println("username is empty");
                return;
            }
            if ( ! this.passwordTextFieldLabel.getText().equals("")){
                System.out.println("password is empty");
                return;
            }

            LoginTransaction login = new LoginTransaction(this.usernameTextField.getText(), this.passwordTextField.getText());
            login.start();

            LoginResponse response = login.getResult();
            System.out.println("login response: " + response);

            if (response == null) {
                System.out.println("LoginController: Server unreachable");
                return;
            }

            if (! login.getClientSocket().isConnected() ) {
                System.out.println("MainWindowController: Server unreachable");
                return;
            }

            if (response.isLoggedIn()){
                System.out.println("login successful");
            }
            LoadUserTransaction loadUser = new LoadUserTransaction(this.usernameTextField.getText(), this.passwordTextField.getText());
//            LoadUserTransaction loadUser = new LoadUserTransaction(usernameTextField.getText(), passwordTextField.getText());
            loadUser.start();

            LoadUserResponseMessage response2 = loadUser.getResult();
            System.out.println("load user response: " + response);


            User user = response2.getUser();

            System.out.println("updating user data cache");
            // Cache updaten
            try {
                CacheUserstate.getInstance().update(user);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            try {
                switchToMainWindow(e, user, true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
    }


    @FXML void switchToMainWindow(ActionEvent event, User user, boolean loggedIn) throws IOException {
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        MainWindow mainWindow = new MainWindow(this.stage, user, loggedIn);
        mainWindow.show();
    }




}
