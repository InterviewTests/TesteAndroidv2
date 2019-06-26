package com.bilulo.androidtest04.data.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.bilulo.androidtest04.data.model.ErrorModel;
import com.bilulo.androidtest04.data.model.UserAccountModel;
import com.google.gson.annotations.SerializedName;

public class LoginResponse implements Parcelable {
    @SerializedName("userAccount")
    private UserAccountModel userAccountModel;
    @SerializedName("error")
    private ErrorModel errorModel;

    public UserAccountModel getUserAccountModel() {
        return userAccountModel;
    }

    public void setUserAccountModel(UserAccountModel userAccountModel) {
        this.userAccountModel = userAccountModel;
    }

    public ErrorModel getErrorModel() {
        return errorModel;
    }

    public void setErrorModel(ErrorModel errorModel) {
        this.errorModel = errorModel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.userAccountModel, flags);
        dest.writeParcelable(this.errorModel, flags);
    }

    public LoginResponse() {
    }

    protected LoginResponse(Parcel in) {
        this.userAccountModel = in.readParcelable(UserAccountModel.class.getClassLoader());
        this.errorModel = in.readParcelable(ErrorModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<LoginResponse> CREATOR = new Parcelable.Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel source) {
            return new LoginResponse(source);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };
}
