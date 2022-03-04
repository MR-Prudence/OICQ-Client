package network;


import control.Manager;
import util.Parser;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class ClientThread implements Runnable {
    private InputStream inputStream;
    Manager manager = new Manager();
    public ClientThread(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void read() {
        while (true) {
            Scanner scanner = new Scanner(inputStream);
            if (scanner.hasNext()) {
                System.out.println("回传信息：");
                String s = scanner.nextLine();
                System.out.println(s);
                Parser parser = new Parser(s);
                List<String[]> list = parser.myParser();
                String method = list.get(0)[0];
                if (method.equals("newFriend")||method.equals("delFriend")) {
                    list.remove(0);
                    manager.flushSystemAlert(list);
                    continue;
                }
                    list.remove(0);
                manager.doAccept(list);
            }
        }
    }

    @Override
    public void run() {
        read();
    }
}
