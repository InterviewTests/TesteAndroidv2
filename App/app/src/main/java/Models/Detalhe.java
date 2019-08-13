package Models;

public class Detalhe {
    private String title;
    private String desc;
    private String date;
    private double value;

    public Detalhe(String title, String desc, String date, double value){
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }
}
