
public class User {
    private int id;
    private String type;
    private String email;
    private String password;

    public User() {
        this.id = 0;
        this.type = "";
        this.email = "";
        this.password = "";
    }

    public User(int userId, String userType, String username, String password) {
        this.id = userId;
        this.type = userType;
        this.email = username;
        this.password = password;
    }

    public String showUserDetails() {
        return "Id: " + this.id + " | E-mail: " + this.email + " | Type: " + this.type;
    }

}
