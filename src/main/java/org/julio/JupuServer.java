package org.julio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class JupuServer {

    private ServerSocket serverSocket;
    protected List<ClientHandler> clientHandlers;

    public JupuServer(ServerSocket serverSocket){

        this.serverSocket = serverSocket;
        clientHandlers = Collections.synchronizedList(new ArrayList<>());
    }

    public void startServer(){
        System.out.println("Creating server");
        System.out.println("Server is running in: " + serverSocket.getInetAddress().getHostAddress());
        System.out.println("Waiting for clients...");
        while (!serverSocket.isClosed()){
            accept();
        }
    }

    private void accept(){
        try{
            Socket clientSocket = serverSocket.accept();
            ClientHandler clientHandler = new ClientHandler(clientSocket, this);
            Thread handlerThread = new Thread(clientHandler);
            handlerThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerClient(ClientHandler clientHandler){
        clientHandlers.add(clientHandler);
        broadcast(clientHandler.getClientUsername(), "SERVER: " + clientHandler.getClientUsername() + " has connected!");
        //TODO: add it to the arraylist or hashmap
    }

    public void broadcast(String fromWho, String messageString){
        for (ClientHandler clientHandler : clientHandlers){
                clientHandler.sendMessage(messageString);
            }
        }


    public void shutdown(){
        // for every client in users
            // client.close()
        //server.close()
    }

}
