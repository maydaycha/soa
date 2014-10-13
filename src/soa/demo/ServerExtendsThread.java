package soa.demo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Maydaycha on 10/9/14.
 */
public class ServerExtendsThread extends Thread {

    private ServerSocket serverSocket;
    private boolean runnningServer = true;
    private final int serverPort = 5566;

    public ServerExtendsThread() throws IOException {
        serverSocket = new ServerSocket(serverPort);
    }

    public void run() {
        Socket socket;

        System.out.println("Server started in port: " + serverPort);

        while (runnningServer) {
            try {
                synchronized (serverSocket) {
                    socket = serverSocket.accept();
                }

                System.out.println("Connected, client address: " + socket.getInetAddress());

                /** set timeout, if not get the response from client within 15 ses, then throws the exception */
//                socket.setSoTimeout(60000);

//                Thread handleClientThread = new Thread(new HandleClient(socket));
//                handleClientThread.start();

                new Thread(new HandleClient(socket)).start();


            } catch(IOException e) {
                e.printStackTrace();

                System.out.println("Socket connected fail");
                System.out.println("IOException :" + e.toString());
            }
        }
    }


    private class HandleClient implements Runnable {

        private Socket socket;

        private DataInputStream in;
        private DataOutputStream out;

        HandleClient(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            try {
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    String content = in.readUTF();

                    if (content.equals("logout")) {
                        break;
                    } else {
                        System.out.println("Send From Client: " + content);

                        out.writeUTF("Next Command: ");
                    }
                }

                System.out.println("Client Logout");

                out.flush();
                out.close();

                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
