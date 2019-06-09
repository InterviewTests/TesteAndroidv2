package com.br.projetotestesantanter.refactor.statementScreen;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StatementModel {


    public class ListStatemt{

        @SerializedName("statementList")
        private ArrayList<Statement> statementArrayList;

        public ArrayList<Statement> getStatementArrayList() {
            return statementArrayList;
        }
    }


    public class Statement {


        @SerializedName("title")
        private String title;

        @SerializedName("desc")
        private String description;

        @SerializedName("date")
        private String date;

        @SerializedName("value")
        private float value;

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public String getDate() {
            return date;
        }

        public float getValue() {
            return value;
        }
    }
}
