import javax.swing.*;
import java.awt.*;

public class ChatClient {

    static JFrame chatWindow = new JFrame("PingYo Chat");
    static JTextArea chatArea = new JTextArea(22,40);
    static JTextField textField = new JTextField(40);
    static JLabel blankLabel = new JLabel("        ");
    static JButton sendButton = new JButton("Send");

    ChatClient(){

        chatWindow.setLayout(new FlowLayout());
        chatWindow.add(new JScrollPane(chatArea));
        chatWindow.add(blankLabel);
        chatWindow.add(textField);
        chatWindow.add(sendButton);

        chatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chatWindow.setSize(475,500);
        chatWindow.setVisible(true);

        textField.setEditable(false);
        chatArea.setEditable(false);

    }
    public static void main(String[] args) {
        ChatClient client = new ChatClient();
    }
}
