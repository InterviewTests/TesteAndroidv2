package com.example.androidcodingtest.models;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatementsResponse implements Parcelable
{

    @SerializedName("statementList")
    @Expose
    private List<Statement> statementList = null;
    @SerializedName("error")
    @Expose
    private Error error;
    public final static Parcelable.Creator<StatementsResponse> CREATOR = new Creator<StatementsResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StatementsResponse createFromParcel(Parcel in) {
            return new StatementsResponse(in);
        }

        public StatementsResponse[] newArray(int size) {
            return (new StatementsResponse[size]);
        }

    }
            ;

    protected StatementsResponse(Parcel in) {
        in.readList(this.statementList, (com.example.androidcodingtest.models.Statement.class.getClassLoader()));
        this.error = ((Error) in.readValue((Error.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public StatementsResponse() {
    }

    /**
     *
     * @param statementList
     * @param error
     */
    public StatementsResponse(List<Statement> statementList, Error error) {
        super();
        this.statementList = statementList;
        this.error = error;
    }

    public List<Statement> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<Statement> statementList) {
        this.statementList = statementList;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(statementList);
        dest.writeValue(error);
    }

    public int describeContents() {
        return 0;
    }

}