package org.main.exceptions;

public class UserNotFoundException extends Exception{

    public UserNotFoundException(String username ) {
        super("User " + username +" does not exist in database!\n");
    }
}
