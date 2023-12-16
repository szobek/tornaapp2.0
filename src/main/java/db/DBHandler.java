package db;

import Helpers.ReadConfig;
import model.User;
import model.UserRight;

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



    public static boolean saveUserRightsInDB(User user) {
        Connection con;
        try {
            con = connectToDb();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        boolean success = false;
        if (con != null) {
            try {

                String query = "UPDATE `user_rights` set newuser=?, listreserves=? WHERE user_id= ?";

                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setBoolean(1, user.getUserRight().isCreateUser());
                preparedStmt.setBoolean(2, user.getUserRight().isReserveList());
                preparedStmt.setInt(3, user.getUserId());


                preparedStmt.executeUpdate();

                con.close();
                success = true;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("hiba...");
        }
        return success;
    }
}
