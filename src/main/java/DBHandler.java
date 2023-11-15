import java.sql.*;
import java.util.ArrayList;

public class DBHandler {
    private static Connection connectToDb() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tornaapp", "root", "");

        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public static User checkLogin(String email, String password) {
        User user=null;
        Connection con = null;
        try {
            con = connectToDb();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        String query ;
        if (con != null) {
            try {
                query = "select users.id,users.email,phone,first_name,last_name,user_rights.newuser,user_rights.listreserves from users inner join user_data on users.id=user_data.user_id inner join user_rights on user_rights.user_id=users.id where email=? and password=?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, email);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {

                    user = new User(rs.getString("phone"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"), rs.getInt("id"), new UserRight(rs.getInt("id"),rs.getBoolean("listreserves"),rs.getBoolean("newuser")));
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


    public static ArrayList<User> getAllFromDB() {

        Connection con = null;
        try {
            con = connectToDb();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ArrayList<User> users = new ArrayList<User>();
        if (con != null) {
            try {
                String query = "select users.id,users.email,phone,first_name,last_name,user_rights.newuser,user_rights.listreserves from user_data inner join users on users.id=user_data.user_id inner join user_rights on user_rights.user_id=users.id where users.deleted=0";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    users.add(new User(rs.getString("phone"), rs.getString("first_name"),
                            rs.getString("last_name"), rs.getString("email"),rs.getInt("id"),
                            new UserRight(rs.getInt("id"),rs.getBoolean("listreserves"),rs.getBoolean("newuser"))));
                }


            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("hiba...");
        }

        return users;
    }

    public static boolean updateUserData(User user) {
        Connection con = null;
        try {
            con = connectToDb();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        boolean success=false;
        if (con != null) {
            try {

                String query = "UPDATE `user_data` inner join users on users.id=user_data.user_id SET `first_name` = ?, `last_name`=?,`phone`=? WHERE users.email = ?";

                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1, user.getFirstName());
                preparedStmt.setString(2, user.getLastName());
                preparedStmt.setString(3, user.getPhone());
                preparedStmt.setString(4, user.getEmail());
                //

                preparedStmt.executeUpdate();

                con.close();
                success=true;

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("hiba...");
        }
        return success;
    }
}
