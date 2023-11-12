import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Welcome extends JFrame{
    private JPanel WelcomePanel;
    private JPanel meuPanel;
    private JMenu firstMenu;
    private JMenu sec;
    private JTable userListTable;
    private JScrollPane sp2;
    private JMenu thirsMenu;
    private JButton button1;

    private ArrayList<User> users;
    public Welcome(){
        showUsers();
        setContentPane(WelcomePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Alkalmazás");
        Image im = Toolkit.getDefaultToolkit().getImage("./src/main/resources/t5.png");
        setIconImage(im);
        pack();


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createDialog(null);
            }
        });
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

    private void createDialog(final JFrame frame){
        final JDialog modelDialog = new JDialog(frame, "Swing Tester",
                Dialog.ModalityType.APPLICATION_MODAL);
        modelDialog.setBounds(132, 132, 300, 200);
        Container dialogContainer = modelDialog.getContentPane();
        dialogContainer.setLayout(new BorderLayout());
        dialogContainer.add(new JLabel("                         Welcome to Swing!")
                , BorderLayout.CENTER);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelDialog.setVisible(false);
            }
        });

        panel1.add(okButton);
        dialogContainer.add(panel1, BorderLayout.SOUTH);
modelDialog.setVisible(true);

    }
}
