package modal;

import db.InformationInDb;
import model.Information;

import javax.swing.*;
import java.awt.*;

public class ModalCreateAndUpdateInformation extends JDialog {
    private JPanel mainPanel;
    private JTextField textFieldInfoMessage;
    private JCheckBox checkBoxInfoVisible;
    private JButton btnCancel;
    private JButton btnSave;

    public ModalCreateAndUpdateInformation(JFrame frame,Information information) {
        super(frame,"Infó",true);
        setListeners(information);

        setContentPane(mainPanel);
        setInputByInformation(information);
        setSize(300,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(300,400));
        setLocationRelativeTo(frame);
        setVisible(true);
        pack();
    }

    private void setListeners(Information information){
        btnCancel.addActionListener(e -> dispose());
        if(information!=null) btnSave.addActionListener(e -> {
            information.setVisible(checkBoxInfoVisible.isSelected());
            information.setMessage(textFieldInfoMessage.getText());
            InformationInDb.update(information);
            dispose();
        });
    }

    private void setInputByInformation(Information information){
        if(information!=null){
            textFieldInfoMessage.setText(information.getMessage());
            checkBoxInfoVisible.setSelected(information.isVisible());
        }
    }
}
