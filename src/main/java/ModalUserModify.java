import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ModalUserModify {
    private JButton saveButton;
    private JTextField txtVezetek;
    private JTextField txtKereszt;
    private JPanel ModalMainPanel;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JLabel lblEmail;
    private JLabel lblVezetek;
    private JLabel lblKereszt;
    private JLabel lblPhone;
    private JLabel lblModifyUserName;
    private JButton cancelButton;
    private JButton btnDeleteUser;
    private User user;

    ModalUserModify(User user, JFrame frame) {

        createDialogBase(user, frame);
    }

    ModalUserModify(JFrame frame) {
        createDialogBase(null, frame);
    }

    private void createDialogBase(User mUser, JFrame frame) {
        JDialog dialog = new JDialog(frame, "Felhasználói adatok", true);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);

        JPanel s = returnPanel();
        dialog.setContentPane(s);
        if (mUser != null) {
            this.user = mUser;
            setInputDatas();
        }
        dialog.setSize(480, 250);
        dialog.setLocationRelativeTo(null);
        dialog.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.out.println("jdialog window closed");

            }


        });
        saveButton.addActionListener(e -> {
            user=(user==null)?new User("","","","",0, new UserRight(0,false,false)):user;
            if(user.getEmail().isEmpty()){
                getInputDatas();
                DBHandler.saveNewUserInDb(user);
                dialog.dispose();
            } else {
getInputDatas();
                if (DBHandler.updateUserData(user)) dialog.dispose();
            }

        });
        cancelButton.addActionListener(e -> dialog.dispose());

        if (user == null) btnDeleteUser.setVisible(false);

        btnDeleteUser.addActionListener(e -> {
            Success.DELETED.setSuc(true);
            if (JOptionPane.showConfirmDialog(frame, "Valóban törli?", "Törlés", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                if (Success.DELETED.isSuc()) {
                    if(DBHandler.deleteUser(user)){
                        dialog.dispose();
                    }
                }
            }

            dialog.dispose();
        });

        dialog.setVisible(true);
        dialog.pack();
    }


    public void setInputDatas() {
        txtEmail.setText(this.user.getEmail());
        txtEmail.setEnabled(this.user.getEmail().isEmpty());
        txtKereszt.setText(this.user.getLastName());
        txtVezetek.setText(this.user.getFirstName());
        txtPhone.setText(this.user.getPhone());
        lblModifyUserName.setText(this.user.getUserName());
    }

    private void getInputDatas() {
        this.user.setFirstName(txtKereszt.getText());
        this.user.setLastName(txtVezetek.getText());
        this.user.setPhone(txtPhone.getText());
        this.user.setEmail(txtEmail.getText());
    }

    public JPanel returnPanel() {
        ModalMainPanel.setSize(300, 200);
        return ModalMainPanel;
    }



}
