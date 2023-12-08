package modal;

import db.DBHandler;
import db.RoomsInDb;
import model.Room;
import model.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class Welcome extends JFrame {
    private JPanel WelcomePanel;

    private JMenuItem newUser;
    private JMenuItem userList;
    private JMenuItem menuItemReserveList;
    private JMenuItem menuItemCreateReserve;
    private JMenuItem roomListMenuItem;
    private JTextArea txtwelcome;
    private JMenuItem createRoomMenuItem;
    private JScrollPane htmlScrollPane;
    private final String[] columnNames = {"Név", "Telefon", "E-mail"};
    private ArrayList<Room> rooms;

    private ArrayList<User> users;

    public Welcome()  {
        super();
        setTitle("Alkalmazás");
        WelcomePanel.setPreferredSize(new Dimension(300,400));
//        frame.setResizable(false);
        setContentPane(WelcomePanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setLabelTextAfterInitApp();

        try {
            Image image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("t5.png")));
            setIconImage(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pack();
        createMenu();


    }


    private void getDataFromDB() {
        this.users = DBHandler.getAllFromDB();
    }

    public void showUsers() {
        new ModalUserList(this);
    }

    private void createMenu() {
//

        userList.addActionListener(e -> showUsers());
        newUser.addActionListener(e -> createDialog(null, this));
        menuItemReserveList.addActionListener(e -> createReserveListDialog());
        menuItemCreateReserve.addActionListener(e -> createReserveMakeDialog());

        roomListMenuItem.addActionListener(e -> getRoomsFromDb());
        createRoomMenuItem.addActionListener(e -> createRoomModal());
    }

    private void createRoomModal() {
        new ModalRoomUpdateAndDtata(this, null);
    }


    private void getRoomsFromDb() {
        rooms = RoomsInDb.getAllRooms();
        new ModalRoomList(this, rooms);
    }

    private void createDialog(User user, JFrame frame) {
        if (user == null) new ModalUserModify(frame);
        else new ModalUserModify(user, frame);
    }

    private void createReserveListDialog() {
        new ModalReserveList(this);
    }

    private void createReserveMakeDialog() {
        getDataFromDB();
        new ModalCreateReserve(this, users);
    }

    private void setLabelTextAfterInitApp() {
        String fileName = "welcome.txt";

        ClassLoader classLoader = getClass().getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            assert inputStream != null;
            try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(streamReader)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    txtwelcome.setText(line);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
