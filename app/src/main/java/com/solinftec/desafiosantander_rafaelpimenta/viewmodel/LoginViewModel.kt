package com.solinftec.desafiosantander_rafaelpimenta.viewmodel

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.solinftec.desafiosantander_rafaelpimenta.R
import com.solinftec.desafiosantander_rafaelpimenta.communication.ApiService
import com.solinftec.desafiosantander_rafaelpimenta.model.LoginResponse
import com.solinftec.desafiosantander_rafaelpimenta.model.StatementResponse
import com.solinftec.desafiosantander_rafaelpimenta.model.UserAccount
import com.solinftec.desafiosantander_rafaelpimenta.util.DialogKeys
import com.solinftec.desafiosantander_rafaelpimenta.util.LoginValidate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application),
    LifecycleObserver {


    var usuario = ObservableField<String>()
    var senha = ObservableField<String>()
    var msg = 0
    var loginValidade = LoginValidate()
    var showDialog = MutableLiveData<Boolean>()
    var msgType = 0
    var msgStr = ""

    fun login() {

        val user = usuario.get()
        val passwd = senha.get()



        if (user.isNullOrBlank())
            msg = R.string.lbl_input_user
        else if (!loginValidade.validEmail(user.toString()) )
            msg = R.string.lbl_email_inval
        else if (passwd.isNullOrBlank())
            msg = R.string.lbl_input_senha
        else if (!loginValidade.validUpperCase(passwd.toString()))
            msg = R.string.lbl_maisc
        else if (!loginValidade.validarNumber(passwd.toString()))
            msg = R.string.lbl_letras_number
        else if (!loginValidade.validSpecialCharacter(passwd.toString()))
            msg = R.string.lbl_carac_esp

        if (msg != 0)
        {
            msgType = DialogKeys.ERRO_VALIDACAO
            showDialog.postValue(true)
            return
        }

//        var apiService = ApiService().api.login(
//            mapOf("user" to user.toString(), "password" to passwd.toString())
//        ).enqueue(object : Callback<LoginResponse>{
//            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                if (response.isSuccessful) {
//                    Log.d("TIMAO", "VALIDOU")
//                    var userAccount = response.body()
//                    Log.d("TIMAO", "RECEBEU: $userAccount")
//                }
//            }
//
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })


//        var apiService = ApiService().api.getStatements(1 ).enqueue(object  : Callback<StatementResponse>{
//            override fun onResponse(
//                call: Call<StatementResponse>,
//                response: Response<StatementResponse>
//            ) {
//                if (response.isSuccessful) {
//                    Log.d("TIMAO", "VALIDOU")
//                    var userAccount = response.body()
//                    Log.d("TIMAO", "RECEBEU: $userAccount")
//                }
//            }
//            override fun onFailure(call: Call<StatementResponse>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//
//        })
    }
}