package com.projeto.testedevandroidsantander.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LancamentoViewModel extends LancamentoModel implements Parcelable{

    public LancamentoViewModel() {
        super();
    }

    protected LancamentoViewModel(Parcel in) {
        super(in);
    }

    public static final Creator<LancamentoViewModel> CREATOR = new Creator<LancamentoViewModel>() {
        @Override
        public LancamentoViewModel createFromParcel(Parcel in) {
            return new LancamentoViewModel(in);
        }

        @Override
        public LancamentoViewModel[] newArray(int size) {
            return new LancamentoViewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }
}
