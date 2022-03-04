package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    private static Socket socket;
    private static InputStream inputStream;
    private static OutputStream outputStream;

    public Client() {
    }

    static {
        try {
            socket = new Socket("127.0.0.1", 8080);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void write(String msg) {
        try {
            outputStream.write(msg.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(){
        String s = null;
        while (true) {
            Scanner scanner = new Scanner(inputStream);
            if (scanner.hasNext()) {
                s = scanner.nextLine();
                break;
            }
        }
        return s;
    }

    public static void StartThread(){
        ClientThread thread = new ClientThread(inputStream);
        new Thread(thread).start();
    }
}
