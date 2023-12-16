package tests.dbtest;

import Helpers.ReadConfig;
import modal.Welcome;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

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
        ClassLoader classLoader = ReadConfig.class.getClassLoader();
        assertNotNull(classLoader.getResource("app.config"));
    }
}
