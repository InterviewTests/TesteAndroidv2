package com.projeto.testedevandroidsantander.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class LancamentoModel implements Parcelable {
    @SerializedName("title")
    public String titulo;

    @SerializedName("desc")
    public String descricao;

    @SerializedName("date")
    public Date data;

    @SerializedName("value")
    public Double valor;

    public LancamentoModel() {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    protected LancamentoModel(Parcel in) {
        titulo = in.readString();
        descricao = in.readString();
        if (in.readByte() == 0) {
            valor = null;
        } else {
            valor = in.readDouble();
        }
    }

    public static final Creator<LancamentoModel> CREATOR = new Creator<LancamentoModel>() {
        @Override
        public LancamentoModel createFromParcel(Parcel in) {
            return new LancamentoModel(in);
        }

        @Override
        public LancamentoModel[] newArray(int size) {
            return new LancamentoModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titulo);
        dest.writeString(descricao);
        if (valor == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(valor);
        }
    }
}
