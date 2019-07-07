package com.projeto.testedevandroidsantander.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UsuarioDTO implements Parcelable {

    @SerializedName("userAccount")
    public UsuarioModel userAccount;

    @SerializedName("error")
    private ErrorModel errorModel;

    protected UsuarioDTO(Parcel in) {
        userAccount = in.readParcelable(UsuarioModel.class.getClassLoader());
    }

    public static final Creator<UsuarioDTO> CREATOR = new Creator<UsuarioDTO>() {
        @Override
        public UsuarioDTO createFromParcel(Parcel in) {
            return new UsuarioDTO(in);
        }

        @Override
        public UsuarioDTO[] newArray(int size) {
            return new UsuarioDTO[size];
        }
    };

    public UsuarioModel getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UsuarioModel userAccount) {
        this.userAccount = userAccount;
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
        dest.writeParcelable(userAccount, flags);
    }
}
