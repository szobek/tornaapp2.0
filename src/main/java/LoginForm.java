import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel loginPanel;
    private JButton button1;


    public LoginForm() {
        setContentPane(loginPanel);
        setTitle("login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
          openAnother();
            }
        });
    }

    public static void main(String[] args) {
new LoginForm();
    }

    private void openAnother(){
        new Welcome();
        dispose();

    }
}
