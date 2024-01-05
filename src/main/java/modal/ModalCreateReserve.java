package modal;

import db.ReservesInDB;
import db.RoomsInDb;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

public class ModalCreateReserve extends JDialog {
    private JPanel createReserveMainPanel;
    private JComboBox<String> cbToMonth;
    private JComboBox<String> cbFromYear;
    private JComboBox<String> cbFromDay;
    private JComboBox<String> cbFromHour;
    private JComboBox<String> cbFromMinute;
    private JComboBox<String> cbFromMonth;
    private JComboBox<String> cbToYear;
    private JComboBox<String> cbToDay;
    private JComboBox<String> cbToHour;
    private JComboBox<String> cbToMinute;
    private JButton btnSave;
    private JButton btnCancel;
    private JComboBox<People> cbUser;
    private JPanel toPanel;
    private JComboBox<Room> cbRooms;
    private JPanel roomPanel;
    private JButton btnShowRooms;
    private JButton btnNext;
    private JButton btnNext2;
    private JPanel btnPanel;
    private final ArrayList<String> days = new ArrayList<>();
    private final ArrayList<Partner> users;
    private ArrayList<Room> rooms;
private Timestamp reserveFromDate;
    private Timestamp reserveToDate;

    ModalCreateReserve(JFrame frame, ArrayList<Partner> users) {
        super(frame, "Felhasználói adatok", true);

        this.users = users;
        hideElements();
        createListeners();
        setContentPane(createReserveMainPanel);
        setUserCombobox();
        setSize(600, 600);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
        setVisible(true);
        setModal(true);
        pack();

    }

    private void hideElements() {
        roomPanel.setVisible(false);
        toPanel.setVisible(false);
        btnCancel.setVisible(false);
        btnSave.setVisible(false);
    }

    private void setUserCombobox() {

        DefaultComboBoxModel<People> cbModel = new DefaultComboBoxModel<>();
        cbUser.setModel(cbModel);
        cbModel.addAll(users);
        cbModel.setSelectedItem(users.get(0));
    }

    private void setDayInCombox(JComboBox<String> boxYear, JComboBox<String> boxMonth, JComboBox<String> boxDay) {

        DefaultComboBoxModel<String> cbModel = new DefaultComboBoxModel<>();
        boxDay.setModel(cbModel);
        cbModel.removeAllElements();
        setDays(boxYear, boxMonth);
        cbModel.addAll(days);
        boxDay.setSelectedIndex(0);
    }

    private void setDays(JComboBox<String> boxYear, JComboBox<String> boxMonth) {
        String month = Objects.requireNonNull(boxMonth.getSelectedItem()).toString();
        int dayInMonth = switch (month) {
            case "01", "03", "05", "07", "08", "10", "12" -> 31;
            case "02" -> (leap(boxYear)) ? 29 : 28;
            case "04", "06", "09", "11" -> 30;
            default -> 10;
        };
        days.clear();
        for (int i = 1; i <= dayInMonth; i++) {
            days.add(i + "");
        }
    }

    private void createListeners() {
        // button events
        btnSave.addActionListener(e -> createReserve());
        btnCancel.addActionListener(e -> dispose());


        //set from days
        cbFromYear.addActionListener(e -> {
            setDayInCombox(cbFromYear, cbFromMonth, cbFromDay);
        });

        cbFromMonth.addActionListener(e -> {
            setDayInCombox(cbFromYear, cbFromMonth, cbFromDay);
        });

        btnNext.addActionListener(e -> toPanel.setVisible(true));

        // set to days

        cbToYear.addActionListener(e -> {
            setDayInCombox(cbToYear, cbToMonth, cbToDay);
        });

        cbToMonth.addActionListener(e -> {
            setDayInCombox(cbToYear, cbToMonth, cbToDay);
            //this.rooms= RoomsInDb.getFreeRooms();
        });
        btnNext2.addActionListener(e -> {

            roomPanel.setVisible(true);
            getFreeRoomsFromDbByDate();
            btnSave.setVisible(true);
            btnCancel.setVisible(true);
            btnNext.setVisible(false);
        });


    }

    private void getFreeRoomsFromDbByDate() {
        setReserveDates();
        this.rooms = RoomsInDb.getFreeRooms(reserveFromDate, reserveToDate);
        fillRoomsCombobox();
    }


    private boolean leap(JComboBox<String> boxYear) {
        int year = Integer.parseInt(Objects.requireNonNull(boxYear.getSelectedItem()).toString());
        boolean leap;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                leap = (year % 400 == 0);
            } else
                leap = true;
        } else
            leap = false;

        return leap;
    }


    private void createReserve() {

       setReserveDates();
        int userIndex = cbUser.getSelectedIndex();
        int roomId = cbRooms.getSelectedIndex();
        // comapre dates (-1,0,1)
        if (reserveToDate.compareTo(reserveFromDate) > -1) {

            Reserve reserve = new Reserve(
                    0,
                    rooms.get(roomId).getId(),
                    users.get(userIndex).getUserId(),
                    reserveFromDate,
                    reserveToDate,
                    users.get(userIndex).getUserName(),
                    rooms.get(roomId).getName(),
                    rooms.get(roomId).getNum()
            );
            if (ReservesInDB.saveReserve(reserve)) {
                JOptionPane.showMessageDialog(null, "Foglalás mentve", "Üzenet", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Foglalást nem sikerült menteni", "Üzenet", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "A meddig nem lehet kisebb mint a mettől!", "Üzenet", JOptionPane.ERROR_MESSAGE);
        }


    }


    private void fillRoomsCombobox() {
        DefaultComboBoxModel<Room> cbModel = new DefaultComboBoxModel<>();
        cbRooms.setModel(cbModel);
        if (!rooms.isEmpty()) {
            cbModel.addAll(rooms);
            cbModel.setSelectedItem(rooms.get(0));
            btnPanel.setVisible(true);
        } else {
            btnPanel.setVisible(false);
        }

    }

    private void setReserveDates(){
        String fromDay = (Integer.parseInt(Objects.requireNonNull(cbFromDay.getSelectedItem()).toString()) < 10) ? "0" + cbFromDay.getSelectedItem() : cbFromDay.getSelectedItem().toString();
        String toDay = (Integer.parseInt(Objects.requireNonNull(cbToDay.getSelectedItem()).toString()) < 10) ? "0" + cbToDay.getSelectedItem() : cbToDay.getSelectedItem().toString();

        String from = cbFromYear.getSelectedItem() + "-" + cbFromMonth.getSelectedItem() + "-" + fromDay + " " + cbFromHour.getSelectedItem() + ":" + cbFromMinute.getSelectedItem() + ":00";
        String to = cbToYear.getSelectedItem() + "-" + cbToMonth.getSelectedItem() + "-" + toDay + " " + cbToHour.getSelectedItem() + ":" + cbToMinute.getSelectedItem() + ":00";

        reserveFromDate = Timestamp.valueOf(from);
        reserveToDate = Timestamp.valueOf(to);
    }
}
