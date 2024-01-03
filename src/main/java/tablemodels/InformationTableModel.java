package tablemodels;

import db.InformationInDb;
import model.Information;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class InformationTableModel extends AbstractTableModel {
private final ArrayList<Information> informations;
    private final String[] columnNames = {"Azosító","Látható", "Archivált", "Üzenet"};
private JTable infoTable;


    public InformationTableModel(ArrayList<Information> informations, JTable table)
    {
        this.informations= informations;
        this.infoTable = table;
    }


    @Override
    public int getRowCount() {
        return informations.size();
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
            case 0->a=informations.get(rowIndex).getId()+"";
            case 1 -> a=(informations.get(rowIndex).isVisible()) ? "Igen" : "Nem";
            case 2 -> a=(informations.get(rowIndex).isArchived()) ? "Igen "+"(dátum: "+informations.get(rowIndex).getArchived_at()+")" : "Nem";
            case 3 -> a=informations.get(rowIndex).getMessage();
            default -> a="n/a";
        }

        return a;
    }

}
