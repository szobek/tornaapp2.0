package modal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import model.User;

import db.DBHandler;
import model.UserRight;
import enum_pck.Success;

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
    private JButton btnUserRights;
    private User user;

    ModalUserModify(User user, JFrame frame) {
        createDialogBase(user, frame,true);
    }

    ModalUserModify(JFrame frame) {
        createDialogBase(null, frame,false);
    }

    private void createDialogBase(User mUser, JFrame frame, boolean widthter) {
        JDialog dialog = new JDialog(frame, "Felhasználói adatok", true);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);

        JPanel s = returnPanel();
        dialog.setContentPane(s);
        if (mUser != null) {
            this.user = mUser;
            setInputDatas();
        }
        if(widthter){dialog.setSize(620, 250);}else {dialog.setSize(480, 250);}

        dialog.setLocationRelativeTo(null);
        dialog.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.out.println("jdialog window closed");

            }


        });
        saveButton.addActionListener(e -> {
            user=(user==null)?new User("","","","",0, new UserRight(false,false)):user;
            if(user.getEmail().isEmpty()){
                getInputDatas();
               if( DBHandler.saveNewUserInDb(user)) {
                   Success.INSERTED.setSuc(true);
                   if(Success.INSERTED.isSuc()) dialog.dispose();
               }

            } else {
getInputDatas();
                if (DBHandler.updateUserData(user)){
                    Success.UPDATED.setSuc(true);
                    Success.UPDATEUSER.setSuc(true);
                    if(Success.UPDATED.isSuc()) dialog.dispose();
                }
            }

        });
        cancelButton.addActionListener(e -> dialog.dispose());

        if (user == null){
            btnUserRights.setVisible(false);
            btnDeleteUser.setVisible(false);
        }

        btnDeleteUser.addActionListener(e -> {

            if (JOptionPane.showConfirmDialog(frame, "Valóban törli?", "Törlés", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Success.DELETEDUSER.setSuc(true);
                if(DBHandler.deleteUser(user)){
                    dialog.dispose();
                }
            }

            dialog.dispose();
        });
        btnUserRights.addActionListener(e ->   createRightsDialog(user,frame));

        dialog.setVisible(true);
        lblEmail.setForeground(Color.BLACK);
        lblVezetek.setForeground(Color.BLACK);
        lblKereszt.setForeground(Color.BLACK);
        lblPhone.setForeground(Color.BLACK);
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
        this.user.setFirstName(txtVezetek.getText());
        this.user.setLastName(txtKereszt.getText());
        this.user.setPhone(txtPhone.getText());
        this.user.setEmail(txtEmail.getText());
    }

    public JPanel returnPanel() {
        ModalMainPanel.setSize(300, 200);
        return ModalMainPanel;
    }

    private void createRightsDialog(User user, JFrame frame) {
        new SetUserRighsModal(user,frame);

    }



}
