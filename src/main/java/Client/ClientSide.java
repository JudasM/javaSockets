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
            System.out.println("Server address: "+ address);

            //socket
            socket = new Socket(address,8081);
            System.out.println("Cumminication cahnnel :" +socket);

            // reading and writing streams
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

            // data being sent

            System.out.println("Enter clientMessage: ");
            clientMessage =  scanner.nextLine();

            //writing to server
            writer.println(clientMessage);
            if(clientMessage.equalsIgnoreCase("ok")) {
                System.out.println("Communication terminated");
                System.exit(1);
            }

            //messge from server
            serverMessage = reader.readLine();
            System.out.println("Message from Server: "+serverMessage);

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                System.out.println("Communication Closing......");
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
