import java.io.Serializable;

public class Doctor  implements Serializable{
    private int idDoctor;
    private String nameDoctor;
    private String phoneDoctor;
    private String localityDoctor;
    private String typeDoctor;
    private int ageDoctor;
    private int priceConsultationDoctor;
    private int experienceAgeDoctor;
    private String emailDoctor;
    private String passwordDoctor;

    public Doctor() {
    }

    public Doctor(int id, String name, int age, String phone, String address, String specialisation, int experience, int price, String email, String password) {
        this.idDoctor = id;
        this.nameDoctor = name;
        this.phoneDoctor = phone;
        this.ageDoctor = age;
        this.localityDoctor = address;
        this.typeDoctor = specialisation;
        this.priceConsultationDoctor = price;
        this.experienceAgeDoctor = experience;
        this.emailDoctor = email;
        this.passwordDoctor = password;
    }

    public int getIdDoctor() {
        return this.idDoctor;
    }

    public int getAgeDoctor() {
        return this.ageDoctor;
    }

    public int getExperienceAgeDoctor() {
        return this.experienceAgeDoctor;
    }

    public int getPriceConsultationDoctor() {
        return this.priceConsultationDoctor;
    }

    public String getNameDoctor() {
        return this.nameDoctor;
    }

    public String getPhoneDoctor() {
        return this.phoneDoctor;
    }

    public String getEmailDoctor() {
        return this.emailDoctor;
    }

    public String getTypeDoctor() {
        return this.typeDoctor;
    }

    public String getLocalityDoctor() {
        return this.localityDoctor;
    }

    public String getPasswordDoctor() {
        return this.passwordDoctor;
    }

    public String showDoctorDetails() {
        return "Nume: " + this.nameDoctor + " | Localitate: " + this.localityDoctor + " | Varsta: " + this.ageDoctor + " | Specializare: " + this.typeDoctor +
                " | Ani de experienta: " + this.experienceAgeDoctor + " ani" + " | Nr.telefon: " + this.phoneDoctor + " | Pret consultatie: " + this.priceConsultationDoctor + " RON";
    }
}
