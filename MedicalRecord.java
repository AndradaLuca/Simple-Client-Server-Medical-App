




import java.util.ArrayList;

public class MedicalRecord {
    private String insurance;
    private String treatment;
    private ArrayList<Disease> patientDiseases = new ArrayList<>();

    public MedicalRecord() {
        this.insurance = "";
        this.treatment = "";
    }

    public MedicalRecord(String insurance, String treatment) {
        this.insurance = insurance;
        this.treatment = treatment;
    }

    public void addDiseaseToMedicalRecord(Disease disease) {
        this.patientDiseases.add(disease);
    }

    public void printMedicalRecord() {
        System.out.print("Asigurare: " + this.insurance + " | Tratament: " + this.treatment + "\n" + "Boli: ");
        for (int i = 0; i < patientDiseases.size(); i++) {
            System.out.print(patientDiseases.get(i).getName() + " ");
        }
        System.out.println();
    }
}
