package model;

import java.util.Date;

public class Information {

    private String message;
    private boolean visible;
    private int id;

    private final boolean archived;

    private final Date archived_at;

    public Information(int id,String message, boolean visible,boolean archived,Date archived_at) {

        this.message = message;
        this.visible = visible;
        this.id = id;
        this.archived=archived;

        this.archived_at=archived_at;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isArchived() {
        return archived;
    }

    public Date getArchived_at() {
        return archived_at;
    }

}
