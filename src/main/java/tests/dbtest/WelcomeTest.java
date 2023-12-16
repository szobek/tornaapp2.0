package tests.dbtest;

import Helpers.ReadConfig;
import modal.Welcome;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class WelcomeTest {

    @Test
    void tesztTitle() {
        Welcome welcome = new Welcome();
        assertEquals("Alkalmazás", welcome.getTitle());

    }
    @Test
    void tesztSize(){
        Welcome welcome = new Welcome();
        assertEquals(new Dimension(316,339),welcome.getSize());
    }

    @Test
    void tesztAvailableMenu(){
        Welcome welcome = new Welcome();
        assertTrue(welcome.getContentPane().getComponent(0) instanceof  javax.swing.JMenuBar);
    }

    @Test
    void tesztConfigFileAvailable() {
        assertNotNull(ReadConfig.class.getClassLoader().getResource("app.config"));
    }
    @Test
    void tesztClose(){
        Welcome welcome = new Welcome();
        assertEquals(JFrame.EXIT_ON_CLOSE,welcome.getDefaultCloseOperation());
    }
    @Test
    void tesztWelcomeFileAvailable() {
        assertNotNull(ReadConfig.class.getClassLoader().getResource("welcome.txt"));
    }
    @Test
    void tesztReadFile(){
        String fileName = "welcome.txt";
        StringBuilder txt = new StringBuilder();

        ClassLoader classLoader = getClass().getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            assert inputStream != null;
            try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(streamReader)) {

                String line;
                while ((line = reader.readLine()) != null) {
                    txt.append(line);
                }

            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        assertNotNull(txt);
    }

    @Test
    void tesztUserLength(){
        Welcome welcome = new Welcome();
        assertTrue(welcome.getUsersSize()>0);
    }

    @Test
    void tesztRoomLength(){
        Welcome welcome = new Welcome();
        assertTrue(welcome.getRoomsSize()>0);
    }




}
