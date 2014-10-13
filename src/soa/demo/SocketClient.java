package soa.demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Maydaycha on 9/27/14.
 */
public class SocketClient {

    private String serverAddress = "127.0.0.1";
    private int serverPort = 5566;

    public SocketClient() {

        Socket clientSocket = new Socket();

        InetSocketAddress isa = new InetSocketAddress(this.serverAddress, this.serverPort);

        Scanner inScanner = new Scanner(System.in);

        DataOutputStream out;
        DataInputStream in;

        try {
            System.out.println("Client try to connect to server");
            clientSocket.connect(isa, 10000);

            System.out.println("Connect successfully");

            out = new DataOutputStream(clientSocket.getOutputStream());

            in = new DataInputStream(clientSocket.getInputStream());

            while (inScanner.hasNext()) {
                out.writeUTF(inScanner.next());

                System.out.println(in.readUTF());
            }

            out.flush();
            out.close();
            out = null;

            in.close();
            in = null;

            clientSocket.close();
            clientSocket = null;

        } catch (IOException e) {
            System.out.println("Server close connection!");
        }
    }
}
