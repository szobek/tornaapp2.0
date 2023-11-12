import javax.swing.*;

public class ModalUserModify extends JDialog {
    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel ModalMainPanel;

    ModalUserModify(){
        setContentPane(ModalMainPanel);
        setVisible(true);
        setTitle("Modify Modal");
        setSize(300,200);
    }
}
