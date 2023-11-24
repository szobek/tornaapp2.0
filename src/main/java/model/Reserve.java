package model;

import java.sql.Timestamp;

public class Reserve {
    private final Timestamp from;
    private final Timestamp to;
    private final int id;

    public Reserve(int id,Timestamp from, Timestamp to) {
        this.from = from;
        this.to = to;
        this.id=id;
    }

    @Override
    public String toString() {
        return "Reserve{" +
                "from=" + from +
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
}
