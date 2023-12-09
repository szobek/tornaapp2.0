import db.DBHandler;
import hash.PasswordHash;
import modal.Welcome;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class LoginForm extends JFrame {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel loginPanel;
    private JButton btnLogin;

    public LoginForm() {
        setContentPane(loginPanel);
        setTitle("login");
        createHttpCall("http://127.0.0.1:8000/tt");

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

        User user = DBHandler.checkLogin(email, psw);

        if (user != null) {
            openAnother();
        } else {
            JOptionPane.showMessageDialog(null, "Hibás adatok");
        }

    }

    private void createHttpCall(String remoteUrl){
        try {
            URL url = new URL(remoteUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            System.out.println("a válasz: "+content);
            in.close();
            con.disconnect();
        } catch (IOException  e) {
            System.out.println("hiba van");
            throw new RuntimeException(e);
        }


    }



}
