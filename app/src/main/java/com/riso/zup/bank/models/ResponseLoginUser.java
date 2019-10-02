package com.riso.zup.bank.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseLoginUser implements Parcelable {

    @SerializedName("userAccount")
    @Expose
    private UserInfo userInfo;

    @SerializedName("error")
    @Expose
    private ResponseError error;

    protected ResponseLoginUser(Parcel in) {
        this.userInfo = ((UserInfo) in.readValue((UserInfo.class.getClassLoader())));
        this.error = ((ResponseError) in.readValue((Error.class.getClassLoader())));
    }

    public static final Creator<ResponseLoginUser> CREATOR = new Creator<ResponseLoginUser>() {
        @Override
        public ResponseLoginUser createFromParcel(Parcel in) {
            return new ResponseLoginUser(in);
        }

        @Override
        public ResponseLoginUser[] newArray(int size) {
            return new ResponseLoginUser[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userInfo);
        dest.writeValue(error);
    }

    public ResponseLoginUser(){}

    public ResponseLoginUser(UserInfo userInfo, ResponseError error){
        super();
        this.userInfo = userInfo;
        this.error = error;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public ResponseError getError() {
        return error;
    }


}
