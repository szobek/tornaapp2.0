package modal;

import model.Room;

import javax.swing.*;
import java.io.File;

public class ModalRoomImages extends JDialog {
    private Room room;

    ModalRoomImages(JFrame frame, Room room) {
        super(frame, true);
        setSize(500, 500);
        this.room = room;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        createLabels();
        setLocationRelativeTo(frame);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setVisible(true);
        setAlwaysOnTop(true);
        pack();
    }

    private void createLabels() {
        String imagePath = room.getImagePath();
JPanel imagesaPanel = new JPanel();

        // fájlok száma: new File(<directory path>).list().length

        int fajlokMennyisege = new File("src/main/resources/rooms/" + imagePath).list().length;
        for (int i = 0; i < fajlokMennyisege; i++) {
            ImageIcon icon = new ImageIcon("src/main/resources/login.png");
            JLabel jLabel = new JLabel(icon);
            jLabel.setVisible(true);
            imagesaPanel.add(new JLabel(icon));
        }
        add(imagesaPanel);
    }
}
