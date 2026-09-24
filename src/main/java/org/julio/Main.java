package org.julio;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello bebe");
        System.out.println("Choose!");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        if (option == 1){
            try{
                ServerSocket serverSocket = new ServerSocket(9999);
                JupuServer jupuServer = new JupuServer(serverSocket);
                jupuServer.startServer();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            Scanner sc = new Scanner(System.in);
            System.out.println("Username:");
            String username = sc.nextLine();
            JupuClient jupuClient = new JupuClient(username);
            jupuClient.connectToServer("localhost", 9999);
            jupuClient.startMonitor();
            //jupuClient.sendUsername();
            //jupuClient.listenForMessage();

        }
    }
}