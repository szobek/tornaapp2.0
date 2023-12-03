package model;

public class Room {
    private int id;
    private String num;
    private String name;

    private String imagePath;

    public Room(int id, String num, String name, String imagePath) {
        this.id = id;
        this.num = num;
        this.name = name;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public String getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", num='" + num + '\'' +
                ", name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
