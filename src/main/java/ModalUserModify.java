import javax.swing.*;


public class ModalUserModify extends JDialog {
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

    ModalUserModify(User user) {

        if (user != null) {
            this.user = user;
            Success.UPDATED.setSuc(false);

            setInputDatas();
        } else {
            btnDeleteUser.setVisible(false);
        }

        saveButton.addActionListener(e -> {

            if (user != null) {
                getInputDatas();
                DBHandler.updateUserData(user);


            }
            dispose();
        });
        cancelButton.addActionListener(e -> dispose());
        btnDeleteUser.addActionListener(e -> {
            Success.DELETED.setSuc(true);

            dispose();
        });
    }

    private void setInputDatas() {
        txtEmail.setText(this.user.getEmail());
        txtEmail.setEnabled(this.user.getEmail().isEmpty());
        txtKereszt.setText(this.user.getFirstName());
        txtVezetek.setText(this.user.getLastName());
        txtPhone.setText(this.user.getPhone());
        lblModifyUserName.setText(this.user.getUserName());
    }

    public User getDataFromModal() {
        return new User(txtPhone.getText(), txtKereszt.getText(), txtVezetek.getText(), txtEmail.getText(), 0, new UserRight(0, false, false));
    }

    private void getInputDatas() {
        this.user.setFirstName(txtKereszt.getText());
        this.user.setLastName(txtVezetek.getText());
        this.user.setPhone(txtPhone.getText());
    }

    public JPanel returnPanel() {
        ModalMainPanel.setSize(300, 200);
        return ModalMainPanel;
    }

    public boolean fn() {
        return Success.DELETED.isSuc();
    }

}
