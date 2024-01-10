import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ChatClient.out.println(ChatClient.textField.getText());//on click ill send text to the out socket,
        ChatClient.textField.setText("");//..then i clear the textfiled
    }
}
