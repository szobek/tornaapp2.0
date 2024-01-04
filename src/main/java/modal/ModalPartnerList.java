package modal;

import db.PartnerInDb;
import model.Partner;
import model.UserRight;
import tablemodels.PartnerTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class ModalPartnerList extends JDialog {
    private JPanel mainPanel;
    private JTable tblPartnerList;
    private JScrollPane scrollPane;
    private final JFrame frame;
    private final DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

    public ModalPartnerList(JFrame frame) {
        super(frame, "Partnerek listája", true);
        this.frame = frame;
        setSize(new Dimension(300, 300));
        createPartnerListTable();
        setContentPane(mainPanel);
        setLocationRelativeTo(frame);
        setVisible(true);
        setResizable(false);
        pack();
    }

    private void createPartnerListTable() {
        setTableModel();
        setScrollPaneConfig();
        setTableConfig();


    }


    private void setTableModel() {
        tblPartnerList.setModel(new PartnerTableModel());
    }

    private void setTableConfig() {
        tblPartnerList.setVisible(true);
        tblPartnerList.setSize(300, 300);
        tblPartnerList.setUpdateSelectionOnSort(false);
        tblPartnerList.setAutoCreateRowSorter(true);
        tblPartnerList.getSelectionModel().addListSelectionListener(e -> {
            ArrayList<Partner> partners = PartnerInDb.getAllFromDB();
            if (!e.getValueIsAdjusting() && tblPartnerList.getSelectedRow() != -1) {
                int a = tblPartnerList.convertRowIndexToModel(tblPartnerList.getSelectedRow());
                String email = tblPartnerList.getModel().getValueAt(a, 2).toString();
                int i = 0;
                while (i < partners.size() && !partners.get(i).getEmail().equals(email)) {
                    i++;
                }
                Partner modifyPartner = (i >= partners.size() || i < 0) ? new Partner("",
                        "",
                        "",
                        "",
                        -1,
                        new UserRight(
                                false,
                                false,
                                false,
                                false,
                                false,
                                false,
                                false
                        )) : partners.get(i);
                new ModalCreateAndUpdateParner(frame, modifyPartner);
                tblPartnerList.setModel(new PartnerTableModel());
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                tblPartnerList.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
            }
        });
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblPartnerList.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

    }

    private void setScrollPaneConfig() {
        scrollPane.setVisible(true);
        scrollPane.getViewport().setSize(600, 500);
        scrollPane.setViewportView(tblPartnerList);
    }


}
