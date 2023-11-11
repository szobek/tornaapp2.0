import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Welcome extends JFrame{
    private JPanel WelcomePanel;
    private JPanel meuPanel;
    private JMenu firstMenu;
    private JMenu sec;
    private JTable userListTable;
    private JScrollPane sp2;
    private JScrollPane sc;
    private ArrayList<User> users;
    public Welcome(){
        showUsers();
        setContentPane(WelcomePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        pack();

    }

    public void showUsers(){
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
        sp2.setViewportView(userListTable);
    }
}
