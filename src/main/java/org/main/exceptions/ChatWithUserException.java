package org.main.exceptions;

public class ChatWithUserException extends Exception{

    public ChatWithUserException() {
        super("cannot start chat with user itself!");
    }
}
