import javax.swing.*;


public class ModalUserModify extends JDialog{
    private JButton saveButton;
    private JTextField txtVezetek;
    private JTextField txtKereszt;
    private  JPanel ModalMainPanel;
    private JTextField txtPhone;
    private JTextField txtEmail;
    private JLabel lblEmail;
    private JLabel lblVezetek;
    private JLabel lblKereszt;
    private JLabel lblPhone;
    private JLabel lblModifyUserName;
    private JButton cancelButton;
    private  User user;

    ModalUserModify(User user) {

        if (user != null){
            this.user = user;
            setInputDatas();
        }

        saveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,"Mentés");
            dispose();
        });
        cancelButton.addActionListener(e -> {

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

    public JPanel returnPanel(){
        ModalMainPanel.setSize(300,200);
        return ModalMainPanel;
    }

}
