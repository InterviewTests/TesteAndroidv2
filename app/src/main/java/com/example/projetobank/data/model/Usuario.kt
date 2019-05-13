package com.example.projetobank.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Usuario
    (
    @PrimaryKey()
    @SerializedName("user")
    val user: String,
    @SerializedName("password")
    val password: String
) {

    override fun toString(): String {
        return "user: $user, senha: $password"
    }

    fun converteParaHashMap(): LinkedHashMap<String, String> {
        return linkedMapOf<String, String>(
            Pair("user", user),
            Pair("senha", password)
        )
    }

}
