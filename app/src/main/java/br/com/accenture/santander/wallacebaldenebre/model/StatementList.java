package br.com.accenture.santander.wallacebaldenebre.model;

public class StatementList {
    private String date;
    private String desc;
    private String title;
    private Long value;

    public StatementList(String title, String date, String desc, Long value){
        this.title = title;
        this.date = date;
        this.desc = desc;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

}
