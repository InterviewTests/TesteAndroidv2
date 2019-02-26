package com.example.ilealnod.SantanderProjectKotlin.Todos.Login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import com.example.ilealnod.SantanderProjectKotlin.R
import com.example.ilealnod.SantanderProjectKotlin.Todos.ChamadaApi.GetCredentials
import com.example.ilealnod.SantanderProjectKotlin.Todos.ChamadaApi.RetrofitCall
import com.example.ilealnod.SantanderProjectKotlin.Todos.Dependencias.AccountInfo
import com.example.ilealnod.SantanderProjectKotlin.Todos.Dependencias.ClientData
import com.example.ilealnod.SantanderProjectKotlin.Todos.Dependencias.LoginValidate
import com.example.ilealnod.SantanderProjectKotlin.Todos.Dependencias.UserInput
import com.example.ilealnod.SantanderProjectKotlin.Todos.Form.FormActivity
import com.google.gson.Gson

open class LoginActivity : AppCompatActivity() {

    // Implementação da Tela de Login

    private var btn_login: Button? = null
    private var edt_user: EditText? = null
    private var edt_password: EditText? = null
    private var progress_cir: ProgressBar? = null

    private var context: Context? = null
    private var retrofit: RetrofitCall? = null
    private var userInput: UserInput? = null
    private var clientData: ClientData? = null

    private var stringDadoCliente: String? = null
    private var stringLista: String? = null

    private var loginValidate: LoginValidate? = null
    private var loginAux: LoginAux? = null
    private var service: GetCredentials? = null

    var user: String? = null
    var pass: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        iniciarVariaveis()
        iniciarAcao()

    }

    private fun iniciarAcao() {

        btn_login?.setOnClickListener {

            user = edt_user!!.text.toString()
            pass = edt_password!!.text.toString()
            userInput = UserInput(user!!, pass!!)

            progress_cir!!.visibility = View.VISIBLE

            progress_cir?.setProgress(20, true)

            // Logica para tratamento do usuario e senha imputados

            if (loginAux?.checkLogin(user!!, pass!!)!!) {
                loginAux!!.showToast(context!!, "Login Sucess")

            // Se a logica retornar True, ele pega os dados do cliente,
                // se não ele responde ("Login Failed")

                loginAux!!.requestClientData(service!!, userInput!!)

            } else {

                progress_cir!!.visibility = View.INVISIBLE
                loginAux!!.showToast(context!!, "Login Fail")
            }
        }
    }

    private fun iniciarVariaveis() {

        edt_user = findViewById(R.id.edt_login_user)
        edt_password = findViewById(R.id.edt_login_password)
        progress_cir = findViewById(R.id.progress_circular)
        btn_login = findViewById(R.id.btn_login)


        retrofit = RetrofitCall()
        clientData = ClientData()

        context = applicationContext

        loginValidate = LoginValidate()
        loginAux = LoginAux(this)

        service = retrofit?.getCall()?.create(GetCredentials::class.java)
    }

    // Funcão que Transfere os dados da tela de login Para Tela de formulario

    fun nextPage(accountInfoResponse: List<AccountInfo>?, clientData: ClientData): Boolean {

        val intent = Intent(this@LoginActivity, FormActivity::class.java)
        val gson = Gson()

        stringDadoCliente = gson.toJson(clientData)
        stringLista = gson.toJson(accountInfoResponse)

        intent.putExtra("DATACLIENT", stringDadoCliente)
        intent.putExtra("LISTACLIENT", stringLista)

        progress_cir!!.visibility = View.GONE

        return if (stringDadoCliente != null && stringLista != null) {

            startActivity(intent)

            true
        } else {
            loginAux!!.showToast(this, "Dados não enviados")
            false
        }
    }

}
