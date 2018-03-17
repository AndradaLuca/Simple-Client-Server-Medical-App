


import java.sql.*;
import java.util.ArrayList;

public class UserController {
    private ArrayList<User> userArray = new ArrayList<>();

    public void printUserArray() {
        for (int i = 0; i < userArray.size(); i++) {
            System.out.println(userArray.get(i).showUserDetails());
        }
        System.out.println();
    }

    public void getUsers(Statement statement) {
        try {
            String selectString = "select id,type,email,password from user";
            //System.out.println("The SQL query is: " + strSelect);
            ResultSet result = statement.executeQuery(selectString);

            //System.out.println("The records selected are:");
            int rowCount = 0;
            while (result.next()) {
                int id = result.getInt("id");
                String email = result.getString("text");
                String password = result.getString("password");
                String type = result.getString("type");
                User user = new User(id, type, email, password);
                userArray.add(user);
                printUserArray();
                ++rowCount;
            }
            //System.out.println("Total number of records = " + rowCount);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
