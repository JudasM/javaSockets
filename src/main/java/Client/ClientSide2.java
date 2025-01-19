package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSide2 {
    public static void main(String[] args) {
        String ipAddress = "localhost";
        int port = 8081;

        try (Socket socket = new Socket(ipAddress, port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to the server.");

            // Thread to listen for messages from the server
            Thread listenerThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = reader.readLine()) != null) {
                        System.out.println("Message from another client: " + serverMessage);
                    }
                } catch (IOException e) {
                    System.err.println("Disconnected from server.");
                }
            });
            listenerThread.start();

            // Main thread to send messages to the server
            while (true) {
                System.out.println("Client 1 message: ");
                String message = scanner.nextLine();
                writer.println(message);

                if ("ok".equalsIgnoreCase(message)) {
                    System.out.println("Exiting...");
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
