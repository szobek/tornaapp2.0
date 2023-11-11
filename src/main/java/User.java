public class User {
    private String phone;
    private String firstName;
    private String lastName;
    private String email;
    private int userId;
    private UserRight userRight;


    public User(String phone, String firstName, String lastName,String email,int userId,UserRight userRight) {
        super();
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email=email;
        this.userId=userId;
        this.userRight=userRight;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ", telefon: " + phone;
    }

    public String getUserName() {
        return firstName + " " + lastName ;
    }


    public int getUserId() {
        return userId;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserRight getUserRight() {
        return userRight;
    }

    public void setUserRight(UserRight userRight) {
        this.userRight = userRight;
    }

}
