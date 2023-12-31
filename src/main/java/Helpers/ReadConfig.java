package Helpers;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {
    public static Properties readConfig() {
        Properties prop = new Properties();

        String fileName = "app.config";
        ClassLoader classLoader = ReadConfig.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            prop.load(inputStream);
        } catch (FileNotFoundException ex) {
            String hiba = "Nem találja a fájlt";
            JOptionPane.showMessageDialog(null,hiba,"",JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            String hiba = "I/O hiba";
            JOptionPane.showMessageDialog(null,hiba,"",JOptionPane.ERROR_MESSAGE);
        }
        return prop;

    }
    public static boolean isProd(){
        Properties properties = readConfig();
        int prod = Integer.parseInt(properties.getProperty("prod"));
        return (prod>0);
    }
}
