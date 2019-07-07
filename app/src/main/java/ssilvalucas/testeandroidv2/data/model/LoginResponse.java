package ssilvalucas.testeandroidv2.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class LoginResponse implements Parcelable {

    @SerializedName("userAccount")
    private UserAccount userAccount;

    @SerializedName("error")
    private Error error;

    protected LoginResponse(Parcel parcel) {
        this.userAccount = parcel.readParcelable(UserAccount.class.getClassLoader());
        this.error       = parcel.readParcelable(Error.class.getClassLoader());
    }

    public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(userAccount, flags);
        dest.writeParcelable(error, flags);
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public Error getError() {
        return error;
    }
}
