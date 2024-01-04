package modal;

import db.InformationInDb;
import enums.Success;
import model.Information;

import javax.swing.*;

public class ModalCreateAndUpdateInformation extends JDialog {
    private JPanel mainPanel;
    private JTextField textFieldInfoMessage;
    private JCheckBox checkBoxInfoVisible;
    private JButton btnCancel;
    private JButton btnSave;
    private JButton btnDelete;

    public ModalCreateAndUpdateInformation(JFrame frame, Information information) {
        super(frame, "Infó", true);
        setListeners(information);

        setContentPane(mainPanel);
        setInputByInformation(information);
        setSize(350, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setPreferredSize(new Dimension(400, 400));
        setLocationRelativeTo(frame);
        setVisible(true);
        pack();
    }

    private void setListeners(Information information) {
        btnCancel.addActionListener(e -> dispose());
        if (information != null) btnSave.addActionListener(e -> {
            information.setVisible(checkBoxInfoVisible.isSelected());
            information.setMessage(textFieldInfoMessage.getText());
            InformationInDb.update(information);
            dispose();
        });
        btnDelete.addActionListener(e -> deleteInformation(information));
    }

    private void deleteInformation(Information information) {
        if (JOptionPane.showConfirmDialog(null, "Valóban törli?", "Törlés", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            if (InformationInDb.delete(information)) {
                Success.UPDATEINFORMATION.setSuc(true);
                JOptionPane.showMessageDialog(null, "Információ törölve", "Üzenet", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        }
    }

    private void setInputByInformation(Information information) {
        if (information != null) {
            textFieldInfoMessage.setText(information.getMessage());
            checkBoxInfoVisible.setSelected(information.isVisible());
            if(information.isArchived()) {

                btnDelete.setVisible(false);
                btnSave.setVisible(false);
                btnCancel.setText("Bezár");

               textFieldInfoMessage.setEditable(false);
               checkBoxInfoVisible.setVisible(false);
            }

        }
    }
}
