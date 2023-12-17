package model;

import java.sql.Timestamp;

public record Reserve(int id, int roomId, int userId, Timestamp from, Timestamp to, String userName, String roomName,
                      String roomNum) {

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

}
