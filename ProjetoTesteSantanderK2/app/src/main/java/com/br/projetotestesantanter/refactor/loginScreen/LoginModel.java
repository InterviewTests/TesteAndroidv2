package com.br.projetotestesantanter.refactor.loginScreen;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class LoginModel {


    public static class Login implements Parcelable {

        @SerializedName("userAccount")
        private LoginResponse loginResponse;

        @SerializedName("error")
        private Error error;

        public Login() {

        }


        protected Login(Parcel in) {
            loginResponse = in.readParcelable(LoginResponse.class.getClassLoader());
            error = in.readParcelable(Error.class.getClassLoader());
        }

        public static final Creator<Login> CREATOR = new Creator<Login>() {
            @Override
            public Login createFromParcel(Parcel in) {
                return new Login(in);
            }

            @Override
            public Login[] newArray(int size) {
                return new Login[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(loginResponse, flags);
            dest.writeParcelable(error, flags);
        }

        public LoginResponse getLoginResponse() {
            return loginResponse;
        }

        public Error getError() {
            return error;
        }
    }

    public static class LoginResponse implements Parcelable {

        @SerializedName("userId")
        private Long userId;
        @SerializedName("name")
        private String name;
        @SerializedName("bankAccount")
        private String bankAccount;
        @SerializedName("agency")
        private String agency;

        @SerializedName("balance")
        private float balance;


        public LoginResponse() {

        }

        protected LoginResponse(Parcel in) {
            if (in.readByte() == 0) {
                userId = null;
            } else {
                userId = in.readLong();
            }
            name = in.readString();
            bankAccount = in.readString();
            agency = in.readString();
            balance = in.readFloat();
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
            if (userId == null) {
                dest.writeByte((byte) 0);
            } else {
                dest.writeByte((byte) 1);
                dest.writeLong(userId);
            }
            dest.writeString(name);
            dest.writeString(bankAccount);
            dest.writeString(agency);
            dest.writeFloat(balance);
        }

        public Long getUserId() {
            return userId;
        }

        public String getName() {
            return name;
        }

        public String getBankAccount() {
            return bankAccount;
        }

        public String getAgency() {
            return agency;
        }

        public float getBalance() {
            return balance;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setBankAccount(String bankAccount) {
            this.bankAccount = bankAccount;
        }

        public void setAgency(String agency) {
            this.agency = agency;
        }

        public void setBalance(float balance) {
            this.balance = balance;
        }

    }

    public static class Error implements Parcelable {

        private Object object;

        public Error() {
        }

        public Error(Object object) {
            this.object = object;
        }

        protected Error(Parcel in) {

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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
        }
    }

    public static class LoginRequest {

        String login;
        String password;
    }

}
