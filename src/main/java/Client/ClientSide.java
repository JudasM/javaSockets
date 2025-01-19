package Client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSide {
    public static void main(String[] args) {

        String ipAddress = "localhost";
        InetAddress address = null;
        Socket socket = null;
        PrintWriter writer = null;
        try {
            //address of machine you want to talk to...
            address = InetAddress.getByName(ipAddress);
            System.out.println("IP Address: "+ address);

            //a socket for communication
            socket = new Socket(address, 8081);
            System.out.println("Socket: "+socket);

            //wrting stream, that sends data/infor to the server
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);

            //sending data to the server
            writer.println("----Hello Server---");


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                System.out.println("Closing communitcation socket");
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

}
