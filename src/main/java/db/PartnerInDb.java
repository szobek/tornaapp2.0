package db;

import model.Partner;
import model.User;
import model.UserRight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PartnerInDb {
    public static ArrayList<Partner> getAllFromDB() {

        Connection con;
        try {
            con = DBHandler.connectToDb();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ArrayList<Partner> partners = new ArrayList<>();
        if (con != null) {
            try {
                String query = "select " +
                        "users.id,users.email,phone,first_name,last_name,user_rights.newuser,user_rights.listreserves " +
                        "from user_data " +
                        "inner join users " +
                        "on users.id=user_data.user_id " +
                        "inner join user_rights " +
                        "on user_rights.user_id=users.id " +
                        "where users.deleted=0 and user_or_partner=1";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                ResultSet rs = preparedStmt.executeQuery(query);
                while (rs.next()) {
                    partners.add(new Partner(
                            rs.getString("phone"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getInt("id"),
                            new UserRight(
                                    rs.getBoolean("listreserves"),
                                    rs.getBoolean("newuser"))));
                }




            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("hiba...");
        }
        return partners;
    }

    public static boolean updatePartnerData(Partner partner) {
        System.out.println("update előtt: "+partner.toString());
        Connection con;
        boolean success = false;
        try {
            con = DBHandler.connectToDb();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        if (con != null) {
            try {

                String query = "UPDATE " +
                        "`user_data` " +
                        "inner join users " +
                        "on users.id=user_data.user_id SET " +
                        "`first_name` = ?, " +
                        "`last_name`=?," +
                        "`phone`=? " +
                        "WHERE users.email = ?";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1, partner.getFirstName());
                preparedStmt.setString(2, partner.getLastName());
                preparedStmt.setString(3, partner.getPhone());
                preparedStmt.setString(4, partner.getEmail());
                //

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
