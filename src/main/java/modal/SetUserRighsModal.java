package modal;


import db.DBHandler;
import model.User;
import model.UserRight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SetUserRighsModal extends JDialog {
    private JPanel userRightsMainPanel;
    private JCheckBox cbCreateNewUser;
    private JCheckBox cbReserveList;
    private JButton btnSaveRights;
    private JButton btnCancel;
    private JPanel buttonPanel;
    private User user;

    SetUserRighsModal(User user, JFrame frame) {
        super(frame, "Felhasználói jogok", true);
        createDialogBase(user);
    }

    private void createDialogBase(User mUser) {

        this.user = mUser;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);

        JPanel s = userRightsMainPanel;
        setContentPane(s);
        setSize(480, 250);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.out.println("jdialog window closed");

            }


        });

        btnSaveRights.addActionListener(e -> {
            getInputDatas();
            if (DBHandler.saveUserRightsInDB(user)) dispose();
        });
        btnCancel.addActionListener(e -> dispose());
        setInputData();
        buttonPanel.setSize(400, 200);

        setVisible(true);
        pack();
    }

    private void setInputData() {

        cbCreateNewUser.setSelected(user.getUserRight().isCreateUser());
        cbReserveList.setSelected(user.getUserRight().isReserveList());
    }

    private void getInputDatas() {
        this.user.setUserRight(new UserRight(cbReserveList.isSelected(), cbCreateNewUser.isSelected()));
    }
}
