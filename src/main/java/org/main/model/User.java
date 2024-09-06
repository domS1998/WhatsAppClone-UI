package org.main.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.main.exceptions.UserNotFoundException;
import org.main.exceptions.WrongPasswordException;
import org.main.exceptions.DuplicateChatExeption;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;



public class User implements Serializable {

    private String username = "";
    private String password = "";
    private String tel = "";
    private ArrayList<Chat> chats = new ArrayList<>();

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public String getTel() {return tel;}
    public void setTel(String tel) {this.tel = tel;}

    public ArrayList<Chat> getChats( ) { return this.chats;}
    public void setChats(ArrayList<Chat> messages) {this.chats = messages;}

    public Chat getChat (String username){
        for (Chat chat : this.chats) {
            if (chat.getUser1().equals(username) || chat.getUser2().equals(username)) {
                return chat;
            }
        }
       return null;
    }


    public JSONObject toJson() {

        JSONObject json = new JSONObject();
        json.put("username", this.username);
        json.put("password", this.password);
        json.put("tel", this.tel);

        // Json array für chats
        JSONArray chatsJson = new JSONArray();
        for (Chat chat : this.chats) {
            chatsJson.put(chat.toJson());
        }
        // array in objekt "user" einhängen
        json.put("chats", chatsJson);

        return json;
    }

    @Override
    public String toString() {
        return this.toJson().toString();
    }

    public User (){}

    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User (JSONObject json) throws ParseException {

        this.username = json.get("username").toString();
        this.password = json.get("password").toString();
        this.tel      = json.get("tel").toString();

        JSONArray jsonList = json.getJSONArray("chats");

        for (int i = 0; i < jsonList.length(); i++) {
            this.chats.add(new Chat(jsonList.getJSONObject(i)));
        }
    }


    public static void login (String username, String password) throws UserNotFoundException, WrongPasswordException {
//        UserDAO.login(username, password);
    }

    public void addChat(Chat chat) {
        // prüfen, ob chat bereits existiert
        this.chats.add(chat);           // message in db speichern
    }




    public void update(){

        // api message "update user info"
    }


    public void save() throws DuplicateChatExeption {

        // api message "register new user

//        new UserDAO(this).save();
//        for (var chat : this.chats){
//            chat.save(); // jeden chat in db speichern
//        }
    }



    public void load (String username, String password) throws UserNotFoundException, WrongPasswordException {

        // api message "load user"

//        UserDAO dao = new UserDAO();
//        dao.load(username, password);
//
//        this.username = dao.getUsername();
//        this.password = dao.getPassword();
//        this.tel = dao.getTel();
//
//        this.setChats(Chat.loadAll(username));
    }

    // chats des users laden
    public void loadAllChats () {

        // api message "load chat, user xxx, anzahl = all

//        ArrayList<ChatDAO> list = ChatDAO.loadAll(username);
//        for (var x : list){
//            Chat chat = new Chat(x);
//            this.chats.add(chat);
//        }
    }

    // n chats des users laden
    public void loadChats (int n) {

        // api message "load chats", user xxx, number, xxx


//        ArrayList<ChatDAO> list = ChatDAO.loadN(username, n);
//        for (var x : list){
//            Chat chat = new Chat(x);
//            this.chats.add(chat);
//        }
    }


    public void deleteChat(Chat chat){

        // api message "delete chat xxx"

        this.chats.remove(chat); // chat aus liste entfernen
    }

    public void delete (){

        // api message "delete user"

    }


//    public static void main(String[] args) throws ParseException {
//
//
//        User user = new User();
//        user.setUsername("testuser");
//        user.setPassword("testpassword");
//        user.setTel("000000000");
//
//        Chat chat = new Chat();
//        System.out.println(chat.toJson());
//        chat.setUser1("testuser1");
//        System.out.println(chat.toJson());
//        chat.setUser2("testuser2");
//        System.out.println(chat.toJson());
//        chat.addMessage( "new Message", "testuser1");
//        System.out.println(chat.toJson());
//        user.addChat(chat);
//
//        System.out.println(user.toJson());
//
//
//
////        System.out.println(chat.toJson());
//
////        System.out.println(new User(user.toJson()));
//
//    }
}
