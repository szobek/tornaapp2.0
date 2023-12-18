package model;

public class Information {

    private String message;
    private boolean visible;
    private int id;

    public Information(int id,String message, boolean visible) {
        this.message = message;
        this.visible = visible;
        this.id = id;
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
}
