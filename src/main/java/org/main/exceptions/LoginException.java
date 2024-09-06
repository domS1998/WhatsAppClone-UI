package org.main.exceptions;

public class LoginException extends Exception {

    public LoginException(String username , String password) {
        super("Username or password incorrect!\n");
    }
}
