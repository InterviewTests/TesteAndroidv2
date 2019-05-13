package com.example.projetobank.util

import com.example.projetobank.data.model.Concentrador
import com.example.projetobank.data.model.Usuario
import org.json.JSONStringer

fun LinkedHashMap<String, String>.criaConcentrador(
    usuario: Usuario?
): Concentrador {
    val json = JSONStringer()
    json.`object`().key("log").`object`()

    usuario?.let {
        json.key("user").value(usuario.user)
        json.key("password").value(usuario.password)
    }


    for ((chave, value) in this) {
        json.key(chave).value(value)
    }

    json.endObject().endObject()

    return Concentrador(json.toString())
}