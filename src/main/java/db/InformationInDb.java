package db;


import model.Information;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InformationInDb {
    public static ArrayList<Information> getAllInformation() {
        Connection con;
        try {
            con = DBHandler.connectToDb();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        String query;
        ArrayList<Information> informations = new ArrayList<>();
        if (con != null) {
            try {
                query = "select * from informations";
                PreparedStatement stmt = con.prepareStatement(query);

                ResultSet rs = stmt.executeQuery();
                while (rs.next())
                    informations.add(new Information(rs.getInt("id"), rs.getString("message"),rs.getBoolean("visible")));

                con.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("hiba...");
        }
        return informations;
    }

    public static boolean update(Information information){
        Connection con;
        try {
            con = DBHandler.connectToDb();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        boolean success=false;
        if (con != null) {
            try {

                String query = "UPDATE informations " +
                        "SET  " +
                        "  informations.message=?," +
                        "  informations.visible=?" +
                        "WHERE informations.id = ?";

                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1,information.getMessage());
                preparedStmt.setBoolean(2,information.isVisible());
                preparedStmt.setInt(3,information.getId());


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

    public static boolean delete(Information information){
        Connection con;
        try {
            con = DBHandler.connectToDb();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        boolean success=false;
        if (con != null) {
            try {

                String query = "UPDATE informations " +
                        "SET  " +
                        "  informations.visible=?" +
                        "WHERE informations.id = ?";

                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setBoolean(1,false);
                preparedStmt.setInt(2,information.getId());


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

    public static boolean create(Information information){
        Connection con;
        try {
            con = DBHandler.connectToDb();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        boolean success=false;
        if (con != null) {
            try {

                String query = "insert into informations " +
                        "values(  " +
                        "null,?,1)";

                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1, information.getMessage());


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
