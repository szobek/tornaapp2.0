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
                query = "select * from rooms where deleted=0";
                PreparedStatement stmt = con.prepareStatement(query);

                ResultSet rs = stmt.executeQuery();
                while (rs.next())
                    rooms.add(new Room(rs.getInt("id"),
                            rs.getString("num"),
                            rs.getString("name"),
                            rs.getString("image_path") ));
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
                query = "SELECT * FROM rooms where id not in (SELECT room_id FROM `reserve` WHERE fromTime>=? and toTime<=?)and deleted=0";
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

    public static boolean updateRoomData(Room room){
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

                String query = "UPDATE `rooms` " +
                        "SET  rooms.name=?," +
                        "  rooms.num=?," +
                        "  rooms.image_path=?" +
                        "WHERE rooms.id = ?";

                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1,room.getName());
                preparedStmt.setString(2,room.getNum());
                preparedStmt.setString(3,room.getImagePath());
                preparedStmt.setInt(4,room.getId());


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

    public static boolean createRoom(Room room){
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

                String query = "INSERT INTO `rooms` (`id`, `num`, `name`, `image_path`) VALUES (NULL, ?, ?, ?)";

                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setString(1,room.getNum());
                preparedStmt.setString(2,room.getName());
                preparedStmt.setString(3,room.getImagePath());


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

    public static boolean deleteRoomById(Room room){
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

                String query = "UPDATE `rooms` SET deleted=1 where id = ?";

                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setInt(1,room.getId());


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
