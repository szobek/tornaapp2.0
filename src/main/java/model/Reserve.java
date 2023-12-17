package model;

import java.sql.Timestamp;

public class Reserve {
    private final Timestamp from;
    private final Timestamp to;
    private final int id;
    private final int userId;

    private final String userName;
    private final int roomId;
    private final String roomName;
    private final String roomNum;

    public Reserve(int id, int roomId, int userId, Timestamp from, Timestamp to, String userName, String roomName, String roomNum) {
        this.from = from;
        this.to = to;
        this.id = id;
        this.userId = userId;
        this.roomId = roomId;
        this.userName = userName;
        this.roomName = roomName;
        this.roomNum = roomNum;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "from=" + from +
                ", to=" + to +
                ", id=" + id +
                ", userId=" + userId +
                ", roomId=" + roomId +
                '}';
    }

    public Timestamp getFrom() {
        return from;
    }

    public Timestamp getTo() {
        return to;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getUserName() {
        return userName;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomNum() {
        return roomNum;
    }


}
