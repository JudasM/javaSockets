package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide {
    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        Socket socket = new Socket();
        BufferedReader reader = null;
        String message;

        try {
            //server socket that the server will listen on port 8080
            serverSocket = new ServerSocket(8081);
            System.out.println("Server Started....");

            //wait for connection
            socket = serverSocket.accept();
            System.out.println("Connection established..: "+socket);

            //creatuing a reading stream, to read data sent/recieved
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //reading data
            message = reader.readLine();

            // display message
            System.out.println("Message recieved from client: "+message);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {

            try {
                System.out.println("Closing the socket.....");
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
