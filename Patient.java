import java.io.Serializable;

public class Patient implements Serializable{
    private int idPatient;
    private String namePatient;
    private String phonePatient;
    private String localityPatient;
    private String emailPatient;
    private String passwordPatient;
    private int agePatient;

    public Patient() {
    }

    public Patient(int id, String name, String phone, String address, int age, String email, String password) {
        this.idPatient = id;
        this.namePatient = name;
        this.phonePatient = phone;
        this.localityPatient = address;
        this.agePatient = age;
        this.emailPatient = email;
        this.passwordPatient = password;
    }

    public int getIdPatient() {
        return this.idPatient;
    }

    public int getAgePatient() {
        return this.agePatient;
    }

    public String getNamePatient() {
        return this.namePatient;
    }

    public String getPhonePatient() {
        return this.phonePatient;
    }

    public String getLocalityPatient() {
        return this.localityPatient;
    }

    public String getEmailPatient() {
        return this.emailPatient;
    }

    public String getPasswordPatient() {
        return this.passwordPatient;
    }

    public String showPatientDetails() {
        return "Nume: " + this.namePatient + " | Localitate: " + this.localityPatient + " | Varsta: " + this.agePatient + " | Nr.telefon: " + this.phonePatient;
    }

}
