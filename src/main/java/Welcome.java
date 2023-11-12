import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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

        userListTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting() && userListTable.getSelectedRow() != -1) {
                    int a = userListTable.convertRowIndexToModel(userListTable.getSelectedRow());
                    String email = userListTable.getModel().getValueAt(a, 2).toString();

                    int i = 0;
                    while (!users.get(i).getEmail().equals(email)) {
                        i++;
                    }
                    User modifyUser = (i>users.size()||i<0)?users.get(i):new User("","","","",0,new UserRight(0,false,false));

                    createDialog(null,modifyUser);

                }

            }
        });
        sp2.setViewportView(userListTable);
    }

    private void createDialog(final JFrame frame,User user){
        final JDialog modelDialog = new JDialog(frame, "Swing Tester",
                Dialog.ModalityType.APPLICATION_MODAL);
        modelDialog.setBounds(132, 132, 300, 200);
        Container dialogContainer = modelDialog.getContentPane();
        dialogContainer.setLayout(new BorderLayout());
        dialogContainer.add(new JLabel(user.getUserName()), BorderLayout.CENTER);
        JPanel modalPanel = new JPanel();
        modalPanel.setLayout(new FlowLayout());
        JLabel lblFirstName = new JLabel(user.getFirstName());
        JLabel lblLastName = new JLabel(user.getLastName());
        JButton okButton = new JButton("Ok");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modelDialog.setVisible(false);
            }
        });

        modalPanel.add(okButton);
        dialogContainer.add(modalPanel, BorderLayout.SOUTH);
modelDialog.setVisible(true);

    }

}
