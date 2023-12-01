package modal;

import model.Room;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;

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

    private void createLabels()  {
        String imagePath = room.getImagePath();
JPanel imagesaPanel = new JPanel();

        // fájlok száma: new File(<directory path>).list().length
int height = 0,width=0;
        int fajlokMennyisege = new File("src/main/resources/rooms/" + imagePath).list().length;
        for (int i = 0; i < fajlokMennyisege; i++) {
            String imageName = new File("src/main/resources/rooms/" + imagePath).list()[i];
            File imageFile = new File("src/main/resources/rooms/" + imagePath+"/"+imageName);

            try {
                BufferedImage imageb = ImageIO.read(imageFile);
                height+=imageb.getHeight()+5;
                width+=imageb.getWidth()+5;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ImageIcon icon = new ImageIcon("src/main/resources/rooms/"+room.getImagePath()+"/"+imageName);
            JLabel jLabel = new JLabel(icon);
            jLabel.setVisible(true);
            imagesaPanel.add(new JLabel(icon));
        }
        add(imagesaPanel);
        setSize(width,height);
    }
}
