package db;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRightsInDb {


    public static boolean saveUserRightsInDB(User user) {
        Connection con;
        try {
            con = DBHandler.connectToDb();
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
