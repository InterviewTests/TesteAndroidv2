package ssilvalucas.testeandroidv2.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Error implements Parcelable {

    @SerializedName("code")
    private long code;

    @SerializedName("message")
    private String message;


    protected Error(Parcel parcel) {
        this.code = parcel.readInt();
        this.message = parcel.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(code);
        dest.writeString(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static final Creator<Error> CREATOR = new Creator<Error>() {
        @Override
        public Error createFromParcel(Parcel in) {
            return new Error(in);
        }

        @Override
        public Error[] newArray(int size) {
            return new Error[size];
        }
    };
}
