package model;

public class User extends People {
    public User(String phone, String firstName, String lastName, String email, int userId, UserRight userRight) {
        super(phone, firstName, lastName, email, userId, userRight);
    }

}
