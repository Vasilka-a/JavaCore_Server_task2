package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final Integer PORT = 8080;

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    System.out.println("New connection accepted");
                    System.out.printf("Write your name%n", clientSocket.getPort());
                    String name = in.readLine();
                    System.out.println(name);
                    out.println(clientSocket.getPort());
                    System.out.printf("Are you child? (yes/no)%n", clientSocket.getPort());
                    String question1 = in.readLine();
                    System.out.println(question1);
                    out.println(clientSocket.getPort());
                    switch (question1) {
                        case ("yes"):
                            System.out.println("Welcome to the kids area, " + name + "! Let's play!");
                            break;
                        case ("no"):
                            System.out.println("Welcome to the adult zone, " + name + "! Have a good rest, or a good working day!");
                            break;
                        default:
                            System.out.println("Wrong!");
                            break;
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
