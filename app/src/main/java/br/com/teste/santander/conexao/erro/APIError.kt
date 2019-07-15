package br.com.teste.santander.conexao.erro

import android.content.Context
import br.com.teste.santander.R

class APIError {

    companion object {
        fun getError(code: Int, context: Context): String {
            when (code) {
                400 -> return context.resources.getString(R.string.erro_400)
                401 -> return context.resources.getString(R.string.erro_401)
                403 -> return context.resources.getString(R.string.erro_403)
                404 -> return context.resources.getString(R.string.erro_404)
                500 -> return context.resources.getString(R.string.erro_500)
                502 -> return context.resources.getString(R.string.erro_502)
                503 -> return context.resources.getString(R.string.erro_503)
                504 -> return context.resources.getString(R.string.erro_504)
                else ->
                    return context.resources.getString(R.string.erro_default).replace("_CODE", code.toString())
            }

        }
    }


}