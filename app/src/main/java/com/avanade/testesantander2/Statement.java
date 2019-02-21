package com.avanade.testesantander2;

import com.google.gson.annotations.Expose;

public class Statement {

    @Expose
    private String title;

    @Expose
    private String desc;

    @Expose
    private String date;

    @Expose
    private double value;

    public Statement() {
    }

    public Statement(String title, String desc, String date, double value) {
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

    @Override
    public String toString() {
        return "Statement{" +
                "\n\ttitle='" + title + '\'' +
                "\n\t, desc='" + desc + '\'' +
                "\n\t, date='" + date + '\'' +
                "\n\t, value=" + value +
                '}';
    }
}

/*

{
    "statementList": [
        {
            "title": "Pagamento",
            "desc": "Conta de luz",
            "date": "2018-08-15",
            "value": -50
        },
        {
            "title": "TED Recebida",
            "desc": "Joao Alfredo",
            "date": "2018-07-25",
            "value": 745.03
        },
        {
            "title": "DOC Recebida",
            "desc": "Victor Silva",
            "date": "2018-06-23",
            "value": 399.9
        },
        {
            "title": "Pagamento",
            "desc": "Conta de internet",
            "date": "2018-05-12",
            "value": -73.4
        },
        {
            "title": "Pagamento",
            "desc": "Faculdade",
            "date": "2018-09-10",
            "value": -500
        },
        {
            "title": "Pagamento",
            "desc": "Conta de telefone",
            "date": "2018-10-17",
            "value": -760
        },
        {
            "title": "TED Enviada",
            "desc": "Roberto da Luz",
            "date": "2018-07-27",
            "value": -35.67
        },
        {
            "title": "Pagamento",
            "desc": "Boleto",
            "date": "2018-08-01",
            "value": -200
        },
        {
            "title": "TED Recebida",
            "desc": "Salário",
            "date": "2018-08-21",
            "value": 1400.5
        }
    ],
    "error": {}
}

*/
