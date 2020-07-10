package com.accenture.android.app.testeandroid.data.http.responses;

import com.accenture.android.app.testeandroid.data.http.responses.generics.ErrorResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class StatementResponse {
    @SerializedName("statementList")
    private List<StatementData> data;
    private ErrorResponse error;

    public class StatementData {
        private String title;
        private String desc;
        private String date;
        private Double value;

        public String getTitle() {
            return title;
        }

        public String getDesc() {
            return desc;
        }

        public String getDate() {
            return date;
        }

        public Double getValue() {
            return value;
        }
    }

    public List<StatementData> getData() {
        return data;
    }

    public ErrorResponse getError() {
        return error;
    }
}