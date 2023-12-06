package db;

import model.Reserve;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReservesInDB {
    public static ArrayList<Reserve> getAllReserves() {
        Connection con;
        try {
            con = DBHandler.connectToDb();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        String query;
        ArrayList<Reserve> reserves = new ArrayList<>();
        if (con != null) {
            try {
                query = "select * from reserve inner join user_data on user_data.user_id=reserve.user_id inner join rooms on reserve.room_id=rooms.id";
                PreparedStatement stmt = con.prepareStatement(query);

                ResultSet rs = stmt.executeQuery();
                while (rs.next())
                    reserves.add(new Reserve(rs.getInt("id"),rs.getInt("room_id"), rs.getInt("user_id"),
                            rs.getTimestamp("fromTime"), rs.getTimestamp("toTime"),
                            rs.getString("first_name")+" "+rs.getString("last_name") ,
                            rs.getString("name"), rs.getString("num") ));

                con.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("hiba...");
        }
        return reserves;
    }

    public static boolean saveReserve(Reserve reserve) {
        Connection con;
        String query;
        boolean success=false;
        try {
            con = DBHandler.connectToDb();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }


        if (con != null) {
            try {
                query = "insert into reserve  values (null,?,?,?,?)";
                PreparedStatement stmt = con.prepareStatement(query);

                stmt.setInt(1,reserve.getUserId());
                stmt.setTimestamp(2,reserve.getFrom());
                stmt.setTimestamp(3,reserve.getTo());
                stmt.setInt(4,reserve.getRoomId());
                stmt.executeUpdate();
                con.close();
                success=true;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("hiba... con null");
        }
        return success;
    }
}
