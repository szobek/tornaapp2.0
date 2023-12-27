package tablemodels;

import db.PartnerInDb;
import model.Partner;
import model.UserRight;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PartnerTableModel extends AbstractTableModel {
private final ArrayList<Partner> partners;
    private final String[] columnNames = {"Név", "Telefon", "E-mail"};
    public PartnerTableModel() {
        this.partners=PartnerInDb.getAllFromDB();
    }

    @Override
    public int getRowCount() {
        return partners.size();
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
            case 0 -> a=partners.get(rowIndex).getUserName();
            case 1 -> a=partners.get(rowIndex).getPhone();
            case 2 -> a=partners.get(rowIndex).getEmail();
            default -> a="n/a";
        }
        return a;
    }
}
