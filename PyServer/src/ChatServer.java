import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {

    static ArrayList<String> userNames = new ArrayList<String>();
    static ArrayList<PrintWriter> printWriters = new ArrayList<PrintWriter>();

    public static void main(String[] args)  {

        try {
            System.out.println("Waiting for clients....");
            ServerSocket socket = new ServerSocket(7654);
            while(true){//to set my server to be ever ready for new clients
                Socket sock = socket.accept();//returns a new socket obj for the new client
                System.out.println("Connection established");
                ConversationHandler handler = new ConversationHandler(sock);//...so a new thread for the new client
                handler.start();//.....which i now start
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
