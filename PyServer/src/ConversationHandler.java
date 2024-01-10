import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConversationHandler extends Thread{
    Socket socket;
    BufferedReader in;
    PrintWriter out;
    String name;

    public ConversationHandler(Socket socket)throws IOException{
        this.socket = socket;
    }

    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(),true);
            int count =0;
            while(true){
                if(count>0){
                    out.println("Name already exists!!!");
                }else {
                    out.println("Name required");
                }

                name = in.readLine();
                if(name==null)return;

                if(!ChatServer.userNames.contains(name)){
                    ChatServer.userNames.add(name);
                    break;
                }
                count++;
            }

            out.println("Name accepted");
            ChatServer.printWriters.add(out);

        }catch (Exception e){

        }
    }
}
