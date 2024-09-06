package org.main.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.main.exceptions.DuplicateChatExeption;
import org.main.exceptions.ChatWithUserException;

public class Chat implements Serializable {

    private String user1 = "";
    private String user2 = "";
    private ArrayList<Message> messages = new ArrayList<>();

    public String getUser1(){return this.user1;}
    public String getUser2(){return this.user2;}
    public void setUser1(String user1){this.user1 = user1;}
    public void setUser2(String user2){this.user2 = user2;}
    public ArrayList<Message> getMessages( ) { return this.messages;}
    public void setMessages(ArrayList<Message> messages) {this.messages = messages;}
    public String getOtherUser (String username) {
        if (this.getUser1().equals(username)) { return user2; }
        return user1;
    }

    public Message getMostRecentMsg () {

        if (messages.isEmpty()) { return null; }

        return messages.get(messages.size()-1);
    }

//    public Message getMostRecentSentMsg (String username) {
//        if (messages.isEmpty()) { return null; }
//
//        for (int i = messages.size()-1; i >= 0;  i--){
//            if (messages.get(i).getSender().equals(username)){
//                return messages.get(i);
//            }
//        }
//        return null;
//    }


    public Message getMessage (String id){
        for (Message message : this.messages) {
            if (message.getId().equals(id)) {
                return message;
            }
        }
        return null;
    }

    public JSONObject toJson () {

        JSONObject json = new JSONObject();
        json.put("user1", this.user1);
        json.put("user2", this.user2);
        JSONArray jsonMessages = new JSONArray();

        for (Message message : this.messages) {
            jsonMessages.put(message.toJson());
        }
        json.put("messages", jsonMessages);

        return json;
    }

    @Override
    public String toString() {
        return this.toJson().toString();
    }



    public Chat () {}

    public Chat(String user1, String user2) throws ChatWithUserException {

        if (user1.equals(user2)) { throw new ChatWithUserException();}
        this.user1 = user1;
        this.user2 = user2;
        this.messages = new ArrayList<>();
    }

    public String addMessage(Message message) {
//        message.setChat(this);
        this.messages.add(message);
        return message.getId();
    }


    public String addMessage(String text, String sender) {

        Message message = new Message();
        message.setContent(text);

        if (this.user1.equals(sender)) {
            message.setSender(this.user1);
            message.setReceiver(this.user2);
        }
        else {
            message.setSender(this.user2);
            message.setReceiver(this.user1);
        }

        this.messages.add(message);
        return message.getId();
    }

    public void delete (){
        // delete all chats of user
        // delete user
    }

    public void save () throws DuplicateChatExeption {}

    public Chat (JSONObject json) throws ParseException {
        this.user1 = json.getString("user1");
        this.user2 = json.getString("user2");
        JSONArray jsonList = json.getJSONArray("messages");
        for (int i = 0; i < jsonList.length(); i++) {
            Message message = new Message(jsonList.getJSONObject(i));
            this.messages.add(message);
        }
    }




//    public static void main(String[] args) throws ChatWithUserException, ParseException {
//
//        Chat chat = new Chat("dan","rob");
//        Message message = new Message();
//        message.setSender("dan");
//        message.setReceiver("rob");
//        message.setContent("new message");
//        chat.addMessage(message);
//        System.out.println(chat.toJson());
//        System.out.println(new Chat(chat.toJson()));
//
//    }



}
