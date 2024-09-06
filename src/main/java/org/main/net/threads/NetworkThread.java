package org.main.net.threads;


import org.main.net.ClientSocket;

import java.io.IOException;

abstract public class NetworkThread extends Thread {

    protected String username;
    protected String password;
    protected ClientSocket clientSocket;
    protected boolean connected = false;

    public boolean isConnected  () { return this.connected;}
    public ClientSocket getClient () { return this.clientSocket; }


    protected NetworkThread(String username, String password) {
        this.username = username;
        this.password = password;
    }
    protected NetworkThread(ClientSocket clientSocket, String username, String password) {
        this.clientSocket = clientSocket;
        this.username = username;
        this.password = password;
    }

    // Funktion, um manuell den Client Socket zu verbinden
    //  manuell, damit auch übergeben eines verbundenen Sockets möglich
    protected boolean connect () {

        try {
            this.clientSocket = new ClientSocket();

            if ( ! this.clientSocket.isConnected()) {
                System.out.println("Network Thread: could not connect to server");
                this.connected = false;
                return false;
            }
        } catch (IOException e) {
            System.out.println("Network Thread: could not connect to server");
            this.connected = false;
            return false;
        }

        System.out.println("Network Thread: successfully connected to server");
        this.connected = true;
        return true;
    }

}
