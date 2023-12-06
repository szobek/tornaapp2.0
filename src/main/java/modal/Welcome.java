package modal;

import db.DBHandler;
import db.RoomsInDb;
import model.Room;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Welcome {
    private JPanel WelcomePanel;

    private JMenuItem newUser;
    private JMenuItem userList;
    private JMenuItem menuItemReserveList;
    private JMenuItem menuItemCreateReserve;
    private JMenuItem roomListMenuItem;
    private JTextArea txtwelcome;
    private JScrollPane htmlScrollPane;
    private final String[] columnNames = {"Név", "Telefon", "E-mail"};
    private ArrayList<Room> rooms;

    private ArrayList<User> users;
    private final JFrame frame;

    public Welcome()  {
        frame = new JFrame();
        frame.setTitle("Alkalmazás");
        WelcomePanel.setPreferredSize(new Dimension(300,400));
//        frame.setResizable(false);
        frame.setContentPane(WelcomePanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        setLabelTextAfterInitApp();
        Image im = Toolkit.getDefaultToolkit().getImage("./src/main/resources/t5.png");
        frame.setIconImage(im);
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

    private void setLabelTextAfterInitApp() {
        String fileName = "src/main/resources/welcome.txt";

        try (BufferedReader br = new BufferedReader(
                new FileReader(fileName, StandardCharsets.UTF_8))) {

            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            txtwelcome.setLineWrap(true);
            txtwelcome.setText(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



}
