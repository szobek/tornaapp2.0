package model;

import java.sql.Timestamp;

public class Reserve {
    private final Timestamp from;
    private final Timestamp to;
    private final int id;
    private final int userId;

    public Reserve(int id,int userId,Timestamp from, Timestamp to) {
        this.from = from;
        this.to = to;
        this.id=id;
        this.userId=userId;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "user_id: "+userId+
                ", from=" + from +
                ", to=" + to +
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
}
