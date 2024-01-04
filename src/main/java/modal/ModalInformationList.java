package modal;

import db.InformationInDb;
import enums.Success;
import model.Information;
import tableRenderers.InformationTableRenderer;
import tablemodels.InformationTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

public class ModalInformationList extends JDialog {
    private JTable infoTable;
    private JScrollPane scrollPane;
    private JPanel mainPanel;

    private ArrayList<Information> infos;
    private final JFrame jFrame;

    private final InformationTableModel informationTableModel;

    public ModalInformationList(JFrame frame) {
        super(frame, "Információk", true);
        this.jFrame = frame;
        setContentPane(mainPanel);
        getDataFromDb();
        informationTableModel = new InformationTableModel(infos, infoTable);
        createTable();
        setSize(new Dimension(600, 400));
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
    }

    private void createTable() {

        DefaultTableCellRenderer defaultTableCellRenderer = new InformationTableRenderer();


        // fejléc színezése
        infoTable.getTableHeader().setBackground(Color.GREEN);

        infoTable.setDefaultRenderer(Object.class, defaultTableCellRenderer);
        infoTable.setModel(informationTableModel);
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
                if (Success.UPDATEINFORMATION.isSuc()) {
                    getDataFromDb();
                    infoTable.setModel(new InformationTableModel(infos, infoTable));

                }
            }

        });



    }

    private void getDataFromDb() {
        infos = InformationInDb.getAllInformation();
    }

}
