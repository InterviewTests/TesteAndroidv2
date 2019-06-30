package com.example.androidcodingtest.models;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatmentsResponse implements Parcelable
{

    @SerializedName("statementList")
    @Expose
    private List<Statement> statementList = null;
    @SerializedName("error")
    @Expose
    private Error error;
    public final static Parcelable.Creator<StatmentsResponse> CREATOR = new Creator<StatmentsResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StatmentsResponse createFromParcel(Parcel in) {
            return new StatmentsResponse(in);
        }

        public StatmentsResponse[] newArray(int size) {
            return (new StatmentsResponse[size]);
        }

    }
            ;

    protected StatmentsResponse(Parcel in) {
        in.readList(this.statementList, (com.example.androidcodingtest.models.Statement.class.getClassLoader()));
        this.error = ((Error) in.readValue((Error.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public StatmentsResponse() {
    }

    /**
     *
     * @param statementList
     * @param error
     */
    public StatmentsResponse(List<Statement> statementList, Error error) {
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