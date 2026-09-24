package org.julio;

import java.io.*;
import java.net.Socket;
public class ClientHandler implements Runnable{
    private DataInputStream in;
    private DataOutputStream out;
    private Socket clientSocket;
    private JupuServer jupuServer;
    protected String clientUsername;

    public ClientHandler(Socket clientSocket, JupuServer jupuServer) throws IOException {
        this.clientSocket = clientSocket;
        this.jupuServer = jupuServer;
        in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
        out = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
        clientUsername = in.readUTF();
        jupuServer.registerClient(this);
    }


    public void sendMessage(String message){
        try{
            out.writeUTF(message);
            out.flush();
            //out.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        // receive the message from client then broadcast it to all the other users
        // read from client
        String messageFromClient;
        while (true){
            try{
                messageFromClient = in.readUTF();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            jupuServer.broadcast(clientUsername, messageFromClient);
        }

    }

    public String getClientUsername(){
        return clientUsername;
    }
}

