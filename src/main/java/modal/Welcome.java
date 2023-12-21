package modal;

import db.InformationInDb;
import db.RoomsInDb;
import db.UsersInDb;
import enum_pck.Success;
import model.Information;
import model.Room;
import model.TestModel;
import model.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.table.TableModel;
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
    private JMenuItem createRoomMenuItem;
    private JPanel infoPanel;
    private JMenuItem infoListMenuItem;
    private JMenuItem infoCreateMenuItem;
    private JMenuItem TesztMenuItem;

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
setInfosInWelcome();
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

    private void setInfosInWelcome() {
        ArrayList<Information> infos = InformationInDb.getAllInformation();
        infoPanel.removeAll();
        for (Information information : infos) {
            JTextArea info = new JTextArea(information.getMessage());
            info.setLineWrap(true);
            info.setFont(new Font("Serif", Font.ITALIC, 17));
            info.setPreferredSize(new Dimension(280, 50));
            info.setMargin(new Insets(5, 5, 5, 5));
            if (information.isVisible()) infoPanel.add(info);
        }


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

        infoListMenuItem.addActionListener(e -> listInformationsInModal());

        infoCreateMenuItem.addActionListener(e -> MakeAndUpdateInfoModal());
        TesztMenuItem.addActionListener(e -> makeTestTable());
    }

    private void makeTestTable() {
    }


    private void MakeAndUpdateInfoModal() {
        new ModalCreateAndUpdateInformation(this,null);
    }

    private void listInformationsInModal() {
        new ModalInformationList(this);
//if(Success.UPDATEINFORMATION.isSuc()) setInfosInWelcome();
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

    public int getUsersSize(){
        getDataFromDB();
        return users.size();
    }

    public int getRoomsSize(){
        getDataFromDB();
        return rooms.size();
    }

}
