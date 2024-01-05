package model;

public class User extends People {
    public User(String phone, String firstName, String lastName, String email, int userId, UserRight userRight) {
        super(phone, firstName, lastName, email, userId, userRight);
    }

    @Override
    public String toString() {
        return super.getUserName()+", "+super.getEmail();
    }

    public static User createEmptyUser(){
        return new User("",
                "",
                "",
                "",
                -1,
                new UserRight(
                        false,
                        false,
                        false,
                        false,
                        false,
                        false,
                        false
                ));
    }

}
