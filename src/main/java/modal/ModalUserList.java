package modal;

import db.DBHandler;
import enum_pck.Success;
import model.User;
import model.UserRight;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ModalUserList extends JDialog {

    private JPanel ContentMainPanel;
    private JTable userListTable;
    private JScrollPane scrollPane;
    private final String[] columnNames = {"Név", "Telefon", "E-mail"};
    private ArrayList<User> users;
    private final JFrame frame;

    private Object[][] tableData;
    private DefaultTableModel tableModel;

    ModalUserList(JFrame frame){

        super(frame,true);
        this.frame=frame;
        setTitle("Felhasználók");
        setSize(300,400);
        setLocationRelativeTo(frame);
        setContentPane(ContentMainPanel);
        showUsersListInTable();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.DOCUMENT_MODAL);
        setVisible(true);
        pack();
    }
    private void showUsersListInTable(){
        scrollPane.setVisible(true);
        scrollPane.getViewport().setSize(600, 500);
        userListTable.setSize(600, 500);
        getDataFromDB();


        tableData = new Object[users.size()][3];
        reFreshTableData(tableData);
        tableModel = new DefaultTableModel(tableData, columnNames);

        userListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userListTable.setAutoCreateRowSorter(true);
        userListTable.setRowSelectionAllowed(true);

        userListTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting() && userListTable.getSelectedRow() != -1) {
                int a = userListTable.convertRowIndexToModel(userListTable.getSelectedRow());
                String email = userListTable.getModel().getValueAt(a, 2).toString();

                int i = 0;
                while (!users.get(i).getEmail().equals(email)) {
                    i++;
                }
                User modifyUser = (i > users.size() || i < 0) ? new User("", "", "", "", 0, new UserRight(false, false)) : users.get(i);

                createDialog(modifyUser, frame);

            }

        });
        scrollPane.setViewportView(userListTable);
    }

    private void reFreshTableData(Object[][] tableData) {
        getDataFromDB();
        DefaultTableModel dm = (DefaultTableModel) userListTable.getModel();
        int rowCount = dm.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }

        for (int i = 0; i < users.size(); i++) {
            tableData[i][0] = users.get(i).getUserName();
            tableData[i][1] = users.get(i).getPhone();
            tableData[i][2] = users.get(i).getEmail();

        }
        tableModel = new DefaultTableModel(tableData, columnNames);
        userListTable.setModel(tableModel);
    }

    private void getDataFromDB() {
        this.users = DBHandler.getAllFromDB();
    }

    private void createDialog(User user, JFrame frame) {
        if (user == null) new ModalUserModify(frame);
        else new ModalUserModify(user, frame);

        if(Success.UPDATEUSER.isSuc()){
            reFreshTableData(tableData);
        }

    }
}
