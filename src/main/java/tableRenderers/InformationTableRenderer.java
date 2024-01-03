package tableRenderers;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class InformationTableRenderer extends DefaultTableCellRenderer {
    public InformationTableRenderer() {
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(String.valueOf(value));
        setBackground(Color.lightGray);
        if (value == null) {
            return super.getTableCellRendererComponent(table,
                    value, isSelected, hasFocus, row, column);
        }
        JLabel label = new JLabel(value.toString());
        label.setFont(new Font("Helvetica", Font.ITALIC, 12));
        if(String.valueOf(value).startsWith("Igen (")&column==2){

            label.setForeground(Color.BLUE);
            label.setFont(new Font("Ink Free", Font.ITALIC,12));
        }
        return label;

    }
}
