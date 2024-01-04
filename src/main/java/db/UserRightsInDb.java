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
/*
 newuser 	listreserves 	createpartner 	changeRoomName 	changeRoomImages 	changeRoomNum 	createRoom
 */
                String query = "UPDATE `user_rights` " +
                        "set " +
                        "newUser=?, " +
                        "listReserves=?, " +
                        "createPartner=?, " +
                        "changeRoomName=?, " +
                        "changeRoomImages=?, " +
                        "changeRoomNum=?, " +
                        "createRoom=? " +
                        "WHERE " +
                        "user_id= ?";

                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setBoolean(1, user.getUserRight().isCreateUser());
                preparedStmt.setBoolean(2, user.getUserRight().isReserveList());
                preparedStmt.setBoolean(3, user.getUserRight().isCreatePartner());
                preparedStmt.setBoolean(4, user.getUserRight().isChangeRoomName());
                preparedStmt.setBoolean(5, user.getUserRight().isChangeRoomImages());
                preparedStmt.setBoolean(6, user.getUserRight().isChangeRoomNum());
                preparedStmt.setBoolean(7, user.getUserRight().isCreateRoom());
                preparedStmt.setInt(8,user.getUserId());


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
