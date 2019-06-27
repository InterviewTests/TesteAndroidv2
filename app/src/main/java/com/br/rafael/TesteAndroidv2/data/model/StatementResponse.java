package com.br.rafael.TesteAndroidv2.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StatementResponse implements Parcelable {

    @SerializedName("statementList")
    private ArrayList<Statement> statementArrayList;

    public StatementResponse() {

    }

    protected StatementResponse(Parcel in) {
        statementArrayList = in.createTypedArrayList(Statement.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(statementArrayList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StatementResponse> CREATOR = new Creator<StatementResponse>() {
        @Override
        public StatementResponse createFromParcel(Parcel in) {
            return new StatementResponse(in);
        }

        @Override
        public StatementResponse[] newArray(int size) {
            return new StatementResponse[size];
        }
    };

    public ArrayList<Statement> getStatementArrayList() {
        return statementArrayList;
    }
}
