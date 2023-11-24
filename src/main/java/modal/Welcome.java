package modal;

import javax.swing.*;


import javax.swing.table.DefaultTableModel;

import java.awt.*;
import model.User;
import model.UserRight;
import db.DBHandler;


import java.util.ArrayList;

public class Welcome {
    private JPanel WelcomePanel;
    private JPanel meuPanel;
    private JMenu firstMenu;
    private JMenu secondNenu;
    private JTable userListTable;
    private JScrollPane scrollPane;
    private JMenu thirdMenu;

    private JMenuBar menuBar;
    private JMenuItem newUser;
    private JMenuItem userList;
    private JPanel contentPanel;
    private final String[] columnNames = {"Név", "Telefon", "E-mail"};


    private ArrayList<User> users;
    private final JFrame frame;

    public Welcome() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 600);

//scrollPane.setVisible(false);
        contentPanel.setVisible(false);

        frame.setContentPane(WelcomePanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Alkalmazás");
        Image im = Toolkit.getDefaultToolkit().getImage("./src/main/resources/t5.png");
        frame.setIconImage(im);
        meuPanel.setVisible(true);
        firstMenu.setVisible(true);
        secondNenu.setVisible(true);
        thirdMenu.setVisible(true);
        menuBar.setVisible(true);
        frame.pack();

        createMenu();

    }

    public void showUsers() {
        contentPanel.setVisible(true);
        scrollPane.setVisible(true);
        scrollPane.getViewport().setSize(600, 500);
        userListTable.setSize(600, 500);
        this.users = DBHandler.getAllFromDB();


        Object[][] tableData = new Object[users.size()][3];
        reFreshTableData(tableData);
        DefaultTableModel tableModel = new DefaultTableModel(tableData, columnNames);
        userListTable.setModel(tableModel);
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
                User modifyUser = (i > users.size() || i < 0) ? new User("", "", "", "", 0, new UserRight( false, false)) : users.get(i);

                createDialog(modifyUser, frame);

            }

        });
        scrollPane.setViewportView(userListTable);
    }

    private void createMenu() {

        userList.addActionListener(e -> showUsers());
        newUser.addActionListener(e -> createDialog(null, frame));

    }

    private void createDialog(User user, JFrame frame) {


        if (user == null) new ModalUserModify(frame);
        else new ModalUserModify(user, frame);
        showUsers();

    }




    private void reFreshTableData(Object[][] tableData) {
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

    }


}
