package ssilvalucas.testeandroidv2.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class StatementHome implements Parcelable {

    @SerializedName("title")
    private String title;

    @SerializedName("desc")
    private String desc;

    @SerializedName("date")
    private String date;

    @SerializedName("value")
    private double value;

    private String formatedValue;
    private String formatedDate;

    protected StatementHome(Parcel parcel){
        title = parcel.readString();
        desc  = parcel.readString();
        date  = parcel.readString();
        value = parcel.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(desc);
        dest.writeString(date);
        dest.writeDouble(value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StatementHome> CREATOR = new Creator<StatementHome>() {
        @Override
        public StatementHome createFromParcel(Parcel in) {
            return new StatementHome(in);
        }

        @Override
        public StatementHome[] newArray(int size) {
            return new StatementHome[size];
        }
    };

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

    public String getFormatedValue() {
        return formatedValue;
    }

    public String getFormatedDate() {
        return formatedDate;
    }

    public void setFormatedValue(String formatedValue) {
        this.formatedValue = formatedValue;
    }

    public void setFormatedDate(String formatedDate) {
        this.formatedDate = formatedDate;
    }
}
