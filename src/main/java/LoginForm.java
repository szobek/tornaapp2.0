import db.CheckLogin;
import db.DBHandler;
import hash.PasswordHash;
import modal.Welcome;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class LoginForm extends JFrame {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel loginPanel;
    private JButton btnLogin;

    public LoginForm() {
        setContentPane(loginPanel);
        setTitle("login");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        textField1.setText("kunszt.norbert@gmail.com");
        passwordField1.setText("rrrrrr");
        pack();
        setVisible(true);
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkLogin();
            }
        };
        passwordField1.addActionListener(action);
        btnLogin.addActionListener(e -> checkLogin());

    }


    public static void main(String[] args) {
        new LoginForm();
    }

    private void openAnother() {
        new Welcome();
        dispose();

    }

    private void checkLogin() {
        String email = textField1.getText();
        String psw = PasswordHash.hashing(String.valueOf(passwordField1.getPassword()));

        if (email.isEmpty()) JOptionPane.showMessageDialog(null, "Email nem lehet üres");

        if (psw.isEmpty()) JOptionPane.showMessageDialog(null, "Jelszó nem lehet üres");

        User user = CheckLogin.checkLogin(email, psw);

        if (user != null) {
            openAnother();
        } else {
            JOptionPane.showMessageDialog(null, "Hibás adatok");
        }

    }




}
