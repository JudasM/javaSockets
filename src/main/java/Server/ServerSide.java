package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSide {
    public static void main(String[] args) {
        int port = 8081;
        System.out.println("Starting server on port " + port);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for a client...");

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

                    System.out.println("Client connected: " + socket);
                    Scanner scanner = new Scanner(System.in);
                    String clientMessage;


                    while ((clientMessage = reader.readLine()) != null) {
                        if ("ok".equalsIgnoreCase(clientMessage)) {
                            System.out.println("Client requested to terminate connection.");
                            break;
                        }

                        System.out.println("Client Message: " + clientMessage);

                        System.out.print("Server Response: ");
                        String serverResponse = scanner.nextLine();
                        writer.println(serverResponse);
                    }

                    System.out.println("Client disconnected.");
                } catch (IOException e) {
                    System.err.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }

        System.out.println("Server stopped.");

    }
}
