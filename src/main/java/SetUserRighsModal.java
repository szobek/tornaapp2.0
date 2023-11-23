import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SetUserRighsModal {
    private JPanel userRightsMainPanel;
    private JCheckBox cbCreateNewUser;
    private JCheckBox cbReserveList;
    private JButton btnSaveRights;
    private JButton btnCancel;
    private JPanel buttonPanel;
    private User user;

    SetUserRighsModal(User user, JFrame frame) {
        createDialogBase(user, frame);
    }

    private void createDialogBase(User mUser, JFrame frame) {
        this.user = mUser;
        JDialog dialog = new JDialog(frame, "Felhasználói jogok", true);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);

        JPanel s = userRightsMainPanel;
        dialog.setContentPane(s);
        dialog.setSize(480, 250);
        dialog.setLocationRelativeTo(null);
        dialog.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent e) {
                System.out.println("jdialog window closed");

            }


        });

        btnSaveRights.addActionListener(e -> {
            System.out.println("save!!!");
            getInputDatas();
            DBHandler.saveUserRightsInDB(user);
            dialog.dispose();
        });
        btnCancel.addActionListener(e -> dialog.dispose());
        setInputData();
buttonPanel.setSize(400,200);

        dialog.setVisible(true);
        dialog.pack();
    }

    private void setInputData() {

        cbCreateNewUser.setSelected(user.getUserRight().isCreateUser());
        cbReserveList.setSelected(user.getUserRight().isReserveList());
    }

    private void getInputDatas() {
        this.user.setUserRight(new UserRight(this.user.getUserId(), cbReserveList.isSelected(), cbCreateNewUser.isSelected()));
    }
}
