package com.joaoneto.testeandroidv2.loginscreen.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import com.joaoneto.testeandroidv2.mainscreen.view.activity.MainActivity
import com.joaoneto.testeandroidv2.R
import com.joaoneto.testeandroidv2.loginscreen.model.LoginResponseModel
import com.joaoneto.testeandroidv2.util.system.PreferencesHelper
import com.joaoneto.testeandroidv2.util.retrofit.RetrofitInitializer
import com.joaoneto.testeandroidv2.util.system.SnackbarHelper
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private val preferencesHelper: PreferencesHelper by lazy {
        PreferencesHelper(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login);
        checkPreferences()
        checkFields()
    }

    private fun checkFields() {

        loginButton.setOnClickListener {
            val user = inputEmail.text.toString()
            val pass = inputPass.text.toString()

            if (user.isNotEmpty() && pass.isNotEmpty()) {
                val regex = Regex(
                    "^(?=.*?\\p{Lu})(?=.*?[\\p{L}&&[^\\p{Lu}]])(?=.*?\\d)" +
                            "(?=.*?[`~!@#$%^&*()\\-_=+\\\\\\|\\[{\\]};:'\",<.>/?]).*$"
                )
                if (regex.matches(pass)) {
                    signIn(user, pass)
                } else {
                    SnackbarHelper.message(loginConstraint, "Senha inválida")
                }
            } else {
                SnackbarHelper.message(loginConstraint, "Campos obrigatórios vazios")
            }
        }

    }

    private fun signIn(user: String, password: String) {

        RetrofitInitializer().loginService()
            .signIn(user, password)
            .enqueue(object : Callback<LoginResponseModel> {
                override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {

                    Log.i("--->", t.message!!)
                    SnackbarHelper.message(
                        loginConstraint,
                        "Erro ao acessar conta, verifique suas credenciais"
                    )
                }

                override fun onResponse(
                    call: Call<LoginResponseModel>,
                    response: Response<LoginResponseModel>
                ) {

                    if (response.code() == 200) {

                        preferencesHelper.setUsername(user)
                        preferencesHelper.setPassword(password)

                        Log.i("--->", response.message())
                        Log.i("--->", response.code().toString())
                        Log.i("--->", response.body()?.userAccount?.agency!!)
                        val userAccountModel = response.body()?.userAccount

                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        val bundle = Bundle()
                        bundle.putSerializable("userAccountData", userAccountModel)
                        intent.putExtras(bundle)
                        startActivity(intent)
                        finish()

                    } else {
                        SnackbarHelper.message(
                            loginConstraint,
                            "Erro ao acessar conta, tente novamente"
                        )
                    }

                }

            })

    }

    private fun checkPreferences() {
        if (preferencesHelper.getUsername().isNotEmpty()) {
            inputEmail.text =
                Editable.Factory.getInstance().newEditable(preferencesHelper.getUsername())

        } else {
            inputEmail.text = Editable.Factory.getInstance().newEditable("")
        }
        if (preferencesHelper.getPassword().isNotEmpty()) {
            inputPass.text =
                Editable.Factory.getInstance().newEditable(preferencesHelper.getPassword())
        } else {
            inputPass.text =
                Editable.Factory.getInstance().newEditable("")
        }
    }

}
