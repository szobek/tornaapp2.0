package modal;

import db.RoomsInDb;
import enum_pck.Success;
import model.Room;

import javax.swing.*;

public class ModalRoomUpdateAndDtata extends JDialog {
    private JPanel mainPanel;

private Room room;
    private  JTextField textFieldGooglePhotos;
    private JTextField textFieldName;
    private JTextField textFieldNum;
    private JButton btnSave;
    private JButton btnClose;

    ModalRoomUpdateAndDtata(JFrame frame, Room room) {
        super(frame, true);
        this.room=room;
        setSize(500, 500);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
btnClose.addActionListener(e->dispose());
        btnSave.addActionListener(e -> {
            updateRoomDatas();

            if(RoomsInDb.updateRoomData(room)) {
                Success.UPDATEROOM.setSuc(true);
                dispose();
            }
        });
        textFieldGooglePhotos.setText(room.getImagePath());
        textFieldName.setText(room.getName());
        textFieldNum.setText(room.getNum());
        setSize(400,200);
        setLocationRelativeTo(frame);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setVisible(true);
        setAlwaysOnTop(true);
        pack();

    }

    private void updateRoomDatas(){
        this.room.setName(textFieldName.getText());
        this.room.setNum(textFieldNum.getText());
        this.room.setImagePath(textFieldGooglePhotos.getText());
    }

}
