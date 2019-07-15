package br.com.projetoaccenturebank.util

import android.util.Base64
import android.util.Base64.encodeToString


object Utils {

    fun validaSenha(dados: String, tipo: Int): Boolean {

        if (tipo == 1)
            if (dados.isEmpty())
                return false


        if (tipo == 2) {
            if(dados.isEmpty())
                return false

            val expSenha = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%]).{6,20})"

            return expSenha.toRegex().matches(dados)
        }

        return true
    }

    /**
     * RETORNA HASH MAP
     *
     * @param params
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun retornaHashJson(type: String, vararg params: Any): HashMap<String, String> {
        val mHash = HashMap<String, String>()

        if (params.size > 0) {
            for (`object` in params) {
                val key = (`object` as String).split(type.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                if (key.size >= 2)
                    mHash[key[0]] = key[1]

            }
        }
        return mHash
    }

}