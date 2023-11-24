package db;
import db.DBHandler;
import model.Reserve;
import model.User;
import model.UserRight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class  ReservesFromDB {
    public static ArrayList<Reserve> getAllReserves(){
        Connection con;
        try {
            con = DBHandler.connectToDb();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        String query ;
        ArrayList<Reserve> reserves = new ArrayList<>();
        if (con != null) {
            try {
                query = "select * from reserve";
                PreparedStatement stmt = con.prepareStatement(query);

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) reserves.add(new Reserve(rs.getInt("id"),rs.getTimestamp("from"),rs.getTimestamp("to")));

                con.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("hiba...");
        }
        return reserves;
    }
}
