package modal;

import db.PartnerInDb;
import enum_pck.Success;
import model.Partner;

import javax.swing.*;

public class ModalCreateAndUpdateParner extends JDialog {
    private JPanel mainPanel;
    private JTextField txtFFirstName;
    private JTextField txtFPhone;
    private JTextField txtFEmail;
    private JButton btnSave;
    private JButton btnCanel;
    private JTextField txtFLastName;
    private final Partner partner;

    public ModalCreateAndUpdateParner(JFrame frame, Partner partner) {
        super(frame,"Partner módosítása",true);
        this.partner=partner;
        setSize(400,200);
        setLocationRelativeTo(frame);
        setDataToShow();
        setContentPane(mainPanel);
        createListeners();
        setVisible(true);
        pack();
    }
    private void setDataToShow(){
        if (partner.getUserId()!=-1){
            txtFFirstName.setText(partner.getFirstName());
            txtFLastName.setText(partner.getLastName());
            txtFEmail.setText(partner.getEmail());
            txtFEmail.setEditable(false);
            txtFPhone.setText(partner.getPhone());
        } else {
            setTitle("Új partner");
        }
    }

    private void createListeners(){
        btnCanel.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> updatePartnerData());

    }

    private void updatePartnerData() {
        partner.setEmail(txtFEmail.getText());
        partner.setFirstName(txtFFirstName.getText());
        partner.setLastName(txtFLastName.getText());
        partner.setPhone(txtFPhone.getText());
        if (partner.getUserId()==-1){
            if(PartnerInDb.saveNewPartnerInDb(partner)){
                Success.UPDATEPARTNER.setSuc(true);
                dispose();
            }
        } else {
            if(PartnerInDb.updatePartnerData(partner)){
                Success.UPDATEPARTNER.setSuc(true);
                dispose();
            }
        }

    }
}
