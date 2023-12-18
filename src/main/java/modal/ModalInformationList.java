package modal;

import db.InformationInDb;
import db.ReservesInDB;
import model.Information;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ModalInformationList extends JDialog {
    private JTable infoTable;
    private JScrollPane scrollPane;
    private JPanel mainPanel;

    private ArrayList<Information> infos;

    public ModalInformationList(JFrame frame) {
        super(frame, "Információk", true);
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
        String[] columnNames = {"Üzenet", "Látható"};
        Object[][] data = new Object[infos.size()][columnNames.length];
        addDataToTable(data);
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        infoTable.setModel(tableModel);
        infoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        infoTable.setAutoCreateRowSorter(true);
        infoTable.setRowSelectionAllowed(true);
        infoTable.setVisible(true);
        scrollPane.setViewportView(infoTable);


    }

    private void addDataToTable(Object[][] data) {
        for (int i = 0; i < infos.size(); i++) {
            data[i][0] = infos.get(i).getMessage();
            data[i][1] = (infos.get(i).isVisible())?"Igen":"Nem";

        }
    }

    private void getDataFromDb() {
        infos = InformationInDb.getAllInformation();
    }
}
