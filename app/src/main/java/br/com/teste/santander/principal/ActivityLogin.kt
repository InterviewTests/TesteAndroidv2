package br.com.teste.santander.principal

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.view.View
import br.com.projetoaccenturebank.client.retrofit.OnCallback
import br.com.teste.santander.R
import br.com.teste.santander.model.Login
import br.com.teste.santander.util.Alert
import br.com.teste.santander.util.SendIntent
import br.com.teste.santander.util.Util
import kotlinx.android.synthetic.main.activity_login.*

class ActivityLogin : AppCompatActivity(), OnCallback,View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        controles()
    }

    fun controles() {
        btn_login.setOnClickListener(this)


        val login = Login().returnLogin(this)
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
                if(!Util.validaUs(edt_login,this)) {
                    return
                }

                /** VALIDANDO SENHA **/
                if(!Util.validaSenha(edt_senha,this)) {
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
            Alert.show(this, resources.getString(R.string.erro), Html.fromHtml(mensagem).toString(), false)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}