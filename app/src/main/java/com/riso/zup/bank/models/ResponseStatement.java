package com.riso.zup.bank.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseStatement implements Parcelable {

    @SerializedName("statementList")
    @Expose
    private List<Statement> statementList = null;
    @SerializedName("error")
    @Expose
    private Error error;

    protected ResponseStatement(Parcel in) {
        in.readList(this.statementList, (Statement.class.getClassLoader()));
        this.error = ((Error) in.readValue((Error.class.getClassLoader())));
    }

    public static final Creator<ResponseStatement> CREATOR = new Creator<ResponseStatement>() {
        @Override
        public ResponseStatement createFromParcel(Parcel in) {
            return new ResponseStatement(in);
        }

        @Override
        public ResponseStatement[] newArray(int size) {
            return new ResponseStatement[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(statementList);
        parcel.writeValue(error);
    }

    public ResponseStatement(){}

    public ResponseStatement(List<Statement> statementList, Error error){
        super();
        this.statementList = statementList;
        this.error = error;

    }

    public List<Statement> getStatementList() {
        return statementList;
    }

    public Error getError() {
        return error;
    }
}
