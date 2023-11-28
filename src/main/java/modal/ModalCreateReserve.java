package modal;

import db.ReservesInDB;
import model.Reserve;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

public class ModalCreateReserve {
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
    private JComboBox<User> cbUser;
    private JPanel toPanel;
    private final ArrayList<String> days = new ArrayList<>();
    private final ArrayList<User> users;


    ModalCreateReserve(JFrame frame, ArrayList<User> users) {
        this.users = users;
        toPanel.setVisible(false);
        JDialog dialog = new JDialog(frame, "Felhasználói adatok", true);
        createListeners(dialog);
        dialog.setContentPane(createReserveMainPanel);
        setUserCombobox();
        dialog.setSize(500, 490);

        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
        dialog.setVisible(true);
        dialog.setModal(true);
        dialog.pack();

    }

    private void setUserCombobox() {
        DefaultComboBoxModel<User> cbModel = new DefaultComboBoxModel<>();
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

    private void createListeners(JDialog dialog) {
        // button events
        btnSave.addActionListener(e -> createReserve(dialog));
        btnCancel.addActionListener(e -> dialog.dispose());


        //set from days
        cbFromYear.addActionListener(e -> {
            setDayInCombox(cbFromYear, cbFromMonth, cbFromDay);
            toPanel.setVisible(true);
        });

        cbFromMonth.addActionListener(e -> {
            setDayInCombox(cbFromYear, cbFromMonth, cbFromDay);
            toPanel.setVisible(true);
        });


        // set to days

        cbToYear.addActionListener(e -> setDayInCombox(cbToYear, cbToMonth, cbToDay));

        cbToMonth.addActionListener(e -> setDayInCombox(cbToYear, cbToMonth, cbToDay));
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


    private void createReserve(JDialog dialog) {

        String fromDay = (Integer.parseInt(Objects.requireNonNull(cbFromDay.getSelectedItem()).toString()) < 10) ? "0" + cbFromDay.getSelectedItem() : cbFromDay.getSelectedItem().toString();
        String toDay = (Integer.parseInt(Objects.requireNonNull(cbToDay.getSelectedItem()).toString()) < 10) ? "0" + cbToDay.getSelectedItem() : cbToDay.getSelectedItem().toString();

        String from = cbFromYear.getSelectedItem() + "-" + cbFromMonth.getSelectedItem() + "-" + fromDay + " " + cbFromHour.getSelectedItem() + ":" + cbFromMinute.getSelectedItem() + ":00";
        String to = cbToYear.getSelectedItem() + "-" + cbToMonth.getSelectedItem() + "-" + toDay + " " + cbToHour.getSelectedItem() + ":" + cbToMinute.getSelectedItem() + ":00";

        int userId = users.get(cbUser.getSelectedIndex()).getUserId();


        Timestamp reserveFromDate = Timestamp.valueOf(from);
        Timestamp reserveToDate = Timestamp.valueOf(to);
        // comapre dates (-1,0,1)
        if (reserveToDate.compareTo(reserveFromDate) > -1) {

            Reserve reserve = new Reserve(0, userId, reserveFromDate, reserveToDate);
            if (ReservesInDB.saveReserve(reserve)) {
                JOptionPane.showMessageDialog(null, "Foglalás mentve", "Üzenet", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Foglalást nem sikerült menteni", "Üzenet", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "A meddig nem lehet kisebb mint a mettől!", "Üzenet", JOptionPane.ERROR_MESSAGE);
        }


    }


}
