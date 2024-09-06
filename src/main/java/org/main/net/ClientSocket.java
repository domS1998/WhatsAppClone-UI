package org.main.net;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import java.net.*;

public class ClientSocket {

    private boolean connected = false;
    private Socket socket     = new Socket();
    private int startPort     = 10000;
    private InetSocketAddress targetHost = new InetSocketAddress(Network.getIP(), 8080); ;

    public Socket getSocket                () { return socket     ;}
    public InetSocketAddress getTargetHost () { return targetHost ;}
    public boolean isConnected             () { return connected  ;}

    public ClientSocket () throws IOException {

        int lim = 65535;
        int port = startPort;
        while (port < lim){

            try {
                this.socket.bind(new InetSocketAddress(Network.getIP(), port));
                break;
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Client: binding of socket " + port + " to host failed");
                port++;
            }
        }

        if (port == lim) {
            System.out.println("CLient: all ports are in use, cant start new Clients");
            return;
        }

        System.out.println("Client "+this.getSocket().getLocalSocketAddress()+": connecting to " + targetHost);
        try {
            System.out.println("Client "+this.getSocket().getLocalSocketAddress()+": trying to connect to " + targetHost);
            this.socket.connect(targetHost);
            System.out.println("Client " + this.getSocket().getLocalSocketAddress() + ": connected to " + this.getSocket().getRemoteSocketAddress());
            this.connected = true;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Client: connection failed");
            this.connected = false;
        }
    }

    public void send (String message) throws IOException {
        DataOutputStream dOut = new DataOutputStream(this.socket.getOutputStream());
        dOut.writeUTF(message);
        dOut.flush();
    }

    public String read () throws IOException {
        // input stream objekt für buffer des clients
        DataInputStream dIn = new DataInputStream(socket.getInputStream());
        // gesamten inhalt als string auslesen
        String message = dIn.readUTF();
        return message;
    }

}
