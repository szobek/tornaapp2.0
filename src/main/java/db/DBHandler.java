package db;

import Helpers.ReadConfig;

import javax.swing.*;
import java.sql.*;
import java.util.Properties;

public class DBHandler {

    public static Connection connectToDb() {
        Properties properties = ReadConfig.readConfig();
        Connection con = null;
        try {
            // r0^WuTd4$eBp szobekwe_tornaapp

            Class.forName("com.mysql.cj.jdbc.Driver");
            String[] local = {
                    properties.getProperty("localDbUrl"),
                    properties.getProperty("localDbUser"),
                    properties.getProperty("localDbpaaword", "")
            };

            String[] domain = {
                    properties.getProperty("remoteDbUrl"),
                    properties.getProperty("remoteDbUser"),
                    properties.getProperty("remoteDbPassword")
            };
            con = (ReadConfig.isProd())? DriverManager.getConnection(domain[0], domain[1], domain[2]):DriverManager.getConnection(local[0],local[1],local[2]);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (con == null) {
            JOptionPane.showMessageDialog(null,
                    "nem sikerült az adatbázishoz csatlakozni",
                    "Csatlakozási hiba",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return con;
    }


}
