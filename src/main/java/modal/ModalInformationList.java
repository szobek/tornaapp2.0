package modal;

import db.InformationInDb;
import enum_pck.Success;
import model.Information;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ModalInformationList extends JDialog {
    private JTable infoTable;
    private JScrollPane scrollPane;
    private JPanel mainPanel;

    private ArrayList<Information> infos;
    private final JFrame jFrame;
    private Information information;
    private DefaultTableModel tableModel;
private String[] columnNames = {"Azonosító", "Üzenet", "Látható","Archivált"};
    public ModalInformationList(JFrame frame) {
        super(frame, "Információk", true);
        this.jFrame = frame;
        setContentPane(mainPanel);
        createTable();
        setSize(new Dimension(300, 400));
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
    }

    private void createTable() {
        getDataFromDb();
        mainPanel.setVisible(true);
        scrollPane.setVisible(true);
        scrollPane.getViewport().setSize(600, 500);
        infoTable.setSize(600, 500);

        Object[][] data = new Object[infos.size()][columnNames.length];
        addDataToTable(data);
        tableModel = new DefaultTableModel(data, columnNames);
        infoTable.setModel(tableModel);
        infoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        infoTable.setAutoCreateRowSorter(true);
        infoTable.setRowSelectionAllowed(true);
        infoTable.setVisible(true);
        infoTable.setUpdateSelectionOnSort(false);
        scrollPane.setViewportView(infoTable);


        infoTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting() && infoTable.getSelectedRow() != -1) {
                int rowNum = infoTable.convertRowIndexToModel(infoTable.getSelectedRow());
                int id = Integer.parseInt(infoTable.getModel().getValueAt(rowNum, 0).toString());
                int i = 0;
                while (id != infos.get(i).getId() && i < infos.size()) i++;
                Information informationModify = infos.get(i);
                new ModalCreateAndUpdateInformation(jFrame, informationModify);
                if(Success.UPDATEINFORMATION.isSuc()){
                    addDataToTable(new Object[infos.size()][columnNames.length]);

                }
            }

        });



    }

    private void addDataToTable(Object[][] data) {
        getDataFromDb();
        removeAllRowFromTable();
        for (int i = 0; i < infos.size(); i++) {
            data[i][0] = infos.get(i).getId();
            data[i][1] = infos.get(i).getMessage();
            data[i][2] = (infos.get(i).isVisible()) ? "Igen" : "Nem";
            data[i][3] = (infos.get(i).isArchived()) ? "Igen" : "Nem";

        }
        if(Success.UPDATEINFORMATION.isSuc()) {
            tableModel = new DefaultTableModel(data, columnNames);
            infoTable.setModel(tableModel);
        }
    }

    private void getDataFromDb() {
        infos = InformationInDb.getAllInformation();
    }

    private void removeAllRowFromTable() {
        DefaultTableModel dm = (DefaultTableModel) infoTable.getModel();
        int rowCount = dm.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
    }
}
