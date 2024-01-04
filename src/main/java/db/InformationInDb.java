package db;


import enums.Success;
import model.Information;

import java.sql.*;
import java.time.LocalDate;
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
                    informations.add(
                            new Information(
                                    rs.getInt("id"),
                                    rs.getString("message"),
                                    rs.getBoolean("visible"),
                               rs.getBoolean("archived"),
                                    rs.getDate("archived_at")
                            )
                    );

                con.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("hiba...");
        }
        return informations;
    }

    public static void update(Information information){
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
                String query = "UPDATE informations SET message=?, visible=? WHERE id = ?";

                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1,information.getMessage());
                preparedStmt.setBoolean(2,information.isVisible());
                preparedStmt.setInt(3,information.getId());
                preparedStmt.executeUpdate();

                con.close();
                success=true;
                Success.UPDATEINFORMATION.setSuc(true);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("hiba...");
        }
        System.out.println("az update: "+success);
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


                String query = "UPDATE informations SET visible=0,  archived=1,archived_at=? WHERE id = ?";

                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setDate(1, Date.valueOf(LocalDate.now()));

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

    public static void create(Information information){
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
//  id 	message 	visible 	archived 	archived_at
                String query = "insert into informations " +
                        "values(  " +
                        "null,?,?,0,null)";

                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1, information.getMessage());
                preparedStmt.setBoolean(2, information.isVisible());


                preparedStmt.executeUpdate();

                con.close();
                success=true;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("hiba...");
        }
    }
}
