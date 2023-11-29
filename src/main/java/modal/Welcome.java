package modal;

import db.DBHandler;
import db.RoomsInDb;
import model.Room;
import model.User;
import model.UserRight;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Welcome {
    private JPanel WelcomePanel;
    private JPanel meuPanel;
    private JMenu firstMenu;
    private JMenu secondNenu;
    private JMenu thirdMenu;

    private JMenuBar menuBar;
    private JMenuItem newUser;
    private JMenuItem userList;
    private JPanel contentPanel;
    private JMenuItem menuItemReserveList;
    private JMenuItem menuItemCreateReserve;
    private JMenuItem newRoomMenuItem;
    private JMenuItem roomListMenuItem;
    private final String[] columnNames = {"Név", "Telefon", "E-mail"};
    private ArrayList<Room> rooms;

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

    private void getDataFromDB() {
        this.users = DBHandler.getAllFromDB();
    }

    public void showUsers() {
        new ModalUserList(frame);
    }

    private void createMenu() {
//
        userList.addActionListener(e -> showUsers());
        newUser.addActionListener(e -> createDialog(null, frame));
        menuItemReserveList.addActionListener(e -> createReserveListDialog());
        menuItemCreateReserve.addActionListener(e -> createReserveMakeDialog());
        roomListMenuItem.addActionListener(e -> getRoomsFromDb());
    }

    private void getRoomsFromDb() {
        rooms = RoomsInDb.getAllRooms();
        new ModalRoomList(frame, rooms);
    }

    private void createDialog(User user, JFrame frame) {
        if (user == null) new ModalUserModify(frame);
        else new ModalUserModify(user, frame);
    }

    private void createReserveListDialog() {
        new ModalReserveList(frame);
    }

    private void createReserveMakeDialog() {
        getDataFromDB();
        new ModalCreateReserve(frame, users);
    }



}
