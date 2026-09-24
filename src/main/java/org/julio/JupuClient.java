package org.julio;
import org.julio.clipboard.ClipboardManager;
import org.julio.clipboard.ClipboardMonitor;

import java.io.*;
import java.net.Socket;

public class JupuClient {
    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private String username;
    private ClipboardMonitor clipboardMonitor;


    public JupuClient (String username){
        this.username = username;
    }

    public void connectToServer(String hostToConnect, int portToConnect){
        try{
            clientSocket = new Socket(hostToConnect, portToConnect);
            in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            out = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
        }
       catch (IOException e){
            throw new RuntimeException(e);
       }
    }

    public void startMonitor(){
        clipboardMonitor = new ClipboardMonitor(new ClipboardManager());
        Thread monitorThread = new Thread(clipboardMonitor);
        monitorThread.start();
    }

    public void sendUsername(){
        try{
            out.writeUTF(username);
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromChat;
                while (true){
                    try{
                        messageFromChat = in.readUTF();
                        System.out.println(messageFromChat);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }


}
