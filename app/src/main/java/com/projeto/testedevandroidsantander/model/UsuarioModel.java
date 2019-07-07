package com.projeto.testedevandroidsantander.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class UsuarioModel implements Parcelable {
    @SerializedName("userId")
    public Long id;

    @SerializedName("name")
    public String nome;

    @SerializedName("bankAccount")
    public String conta;

    @SerializedName("agency")
    public String agencia;

    @SerializedName("balance")
    public Double saldo;

    public UsuarioModel() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    protected UsuarioModel(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        nome = in.readString();
        conta = in.readString();
        agencia = in.readString();
        if (in.readByte() == 0) {
            saldo = null;
        } else {
            saldo = in.readDouble();
        }
    }

    public static final Creator<UsuarioModel> CREATOR = new Creator<UsuarioModel>() {
        @Override
        public UsuarioModel createFromParcel(Parcel in) {
            return new UsuarioModel(in);
        }

        @Override
        public UsuarioModel[] newArray(int size) {
            return new UsuarioModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(nome);
        dest.writeString(conta);
        dest.writeString(agencia);
        if (saldo == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(saldo);
        }
    }
}
