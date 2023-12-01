import db.DBHandler;
import hash.PasswordHash;
import modal.Welcome;
import model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.UUID;


public class LoginForm extends JFrame {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel loginPanel;
    private JButton btnLogin;
    private JButton btnUpload;

    public LoginForm() {
        setContentPane(loginPanel);
        setTitle("login");
        btnUpload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Choose file
                JFileChooser fc = new JFileChooser();
                int result = fc.showOpenDialog(null);

// Make sure that a file was chosen, else exit
                if (result != JFileChooser.APPROVE_OPTION) {
                    System.exit(0);
                }
                // Get file path
                String path = fc.getSelectedFile().getAbsolutePath();

// Create folder "images" (variable success will be true if a folder was created and false if it did not)
                File folder = new File("images");
                boolean success = folder.mkdir();
// Get the destination of the folder and the new image (image.jpg will be the new name)
                String destination = folder.getAbsolutePath() + File.separator + "img.jpg";

                try {
                    // Copy file from source to destination
                    FileChannel source = new FileInputStream(path).getChannel();
                    FileChannel dest = new FileOutputStream(destination).getChannel();
                    dest.transferFrom(source, 0, source.size());

                    // Close shit
                    source.close();
                    dest.close();

                    System.out.println("Done");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });


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

}
