package model;

import java.sql.Timestamp;

public class Reserve {
    private final Timestamp from;
    private final Timestamp to;
    private final int id;
    private final int userId;

    private final int roomId;

    public Reserve(int id,int roomId,int userId,Timestamp from, Timestamp to) {
        this.from = from;
        this.to = to;
        this.id=id;
        this.userId=userId;
        this.roomId=roomId;
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
}
