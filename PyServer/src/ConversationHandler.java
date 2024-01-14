import java.io.*;
import java.net.Socket;

public class ConversationHandler extends Thread{
    Socket socket;//ie the socket used for each individual convhandler/ thread...so will control chat to/from jframe
    BufferedReader in;
    PrintWriter out;
    String name;
    PrintWriter pw;
    static FileWriter fw;
    static BufferedWriter bw;
    public ConversationHandler(Socket socket)throws IOException{
        this.socket = socket;
        fw = new FileWriter("C:\\Users\\user\\Desktop\\ChatServer-Logs.txt",true);
        bw = new BufferedWriter(fw);
        pw = new PrintWriter(bw, true);
    }

    public void run() {//code to run on every new thread
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//getting data from socket
            out = new PrintWriter(socket.getOutputStream(),true);//sending data to socket
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

            out.println("Name accepted "+name);
            ChatServer.printWriters.add(out);

            while (true){
                String message = in.readLine();
                if(message==null){
                    return;
                }

                pw.println(name+": "+message);//using my pw to log each message to external file
                for (PrintWriter writer : ChatServer.printWriters){
                    writer.println(name+": "+message);
                }
            }

        }catch (Exception e){

        }
    }
}
