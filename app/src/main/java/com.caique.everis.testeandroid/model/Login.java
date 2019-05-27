package com.caique.everis.testeandroid.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class Login {

    @PrimaryKey
    @NonNull
    @SerializedName("user")
    private String user;

    @SerializedName("password")
    private String password;

    @NonNull
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
