package br.com.projetoaccenturebank.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import br.com.projetoaccenturebank.client.retrofit.OnCallback
import br.com.projetoaccenturebank.client.retrofit.task.TaskLogin
import br.com.projetoaccenturebank.login.R
import br.com.projetoaccenturebank.model.Login
import br.com.projetoaccenturebank.util.Alerta
import br.com.projetoaccenturebank.util.SendIntent
import br.com.projetoaccenturebank.util.Utils
import kotlinx.android.synthetic.main.login_activity.*

class LoginActivity : AppCompatActivity(), View.OnClickListener, OnCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        controles()
    }

    fun controles() {
        btn_login.setOnClickListener(this)


        val login = Login().retornar(this)
        if(login != null)
            SendIntent.with()
                .mClassFrom(this)
                .mClassTo(ActivityPrincipal::class.java)
                .mType(R.integer.slide_from_right)
                .go()
    }

    override fun onClick(v: View?) {
        when(v) {
            btn_login -> {

                /** VALIDANDO USUÁRIO **/
                if(!Utils.validaSenha(edt_login.text.toString(), 1)) {
                    Alerta.show(this, "Atenção","Preencha o usuário", true)
                    return
                }

                /** VALIDANDO SENHA **/
                if(!Utils.validaSenha(edt_senha.text.toString(), 2)) {
                    Alerta.show(this, "Atenção","A senha deve conter pelo menos:\n\nUma letra maiuscula\nUm caracter especial\nUm caracter alfanumérico", true)
                    return
                }

                TaskLogin(this).execute(edt_login.text.toString(), edt_senha.text.toString())
            }
        }
    }

    override fun onRetorno(aBoolean: Boolean, mensagem: String) {
        if(aBoolean) {
            SendIntent.with()
                .mClassFrom(this)
                .mClassTo(ActivityPrincipal::class.java)
                .mType(R.integer.slide_from_right)
                .go()
        } else
            Alerta.show(this, "Erro", Html.fromHtml(mensagem).toString(), false)
    }
}
