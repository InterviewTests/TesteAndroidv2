package br.com.teste.santander.util

import android.content.Context
import android.widget.EditText
import br.com.teste.santander.R
import java.util.HashMap
import java.util.regex.Pattern

object Util {
    /**
     * Validando usuário
     */

    @Throws(Exception::class)
    fun validaUs(edt: EditText, context: Context): Boolean {
        if (edt.text.toString().isEmpty()) {
            edt.error = context.resources.getString(R.string.valida_us)
            return false
        }
        val regexNumber = "^[0-9]+\$"

        if((regexNumber.toRegex().matches(edt.text.toString())
                        && edt.text.toString().length == 11)){
            return true
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(edt.text.toString()).matches()) {
            edt.error = context.resources.getString(R.string.preencha_cpf_email)
            return false
        }

        return true
    }

    /**
     * Validando senha
     */
    fun validaSenha(edt: EditText,context: Context): Boolean {
        if(edt.text.toString().isEmpty()) {
            edt.error = context.resources.getString(R.string.valida_senha)
            return false
        }

        val expSenha = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%]).{6,20})" //FORMATAÇÃO

        if(!expSenha.toRegex().matches(edt.text.toString())) {
            edt.error = context.resources.getString(R.string.valida_senha_format)
            return false
        }

        return true
    }

    /**
     * Montando hash json
    */
    @Throws(Exception::class)
    fun retornaHashJson(type: String, vararg params: Any): HashMap<String, String> {
        val mHash = HashMap<String, String>()

        if (params.isNotEmpty()) {
            for (`object` in params) {
                val key = (`object` as String).split(type.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                if (key.size >= 2)
                    mHash[key[0]] = key[1]

            }
        }
        return mHash
    }

    fun validaPreenchimento(dados: String, tipo: Int): Boolean {

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
}