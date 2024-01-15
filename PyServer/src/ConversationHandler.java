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
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));//inst. reader 4 getting data from socket
            out = new PrintWriter(socket.getOutputStream(),true);//inst. writer for sending data to socket
            int count =0;
            while(true){//going to run this loop until i get a new name entered
                if(count>0){
                    out.println("Name already exists!!!");
                }else {
                    out.println("Name required");
                }

                name = in.readLine();
                if(name==null)return;

                if(!ChatServer.userNames.contains(name)){//only true if name not in list
                    ChatServer.userNames.add(name);//so ill then add the new name
                    break;//and break the while loop
                }
                count++;
            }

            out.println("Name accepted "+name);
            ChatServer.printWriters.add(out);

            while (true){//this will read messages from any client and send to other clients
                String message = in.readLine();
                if(message==null){
                    return;
                }

                pw.println(name+": "+message);//using my pw to log each message to external file
                for (PrintWriter writer : ChatServer.printWriters){//to pass on message 2 each client
                    writer.println(name+": "+message);
                }
            }

        }catch (Exception e){

        }
    }
}
