



import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PatientController {
    private ArrayList<Patient> patientArray = new ArrayList<>();
    // private HashMap<Integer, MedicalRecord> medicalRecords=new HashMap<>();
    private ArrayList<Patient> patientsWithAccounts = new ArrayList<>();
    private int lastUserId = 6;
    private int lastPatientId = 7;

    public void printPatientArray() {
        for (int i = 0; i < patientArray.size(); i++) {
            System.out.println(patientArray.get(i).showPatientDetails());
        }
        System.out.println();
    }




    /* public void printMedicalRecords(){
         for (Integer name: this.medicalRecords.keySet()){
             String key =name.toString();
             System.out.println("Id Pacient: "+key + " " );
             this.medicalRecords.get(name).printMedRec();
         }
         System.out.println();
     }
 */
    public void addNewPatient(Statement statement, Patient patient) {
        try {
            ++lastPatientId;
            String[] name = patient.getNamePatient().split("\\s+");
            ++lastUserId;
            //insert patient info in PATIENT table
            String sqlInsertIntoPatientTable = "insert into patient "
                    + "values (" + lastPatientId + ",'" + name[0] + "','" + name[1] + "','" + patient.getPhonePatient() + "','" + patient.getLocalityPatient() + "'," + lastUserId + "," + patient.getAgePatient() + ")";
            System.out.println("The SQL query is: " + sqlInsertIntoPatientTable);
            int countInsertedPatient = statement.executeUpdate(sqlInsertIntoPatientTable);
            System.out.println(countInsertedPatient + " records inserted into Patient table.");

            //insert patient info in USER table
            String sqlInsertIntoUserTable = "insert into user "
                    + "values (" + lastUserId + "," + "'patient'" + ",'" + patient.getEmailPatient() + "','" + patient.getPasswordPatient() + "')";
            System.out.println("The SQL query is: " + sqlInsertIntoUserTable);
            int countInsertedUser = statement.executeUpdate(sqlInsertIntoUserTable);
            System.out.println(countInsertedUser + " records inserted into User table.\n");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Patient> getPatients(Statement statement) {
        try {
            String selectString = "select * from patient";
            //System.out.println("The SQL query is: " + selectString);
            ResultSet result = statement.executeQuery(selectString);

            // System.out.println("The records selected are:");
            int rowCount = 0;
            while (result.next()) {   // Move the cursor to the next row, return false if no more row
                int id = result.getInt("id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String name = firstName + " " + lastName;
                String phone = result.getString("phone");
                String address = result.getString("address");
                int age = result.getInt("age");
                Patient patient = new Patient(id, name, phone, address, age, "", "");
                patientArray.add(patient);
                ++rowCount;
            }
            //System.out.println("Total number of records = " + rowCount);
           // printPatientArray();
            result.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this.patientArray;
    }

    public void getPatientsWithAccount(Statement statement) {
        try {
            String selectString = "select patient.*,user.email,user.password " +
                    "from patient, user " +
                    "where patient.id_user = user.id";
            //System.out.println("The SQL query is: " + selectString);
            ResultSet result = statement.executeQuery(selectString);

            //System.out.println("The records selected are:");
            int rowCount = 0;
            while (result.next()) {
                int id = result.getInt("patient.id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String name = firstName + " " + lastName;
                String phone = result.getString("phone");
                String address = result.getString("address");
                String email = result.getString("email");
                String password = result.getString("password");
                int age = result.getInt("age");
                Patient patient = new Patient(id, name, phone, address, age, email, password);
                patientsWithAccounts.add(patient);
                ++rowCount;
            }
            //System.out.println("Total number of records = " + rowCount);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void getMedicalRecords(Statement statement) {
        try {
            String selectString = ("SELECT patient.id,medical_record.treatment,medical_record.insurance,diseases.name " +
                    "FROM (patient " +
                    "LEFT OUTER JOIN medical_record ON (patient.id = medical_record.id_patient)) " +
                    "JOIN diseases ON (patient.id = diseases.id_patient)");

            //System.out.println("The SQL query is: " + selectString);
            ResultSet result = statement.executeQuery(selectString);

            //System.out.println("The records selected are:");
            int rowCount = 0;
            while (result.next()) {
                int id = result.getInt("patient.id");
                String treatment = result.getString("treatment");
                String insurance = result.getString("insurance");
                String diseaseName = result.getString("name");
                MedicalRecord patientMedicalRecord = new MedicalRecord(insurance, treatment);
                Disease patientDisease = new Disease(diseaseName);
                patientMedicalRecord.addDiseaseToMedicalRecord(patientDisease);
                System.out.println("Id pacient: " + id);
                patientMedicalRecord.printMedicalRecord();
                System.out.println();
                //System.out.println(id+" | "+treatment+" | "+insurance+" | "+diseaseName);
                ++rowCount;
            }
            //System.out.println("Total number of records = " + rowCount);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
