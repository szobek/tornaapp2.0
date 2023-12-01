package modal;

import model.Room;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ModalRoomImages extends JDialog {
    private Room room;
    private final String pathBefore = "src/main/resources/rooms/";
    private final String imagePath;

    ModalRoomImages(JFrame frame, Room room) {
        super(frame, true);
        imagePath = room.getImagePath();
        setSize(500, 500);
        this.room = room;
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        if (getImagesCount() <= 0) {
            JOptionPane.showMessageDialog(null, "nincs kép", "msg", JOptionPane.ERROR_MESSAGE);
            return;

        }
        createLabels();
        setLocationRelativeTo(frame);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setVisible(true);
        setAlwaysOnTop(true);
        pack();
    }

    private int getImagesCount() {
        String[] files = new File(pathBefore + imagePath).list();
        return (files != null) ? new File(pathBefore + imagePath).list().length : 0;
    }

    private void createLabels() {
        JPanel imagesaPanel = new JPanel();

        // fájlok száma: new File(<directory path>).list().length
        int height = 0, width = 0;
        for (int i = 0; i < getImagesCount(); i++) {
            String imageName = new File(pathBefore + imagePath).list()[i];
            File imageFile = new File(pathBefore + imagePath + "/" + imageName);

            try {
                BufferedImage imageb = ImageIO.read(imageFile);
                height += imageb.getHeight() + 5;
                width += imageb.getWidth() + 5;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ImageIcon icon = new ImageIcon(pathBefore + room.getImagePath() + "/" + imageName);
            JLabel jLabel = new JLabel(icon);
            jLabel.setVisible(true);
            imagesaPanel.add(new JLabel(icon));
        }
        add(imagesaPanel);
        setSize(width, height);
    }
}
