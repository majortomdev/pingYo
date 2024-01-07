import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

    public static void main(String[] args)  {

        try {
            System.out.println("Waiting for clients....");
            ServerSocket socket = new ServerSocket(7654);
            while(true){
                Socket sock = socket.accept();
                System.out.println("Connection established");
                ConversationHandler handler = new ConversationHandler(sock);
                handler.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
