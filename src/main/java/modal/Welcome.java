package modal;

import db.RoomsInDb;
import db.UsersInDb;
import model.Room;
import model.TestModel;
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

    private ArrayList<Room> rooms;

    private ArrayList<User> users;

    public Welcome()  {
        super();


        setTitle("Alkalmazás");
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
        WelcomePanel.setPreferredSize(new Dimension(300,300));
        pack();
        createMenu();


    }


    private void getDataFromDB() {
        this.users = UsersInDb.getAllFromDB();
        this.rooms = RoomsInDb.getAllRooms();
    }

    public void showUsers() {
        new ModalUserList(this);
    }

    private void createMenu() {
//

        userList.addActionListener(e -> showUsers());
        newUser.addActionListener(e -> createDialog( this));
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

    private void createDialog(JFrame frame) {
        new ModalUserModify(frame);
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
            System.err.println(e.getMessage());
        }


    }


    public int getUsersSize(){
        getDataFromDB();
        return users.size();
    }

    public int getRoomsSize(){
        getDataFromDB();
        return rooms.size();
    }

}
