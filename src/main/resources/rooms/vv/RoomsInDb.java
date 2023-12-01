package db;

import model.Room;

import java.sql.*;
import java.util.ArrayList;

public class RoomsInDb {


    public static ArrayList<Room> getAllRooms() {
        Connection con;
        try {
            con = DBHandler.connectToDb();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        String query;
        ArrayList<Room> rooms = new ArrayList<>();
        if (con != null) {
            try {
                query = "select * from rooms";
                PreparedStatement stmt = con.prepareStatement(query);

                ResultSet rs = stmt.executeQuery();
                while (rs.next())
                    rooms.add(new Room(rs.getInt("id"),rs.getString("num"), rs.getString("name"), rs.getString("image_path") ));
                con.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("hiba... a con null");
        }
        return rooms;
    }

    public static ArrayList<Room> getFreeRooms(Timestamp fromDate, Timestamp toDate) {
        Connection con;
        try {
            con = DBHandler.connectToDb();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        String query;
        ArrayList<Room> rooms = new ArrayList<>();
        if (con != null) {
            try {
                // SELECT * FROM `rooms`WHERE id NOT IN ('2','3');
                // hibás: SELECT * FROM rooms WHERE id NOT IN (SELECT id FROM reserve WHERE room_id =2);
                // alakul: SELECT * FROM rooms where id not in (SELECT room_id FROM reserve );
                // SELECT * FROM rooms where id not in (SELECT room_id FROM reserve WHERE fromTime<>'2023-11-08 06:00:00' and toTime<>'2023-11-06 00:00:00 ');
                // siker: SELECT * FROM rooms where id not in (SELECT room_id FROM `reserve` WHERE fromTime='2023-11-08 06:00:00' and toTime='2023-11-06 00:00:00');
                query = "SELECT * FROM rooms where id not in (SELECT room_id FROM `reserve` WHERE fromTime=? and toTime=?)";
                PreparedStatement stmt = con.prepareStatement(query);

                stmt.setTimestamp(1,fromDate);
                stmt.setTimestamp(2,toDate);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()){
                    rooms.add(new Room(rs.getInt("id"),rs.getString("num"), rs.getString("name"), rs.getString("image_path")));
                }

                con.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.err.println("hiba...");
        }
        return rooms;
    }
}
