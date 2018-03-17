

public class Review {
    private int id;
    private String text;
    private int idDoctor;

    public Review() {
        this.id = 0;
        this.text = "";
        this.idDoctor = 0;
    }

    public Review(int id, int idDoctor, String text) {
        this.id = id;
        this.text = text;
        this.idDoctor = idDoctor;
    }
}
