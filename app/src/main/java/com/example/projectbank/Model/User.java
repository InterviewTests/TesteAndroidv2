package com.example.projectbank.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private UserAccount userAccount;
    private Error error;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "User{" +
                "userAccount=" + userAccount +
                ", error=" + error +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.userAccount, flags);
        dest.writeParcelable(this.error, flags);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.userAccount = in.readParcelable(UserAccount.class.getClassLoader());
        this.error = in.readParcelable(Error.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
