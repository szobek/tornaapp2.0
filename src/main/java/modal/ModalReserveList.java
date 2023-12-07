package modal;

import db.ReservesInDB;
import model.Reserve;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ModalReserveList extends JDialog{
    private JPanel ReserveListMainPanel;
    private JTable reserveListTable;
    private JScrollPane scrollPane;
    private ArrayList<Reserve> reserves;

    private Object[][] tableData;

    ModalReserveList(JFrame frame) {
        super(frame, "Felhasználói adatok", true);
        setLayout(new FlowLayout());
        setContentPane(ReserveListMainPanel);
        showReserves();
        setTitle("foglalások");
        setSize(600, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
        setVisible(true);


        pack();
    }

    private void showReserves() {
        this.reserves = ReservesInDB.getAllReserves();
        ReserveListMainPanel.setVisible(true);
        scrollPane.setVisible(true);
        scrollPane.getViewport().setSize(600, 500);
        reserveListTable.setSize(600, 500);
        String[] columnNames = {"id", "mettől", "meddig","szobaszám","felhasználó"};
        tableData = new Object[reserves.size()][columnNames.length];
        addDataToTable();
        DefaultTableModel tableModel = new DefaultTableModel(tableData, columnNames);
        reserveListTable.setModel(tableModel);
        reserveListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reserveListTable.setAutoCreateRowSorter(true);
        reserveListTable.setRowSelectionAllowed(true);
        reserveListTable.setVisible(true);
        scrollPane.setViewportView(reserveListTable);
    }

    private void addDataToTable() {
        for (int i = 0; i < reserves.size(); i++) {
            tableData[i][0] = reserves.get(i).getId();
            tableData[i][1] = reserves.get(i).getFrom();
            tableData[i][2] = reserves.get(i).getTo();
            tableData[i][3] = reserves.get(i).getRoomNum();
            tableData[i][4] = reserves.get(i).getUserName();

        }
    }
}
