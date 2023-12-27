package modal;

import db.InformationInDb;
import db.PartnerInDb;
import db.RoomsInDb;
import db.UsersInDb;
import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
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
    private JMenuItem listPartnersMenuItem;
    private JMenuItem createPartnerMenuItem;

    private ArrayList<Room> rooms;

    private ArrayList<User> users;


    public Welcome() {
        super();


        setTitle("Alkalmazás");
//        frame.setResizable(false);
        setContentPane(WelcomePanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setInfosInWelcome();
        setResizable(false);
        try {
            Image image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("t5.png")));
            setIconImage(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        WelcomePanel.setPreferredSize(new Dimension(400, 300));
        pack();
        createMenu();


    }

    private void setInfosInWelcome() {
        ArrayList<Information> infos = InformationInDb.getAllInformation();
        infoPanel.removeAll();
        for (Information information : infos) {
            JTextArea info = new JTextArea(information.getMessage());
            info.setLineWrap(true);
            info.setEditable(false);
            info.setFont(new Font("Serif", Font.ITALIC, 17));
            info.setPreferredSize(new Dimension(280, 50));
            info.setMargin(new Insets(5, 5, 5, 5));
            if (information.isVisible()) infoPanel.add(info);
        }
        infoPanel.revalidate();
        infoPanel.repaint();

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
        newUser.addActionListener(e -> createDialog(this));
        menuItemReserveList.addActionListener(e -> createReserveListDialog());
        menuItemCreateReserve.addActionListener(e -> createReserveMakeDialog());

        roomListMenuItem.addActionListener(e -> getRoomsFromDb());
        createRoomMenuItem.addActionListener(e -> createRoomModal());

        infoListMenuItem.addActionListener(e -> listInformationsInModal());

        infoCreateMenuItem.addActionListener(e -> MakeAndUpdateInfoModal());
        listPartnersMenuItem.addActionListener(e -> listParners());
        createPartnerMenuItem.addActionListener(e -> createPartnerModal());
    }

    private void createPartnerModal() {
        new ModalCreateAndUpdateParner(this, new Partner("", "", "", "", -1,new UserRight(false, false)) );
    }

    private void listParners() {
        new ModalPartnerList(this);
    }

    private void MakeAndUpdateInfoModal() {
        new ModalCreateAndUpdateInformation(this, null);
    }

    private void listInformationsInModal() {
        new ModalInformationList(this);
        setInfosInWelcome();
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

    public int getUsersSize() {
        getDataFromDB();
        return users.size();
    }

    public int getRoomsSize() {
        getDataFromDB();
        return rooms.size();
    }

}
