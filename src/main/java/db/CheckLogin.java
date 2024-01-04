package db;

import model.User;
import model.UserRight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckLogin {

    public static User checkLogin(String email, String password) {
        User user = null;
        Connection con;
        try {
            con = DBHandler.connectToDb();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        String query;
        if (con != null) {
            try {
                query = "select user_rights.createpartner, " +
                        "users.id," +
                        "users.email," +
                        "phone," +
                        "first_name," +
                        "last_name," +
                        "user_rights.newuser," +
                        "user_rights.listreserves, " +
                        "user_rights.changeRoomName, " +
                        "user_rights.changeRoomImages, " +
                        "user_rights.changeRoomNum, " +
                        "user_rights.createRoom "+
                        "from users " +
                        "inner join user_data " +
                        "on users.id=user_data.user_id " +
                        "inner join user_rights " +
                        "on user_rights.user_id=users.id " +
                        "where " +
                        "email=? " +
                        "and " +
                        "password=?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, email);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {

                    user = new User(
                            rs.getString("phone"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getInt("id"),
                                new UserRight(
                                        rs.getBoolean("listreserves"),
                                        rs.getBoolean("newuser"),
                                        rs.getBoolean("createpartner"),
                                        rs.getBoolean("changeRoomName"),
                                        rs.getBoolean("changeRoomImages"),
                                        rs.getBoolean("changeRoomNum"),
                                        rs.getBoolean("createRoom")
                                )
                    );
                    con.close();
                } else {
                    System.err.println("hiba a loginnal");
                }
                con.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("hiba...");
        }
        return user;
    }

}
