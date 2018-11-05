package br.com.santanderteste.model;

/**
 * @author JhonnyBarbosa
 * @version 1.0
 */
public class Statement {

    private String title;
    private String desc;
    private String date;
    private String value;

    public Statement(String title, String desc, String date, String value) {
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
