package db;

import hash.PasswordHash;
import model.Partner;
import model.UserRight;

import java.sql.*;
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
                        "users.id," +
                        "users.email,phone," +
                        "first_name," +
                        "last_name " +
                        "from user_data " +
                        "inner join users " +
                        "on users.id=user_data.user_id " +
                        "where " +
                        "users.deleted=0 " +
                        "and " +
                        "user_or_partner=1";
                PreparedStatement preparedStmt = con.prepareStatement(query);
                ResultSet rs = preparedStmt.executeQuery(query);
                while (rs.next()) {
                    partners.add(new Partner(
                            rs.getString("phone"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getInt("id"),
                            new UserRight(false,
                                    false,
                                    false,
                                    false,
                                    false,
                                    false,
                                    false)
                        ));
                }



                con.close();
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

    public static boolean saveNewPartnerInDb(Partner partner) {
        Connection con;
        boolean success = false;
        try {
            con = DBHandler.connectToDb();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        ResultSet rs;
        long id = 0;
        if (con != null) {
            try {
                String psw = PasswordHash.hashing("CTf23");
                String query = "INSERT INTO `users` VALUES (NULL, '" + partner.getEmail() + "', '" + psw + "', NULL, NULL, NULL, '0');";
                Statement stmt = con.createStatement();

                stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getLong(1);
                }

                query = "insert into user_data  values (null," + id + ",'"
                        + partner.getFirstName() + "','" + partner.getLastName() + "','" + partner.getPhone() + "','1')";
                stmt = con.createStatement();
                stmt.executeUpdate(query);

                query = "insert into user_rights (id,user_id,newuser,listreserves) values(null," + id + ",0,0)";
                stmt = con.createStatement();
                stmt.executeUpdate(query);

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
