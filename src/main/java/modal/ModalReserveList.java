package modal;

import db.ReservesInDB;
import model.Reserve;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ModalReserveList {
    private JPanel ReserveListMainPanel;
    private JTable reserveListTable;
    private JScrollPane scrollPane;
    private JPanel labelPanel;
    private JLabel lblTest;
    private ArrayList<Reserve> reserves;

    ModalReserveList(JFrame frame) {
        JDialog dialog = new JDialog(frame, "Felhasználói adatok", true);
        dialog.setLayout(new FlowLayout());
        dialog.setContentPane(ReserveListMainPanel);
        showReserves();
        dialog.setTitle("foglalások");
        dialog.setSize(600, 800);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        dialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.setVisible(true);


        dialog.pack();
    }

    private void showReserves() {
        this.reserves = ReservesInDB.getAllReserves();
        ReserveListMainPanel.setVisible(true);
        scrollPane.setVisible(true);
        scrollPane.getViewport().setSize(600, 500);
        reserveListTable.setSize(600, 500);
        System.out.println("a méret: " + reserves.size());
        String[] columnNames = {"id", "mettől", "meddig"};
        Object[][] tableData = new Object[reserves.size()][3];
        for (int i = 0; i < reserves.size(); i++) {
            tableData[i][0] = reserves.get(i).getId();
            tableData[i][1] = reserves.get(i).getFrom();
            tableData[i][2] = reserves.get(i).getTo();

        }
        DefaultTableModel tableModel = new DefaultTableModel(tableData, columnNames);
        // System.out.println(tableModel);
        reserveListTable.setModel(tableModel);
        //System.out.println(reserveListTable);
        reserveListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        reserveListTable.setAutoCreateRowSorter(true);
        reserveListTable.setRowSelectionAllowed(true);
        reserveListTable.setVisible(true);
        scrollPane.setViewportView(reserveListTable);
    }

    private void addDataToTable(Object[][] tableData) {
        for (int i = 0; i < reserves.size(); i++) {
            tableData[i][0] = "users.get(i).getUserName()";
            tableData[i][1] = "users.get(i).getPhone()";
            tableData[i][2] = "users.get(i).getEmail()";

        }
    }
}
