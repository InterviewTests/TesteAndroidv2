package com.projeto.testedevandroidsantander.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UsuarioViewModel extends UsuarioModel implements Parcelable {


    protected UsuarioViewModel(Parcel in) {
        super(in);
    }

    public UsuarioViewModel() {
        super();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UsuarioViewModel> CREATOR = new Creator<UsuarioViewModel>() {
        @Override
        public UsuarioViewModel createFromParcel(Parcel in) {
            return new UsuarioViewModel(in);
        }

        @Override
        public UsuarioViewModel[] newArray(int size) {
            return new UsuarioViewModel[size];
        }
    };
}
