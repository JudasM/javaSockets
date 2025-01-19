package Client;


import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientSide {
    public static void main(String[] args) {

       String ipAddress = "localhost";
       InetAddress address = null;
       Socket socket = null;
       PrintWriter writer = null; // for writing
       BufferedReader reader = null ; // for reading
        String clientMessage, serverMessage ;
        Scanner scanner = new Scanner(System.in);

        try {
            address = InetAddress.getByName(ipAddress);
            System.out.println("Server address: " + address);

            // Establish socket connection to the server
            socket = new Socket(address, 8081);
            System.out.println("Communication channel established: " + socket);

            // Initialize input and output streams
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            while (true) {
                // Get message from client user input
                System.out.print("Enter clientMessage: ");
                clientMessage = scanner.nextLine();

                // Send message to the server
                writer.println(clientMessage);

                // Check for termination condition
                if (clientMessage.equalsIgnoreCase("ok")) {
                    System.out.println("Communication terminated.");
                    break;
                }

                // Read response from the server
                serverMessage = reader.readLine();
                if (serverMessage == null) {
                    System.out.println("Server disconnected.");
                    break;
                }

                System.out.println("Message from Server: " + serverMessage);
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Communication error: " + e.getMessage());
        } finally {
            try {
                System.out.println("Closing communication...");
                if (reader != null) reader.close();
                if (writer != null) writer.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                System.err.println("Error while closing resources: " + e.getMessage());
            }
        }

    }
}
