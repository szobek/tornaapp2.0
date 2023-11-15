import javax.swing.*;


import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Welcome{
    private JPanel WelcomePanel;
    private JPanel meuPanel;
    private JMenu firstMenu;
    private JMenu secondNenu;
    private JTable userListTable;
    private JScrollPane sp2;
    private JMenu thirdMenu;
    private JButton button1;

    private JFrame welcome;

    private ArrayList<User> users;
    public Welcome(){
        JFrame frame = new JFrame();
        showUsers(frame);

        frame.setContentPane(WelcomePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Alkalmazás");
        Image im = Toolkit.getDefaultToolkit().getImage("./src/main/resources/t5.png");
        frame.setIconImage(im);
        frame.pack();


        button1.addActionListener(e -> {

        });
    }

    public void showUsers(JFrame frame){
        this.users = DBHandler.getAllFromDB();
        String[] columnNames = { "Név", "Telefon", "E-mail" };
        Object[][] tableData = new Object[users.size()][3];
        for (int i = 0; i < users.size(); i++) {
            tableData[i][0] = users.get(i).getUserName();

            tableData[i][1] = users.get(i).getPhone();
            tableData[i][2] = users.get(i).getEmail();

        }
        DefaultTableModel tm = new DefaultTableModel(tableData,columnNames);
        userListTable.setModel(tm);

        userListTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting() && userListTable.getSelectedRow() != -1) {
                int a = userListTable.convertRowIndexToModel(userListTable.getSelectedRow());
                String email = userListTable.getModel().getValueAt(a, 2).toString();

                int i = 0;
                while (!users.get(i).getEmail().equals(email)) {
                    i++;
                }
                User modifyUser = (i>users.size()||i<0)?new User("","","","",0,new UserRight(0,false,false)):users.get(i);

                createDialog(modifyUser);

            }

        });
        sp2.setViewportView(userListTable);
    }

    private void createMenu(){}

    private void createDialog(User user){



        ModalUserModify d= new ModalUserModify(user);
        JPanel s =  d.returnPanel();
d.setContentPane(s);
        d.setSize(350,250);
        d.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        d.setVisible(true);
    }

}
