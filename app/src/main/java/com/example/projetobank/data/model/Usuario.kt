package com.example.projetobank.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class Usuario
    (
    @PrimaryKey()
    @SerializedName("usuario")
    val usuario: String,
    @SerializedName("senha")
    val senha: Double
) {

    override fun toString(): String {
        return "usuario: $usuario, senha: $senha"
    }

}
