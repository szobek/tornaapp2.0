package model;

public class Partner extends People{
    public Partner(String phone, String firstName, String lastName, String email, int userId, UserRight userRight) {
        super(phone, firstName, lastName, email, userId, userRight);
    }

    public static Partner createEmptyPartner(){
        return new Partner("",
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
