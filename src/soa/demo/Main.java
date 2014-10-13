package soa.demo;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Maydaycha on 9/27/14.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.out.println("Please input the param [server/client]");
        } else {
            System.out.print(args[0]);

            if (args[0].equals("server")) {
                try {
                    new Thread(new SocketServer()).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (args[0].equals("server_extends_thread")) {

                    new ServerExtendsThread().start();

            } else if (args[0].equals("client")) {
                new SocketClient();
            } else {
                System.out.println("Not such a Service!!!");
            }
        }

    }
}
