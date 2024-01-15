import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {

    static JFrame chatWindow = new JFrame("PingYo Chat");//static as all swing components be uniform for each client
    static JTextArea chatArea = new JTextArea(22,40);
    static JTextField textField = new JTextField(40);
    static JLabel blankLabel = new JLabel("        ");
    static JButton sendButton = new JButton("Send");
    static BufferedReader in;
    static PrintWriter out;
    static JLabel nameLabel = new JLabel("      ");

    ChatClient(){

        chatWindow.setLayout(new FlowLayout());
        chatWindow.add(nameLabel);
        chatWindow.add(new JScrollPane(chatArea));
        chatWindow.add(blankLabel);
        chatWindow.add(textField);
        chatWindow.add(sendButton);
        //chatWindow.add(nameLabel);

        chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatWindow.setSize(475,500);
        chatWindow.setVisible(true);

        textField.setEditable(false);
        chatArea.setEditable(false);

        sendButton.addActionListener(new Listener());//so server will get sent text on this button press
        textField.addActionListener(new Listener());//repeating myself here to include <ENTER> press for same result

    }

    void startChat() throws Exception {
        String ipAddress = JOptionPane.showInputDialog(
                chatWindow,"Enter IP Address:","IP Address Required!",JOptionPane.PLAIN_MESSAGE
        );

        Socket soc = new Socket(ipAddress, 7654);//create a socket oject w/ port number to match server open port
        in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        out = new PrintWriter(soc.getOutputStream(),true);

        while(true){
            String str = in.readLine();
            if (str.equals("Name required")){
                String name = JOptionPane.showInputDialog(
                        chatWindow,
                        "Enter a unique name: ",
                        "Name required!!",
                        JOptionPane.PLAIN_MESSAGE);

                out.println(name);
            } else if (str.equals("Name already exists!!!")){
                String name = JOptionPane.showInputDialog(
                        chatWindow,
                        "Enter another name: ",
                        "Name already exists!!",
                        JOptionPane.WARNING_MESSAGE);

                out.println(name);
            }else if (str.startsWith("Name accepted")){
                textField.setEditable(true);//so opens my JTextfield 4 chat entry once the user enters a new name
                nameLabel.setText("You are logged in as: "+str.substring(14));
            }else {
                chatArea.append(str+"\n");
            }
        }
    }
    public static void main(String[] args) throws Exception {
        ChatClient client = new ChatClient();
        client.startChat();
    }
}
