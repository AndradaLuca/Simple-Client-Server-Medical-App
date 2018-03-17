

import java.sql.*;

public class ReviewController {

    public void getReviews(Statement statement) {
        try {
            String selectString = "select id_doctor,rating from reviews";
            //System.out.println("The SQL query is: " + strSelect);
            ResultSet result = statement.executeQuery(selectString);

            //System.out.println("The records selected are:");
            int rowCount = 0;
            while (result.next()) {   // Move the cursor to the next row, return false if no more row
                int idDoctor = result.getInt("id_doctor");
                String review = result.getString("rating");
                System.out.println(idDoctor + ", " + review);
                ++rowCount;
            }
            System.out.println();
            //System.out.println("Total number of records = " + rowCount);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
