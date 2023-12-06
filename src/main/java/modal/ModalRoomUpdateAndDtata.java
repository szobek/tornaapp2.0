package modal;

import db.RoomsInDb;
import enum_pck.Success;
import model.Room;

import javax.swing.*;

public class ModalRoomUpdateAndDtata extends JDialog {
    private JPanel mainPanel;

    private Room roomField;
    private JTextField textFieldGooglePhotos;
    private JTextField textFieldName;
    private JTextField textFieldNum;
    private JButton btnSave;
    private JButton btnClose;

    ModalRoomUpdateAndDtata(JFrame frame, Room room) {
        super(frame, true);
        if (room == null) {
            textFieldName.setHorizontalAlignment(JTextField.CENTER);
            textFieldNum.setHorizontalAlignment(JTextField.CENTER);
            textFieldGooglePhotos.setHorizontalAlignment(JTextField.CENTER);

            textFieldGooglePhotos.setMinimumSize(textFieldGooglePhotos.getPreferredSize());
        }
        this.roomField = room;
        setSize(500, 500);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        btnClose.addActionListener(e -> dispose());
        btnSave.addActionListener(e -> {

            updateRoomDatas();

            if (roomField.getId() == -1) {
                if (RoomsInDb.createRoom(roomField)) {
                    JOptionPane.showMessageDialog( null,"terem mentve","visszajelzés",JOptionPane.PLAIN_MESSAGE);
                    Success.INSERTEDROOM.setSuc(true);
                    dispose();
                }
            } else {
                if (RoomsInDb.updateRoomData(room)) {
                    Success.UPDATEROOM.setSuc(true);
                    dispose();
                }
            }
        });
        if (room != null) {
            textFieldGooglePhotos.setText(room.getImagePath());
            textFieldName.setText(room.getName());
            textFieldNum.setText(room.getNum());

        }
        setSize(400, 200);
        setLocationRelativeTo(frame);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setVisible(true);
        setAlwaysOnTop(true);
        pack();

    }

    private void updateRoomDatas() {
        this.roomField = new Room(-1, "", "", "");
        this.roomField.setName(textFieldName.getText());
        this.roomField.setNum(textFieldNum.getText());
        this.roomField.setImagePath(textFieldGooglePhotos.getText());
    }


}
