

public class Disease {
    private int id;
    private int idPatient;
    private String name;

    public Disease() {
        this.id = 0;
        this.idPatient = 0;
        this.name = "";
    }

    public Disease(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
