



import java.sql.*;
import java.util.ArrayList;

public class DoctorController {
    private ArrayList<Doctor> doctorArray = new ArrayList<>();
    private ArrayList<Doctor> doctorsAccountsArray = new ArrayList<>();

    public void printDoctorArray() {
        for (int i = 0; i < doctorArray.size(); i++) {
            System.out.println(doctorArray.get(i).showDoctorDetails());
        }
        System.out.println();
    }

    public ArrayList<Doctor> getDoctorsAccountsArray() {
        return this.doctorsAccountsArray;
    }

    public ArrayList<Doctor> getDoctorArray() {
        return this.doctorArray;
    }

    public ArrayList<Doctor> getDoctors(Statement statement) {
        try {
            String selectString = "select id,first_name,last_name,phone,address,specialisation,age,experience,price from doctor";
            // System.out.println("The SQL query is: " + strSelect);
            ResultSet result = statement.executeQuery(selectString);

            //System.out.println("The records selected are:");
            int rowCount = 0; //number of records
            while (result.next()) {
                int id = result.getInt("id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String name = firstName + " " + lastName;
                String phone = result.getString("phone");
                String address = result.getString("address");
                String type = result.getString("specialisation");
                int age = result.getInt("age");
                int experience = result.getInt("experience");
                int price = result.getInt("price");
                Doctor doctor = new Doctor(id, name, age, phone, address, type, experience, price, "", "");
                //System.out.println(doctor.showDoctorDetails());
                doctorArray.add(doctor);
                ++rowCount;
            }
            //System.out.println("Total number of records = " + rowCount);
           // printDoctorArray();
            result.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return this.doctorArray;
    }

    public void getDoctorsWithAccount(Statement statement) {
        try {
            String selectString = "select doctor.*,user.email,user.password " +
                    "from doctor, user " +
                    "where doctor.id_user = user.id";
            //System.out.println("The SQL query is: " + selectString);
            ResultSet result = statement.executeQuery(selectString);

            //System.out.println("The records selected are:");
            int rowCount = 0;
            while (result.next()) {
                int id = result.getInt("doctor.id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String name = firstName + " " + lastName;
                String phone = result.getString("phone");
                String address = result.getString("address");
                String type = result.getString("specialisation");
                int age = result.getInt("age");
                int experience = result.getInt("experience");
                int price = result.getInt("price");
                String email = result.getString("email");
                String password = result.getString("password");
                Doctor doctor = new Doctor(id, name, age, phone, address, type, experience, price, email, password);
                doctorsAccountsArray.add(doctor);
                ++rowCount;
            }
            //System.out.println("Total number of records = " + rowCount);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void getReviewsForEachDoctor(Statement statement) {
        try {
            String selectString = "select doctor.first_name, doctor.last_name, reviews.rating " +
                    "from doctor, reviews " +
                    "where doctor.id = reviews.id_doctor";
            //System.out.println("The SQL query is: " + selectString);
            ResultSet result = statement.executeQuery(selectString);

            //System.out.println("The records selected are:");
            int rowCount = 0;
            while (result.next()) {
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String text = result.getString("rating");
                System.out.println(firstName + " " + lastName + " " + text);
                ++rowCount;
            }
            System.out.println();
            //System.out.println("Total number of records = " + rowCount);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}


