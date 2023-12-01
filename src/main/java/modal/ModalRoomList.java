package modal;

import model.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ModalRoomList extends JDialog {
    private final ArrayList<Room> rooms;
    private JPanel ListMainPanel;
    private JTable tblRoomList;
    private JScrollPane scrollPane;
    private final JFrame frame;

    ModalRoomList(JFrame frame, ArrayList<Room> roomsParam) {
        super(frame, true);
        rooms = roomsParam;
        this.frame=frame;
        setLayout(new FlowLayout());
        setContentPane(ListMainPanel);
        createRoomListTable();
        setLocationRelativeTo(null);
        setTitle("Termek");
        setSize(300,300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setModalityType(ModalityType.DOCUMENT_MODAL);
        pack();
    }

    private void createRoomListTable(){

        ListMainPanel.setVisible(true);
        scrollPane.setVisible(true);
        scrollPane.getViewport().setSize(600, 500);
        tblRoomList.setSize(300,300);
        String[] columnNames = {"id","szoba Neve", "Soba száma"};
        Object[][] tableData = new Object[rooms.size()][columnNames.length];

        for (int i= 0;i< rooms.size();i++){
            tableData[i][0]=rooms.get(i).getId();
            tableData[i][1]=rooms.get(i).getName();
            tableData[i][2]=rooms.get(i).getNum();
        }
        DefaultTableModel tblModel = new DefaultTableModel(tableData,columnNames);
        tblRoomList.setModel(tblModel);
        tblRoomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblRoomList.setAutoCreateRowSorter(true);
        tblRoomList.setRowSelectionAllowed(true);
        tblRoomList.setVisible(true);
        scrollPane.setViewportView(tblRoomList);
tblRoomList.getSelectionModel().addListSelectionListener(e->{
    if (!e.getValueIsAdjusting() && tblRoomList.getSelectedRow() != -1) {
        int data = tblRoomList.convertRowIndexToModel(tblRoomList.getSelectedRow());
        int id = Integer.parseInt( tblRoomList.getModel().getValueAt(data, 0).toString());

        int i = 0;
        while (rooms.get(i).getId()!=id) {
            i++;
        }
        Room room = rooms.get(i);
        new ModalRoomImages(frame,room);

        System.out.println(rooms.get(i));

    }

});
    }
}
