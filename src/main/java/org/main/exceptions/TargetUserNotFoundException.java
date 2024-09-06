package org.main.exceptions;

public class TargetUserNotFoundException extends Exception {

    public TargetUserNotFoundException(String username) {
        super("User " + username + " not found in db\n");
    }

}
