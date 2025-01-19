package Server;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerSide {
    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader reader = null;
        PrintWriter writer = null;
        String clientMessage, serverResponse;
        Scanner scanner = new Scanner(System.in);

        try {
            //starting the server, and listening on port 8081
            serverSocket = new ServerSocket(8081);
            System.out.println("Server started: "+serverSocket);

            //waiting for communmocation from client
            socket = serverSocket.accept();
            System.out.println("Communication established");

            //readign and wrtitng streams
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

            while (true) {

                //reading message
                clientMessage = reader.readLine();

                if (clientMessage.equalsIgnoreCase("ok")) {
                    break;
                }else  {
                    System.out.println("Client Message: " +clientMessage);
                    serverResponse = scanner.nextLine();

                    writer.println(serverResponse);

                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                System.out.println("Closing.....");
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
