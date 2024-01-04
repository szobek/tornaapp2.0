package tablemodels;

import db.UsersInDb;
import model.User;
import model.UserRight;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class UserTableModel extends AbstractTableModel {
    private final ArrayList<User> users;
    private final String[] columnNames = {"Név", "Telefon", "E-mail"};
    public UserTableModel() {
        this.users= UsersInDb.getAllFromDB();
        users.add(User.createEmptyUser());
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String a;
        switch (columnIndex){
            case 0 -> a=users.get(rowIndex).getUserName();
            case 1 -> a=users.get(rowIndex).getPhone();
            case 2 -> a=users.get(rowIndex).getEmail();
            default -> a="n/a";
        }
        return a;
    }
}
