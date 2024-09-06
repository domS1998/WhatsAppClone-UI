package org.main.exceptions;

public class ConnectionRefusedException extends Exception {

    public ConnectionRefusedException() {
        super ("Server refused connection!");
    }
}
