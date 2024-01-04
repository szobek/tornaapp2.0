package modal;


import db.UserRightsInDb;
import model.User;
import model.UserRight;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ModalSetUserRights extends JDialog {
    private JPanel userRightsMainPanel;
    private JCheckBox cbCreateNewUser;
    private JCheckBox cbReserveList;
    private JButton btnSaveRights;
    private JButton btnCancel;
    private JPanel buttonPanel;
    private JCheckBox cbCreatePartner;
    private JCheckBox cbChangeRoomName;
    private JCheckBox cbChangeRoomImages;
    private JCheckBox cbChangeRoomNum;
    private JCheckBox cbCreateRoom;
    private User user;

    ModalSetUserRights(User user, JFrame frame) {
        super(frame, "Felhasználói jogok", true);
        createDialogBase(user);
    }

    private void createDialogBase(User mUser) {

        this.user = mUser;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);

        JPanel s = userRightsMainPanel;
        setContentPane(s);
        setSize(550, 320);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.out.println("jdialog window closed");

            }


        });

        btnSaveRights.addActionListener(e -> {
            getInputDatas();
            if (UserRightsInDb.saveUserRightsInDB(user)) dispose();
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
        cbCreatePartner.setSelected(user.getUserRight().isCreatePartner());

        cbChangeRoomImages.setSelected(user.getUserRight().isChangeRoomImages());
        cbChangeRoomNum.setSelected(user.getUserRight().isChangeRoomNum());
        cbCreateRoom.setSelected(user.getUserRight().isCreateRoom());
        cbChangeRoomName.setSelected(user.getUserRight().isChangeRoomName());
    }

    private void getInputDatas() {
        this.user.setUserRight(
                new UserRight(
                        cbReserveList.isSelected(),
                        cbCreateNewUser.isSelected(),
                        cbCreatePartner.isSelected(),
                        cbChangeRoomName.isSelected(),
                        cbChangeRoomImages.isSelected(),
                        cbChangeRoomNum.isSelected(),
                        cbCreateRoom.isSelected()
                )
        );
    }
}
