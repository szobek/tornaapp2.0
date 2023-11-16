import javax.swing.*;


import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

import java.util.ArrayList;

public class Welcome{
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
private Object[][] tableData;
private DefaultTableModel tableModel;
private String[] columnNames = { "Név", "Telefon", "E-mail" };
    private JFrame welcome;

    private ArrayList<User> users;
    public Welcome(){
        JFrame frame = new JFrame();
        frame.setBounds(100,100,800,600);

//scrollPane.setVisible(false);
        contentPanel.setVisible(false);

        frame.setContentPane(WelcomePanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Alkalmazás");
        Image im = Toolkit.getDefaultToolkit().getImage("./src/main/resources/t5.png");
        frame.setIconImage(im);
        frame.pack();

createMenu();

    }

    public void showUsers(){
        contentPanel.setVisible(true);
        scrollPane.setVisible(true);
        this.users = DBHandler.getAllFromDB();


        tableData = new Object[users.size()][3];
reFreshTableData(tableData);
        tableModel = new DefaultTableModel(tableData,columnNames);
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
                User modifyUser = (i>users.size()||i<0)?new User("","","","",0,new UserRight(0,false,false)):users.get(i);

                createDialog(modifyUser,tableModel);

            }

        });
        scrollPane.setViewportView(userListTable);
    }

    private void createMenu(){

        userList.addActionListener(e -> showUsers());
newUser.addActionListener(e -> createDialog(null,tableModel));
    }

    private void createDialog(User user, DefaultTableModel tm){



        ModalUserModify d= new ModalUserModify(user);
        JPanel s =  d.returnPanel();
d.setContentPane(s);
        d.setSize(350,250);
        d.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        d.setVisible(true);
if(user==null){
    //TODO új user felvitele db-be visszajelzés
    User newUser = d.getDataFromModal();
    DBHandler.saveNewUserInDb(newUser);

}
        showUsers();
    }

    private void reFreshTableData(Object[][] tableData){
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
