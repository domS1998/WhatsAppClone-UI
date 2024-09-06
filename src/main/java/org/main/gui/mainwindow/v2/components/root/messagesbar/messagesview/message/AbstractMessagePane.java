package org.main.gui.mainwindow.v2.components.root.messagesbar.messagesview.message;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import org.main.exceptions.ChatWithUserException;
import org.main.gui.mainwindow.v2.MainWindowController;
import org.main.gui.mainwindow.v2.components.root.Root;
import org.main.gui.mainwindow.v2.components.root.chatsbar.AbstractChatsBar;
import org.main.gui.mainwindow.v2.components.root.chatsbar.ChatsBar;
import org.main.gui.mainwindow.v2.components.root.chatsbar.chatsview.ChatsView;
import org.main.gui.mainwindow.v2.components.root.chatsbar.chatsview.chatbox.ChatBox;
import org.main.model.Chat;
import org.main.model.Message;
import org.main.model.User;
import org.main.util.Util;

import java.time.LocalDateTime;
import java.util.Hashtable;

abstract public class AbstractMessagePane extends AbstractMessagesViewPane {

    protected Message message;
    protected Label userLabel    = new Label();
    protected Label phoneLabel   = new Label();
    protected Label messageLabel = new Label();
    protected Label timeLabel    = new Label();

    public Message getMessage(){return this.message;}
    public Label getUserLabel(){return this.userLabel;}
    public Label getMessageLabel(){return this.messageLabel;}
    public Label getPhoneLabel(){return this.phoneLabel;}
    public Label getTimeLabel(){return this.timeLabel;}

    public void setMessage(Message msg){this.message = msg;}
    public void setUserLabel(String username){this.userLabel.setText(username);}
    public void setMessageLabel(String message){this.messageLabel.setText(message);}
    public void setPhoneLabel(String phone){this.phoneLabel.setText(phone);}
    public void setTimeLabel(String time){this.timeLabel.setText(time);}

    public void update(Message msg){
        setMessage(msg);
        setMessageLabel(msg.getContent());
        setTimeLabel(Util.timestampToMessageTime(msg.getTimestamp()));
        setUserLabel(msg.getOtherUser(MainWindowController.getUser().getUsername()));
        if(msg.isReceived()){
            ((SendMessagePane)this).setDoubleGreyTick();
        }
        if(msg.isRead()){
            ((SendMessagePane)this).setTicked();
            try {
                ChatsBar bar = (ChatsBar)AbstractChatsBar.getChatsPane("chatsBar");
                Chat tmp = new Chat(MainWindowController.getUser().getUsername(), msg.getOtherUser(MainWindowController.getUser().getUsername()));
                ChatsView view = bar.getChatsView();
                ChatBox box  = view.getChatBox(tmp);
                // haken in chatbox setzen
                box.setTicked();
            }
            catch (ChatWithUserException e) { System.out.println(e);}
        }
    }

    public AbstractMessagePane(Root root, Message message, User user) {

       super(root);
       this.message = message;

    }
}
