package com.solinftec.desafiosantander_rafaelpimenta.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.solinftec.desafiosantander_rafaelpimenta.R
import com.solinftec.desafiosantander_rafaelpimenta.communication.ApiService
import com.solinftec.desafiosantander_rafaelpimenta.communication.Repository
import com.solinftec.desafiosantander_rafaelpimenta.model.LoginResponse
import com.solinftec.desafiosantander_rafaelpimenta.model.StatementResponse
import com.solinftec.desafiosantander_rafaelpimenta.model.UserAccount
import com.solinftec.desafiosantander_rafaelpimenta.util.DialogKeys
import com.solinftec.desafiosantander_rafaelpimenta.util.LoginListener
import com.solinftec.desafiosantander_rafaelpimenta.util.LoginValidate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application),
    LifecycleObserver {

    var repository: Repository = Repository()
    var loginListener: LoginListener? = null

    var usuario = ObservableField<String>()
    var senha = ObservableField<String>()
    var msg = 0
    var loginValidade = LoginValidate()
    var showDialog = MutableLiveData<Boolean>()
    var msgType = 0
    var msgStr = ""

    fun login(view: View) {

        val user = usuario.get()
        val passwd = senha.get()

        loginListener?.onStarted(view)


        if (user.isNullOrBlank())
            msg = R.string.lbl_input_user
        else if (!loginValidade.validEmail(user.toString())
            && !loginValidade.isCPF(user.toString()))
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

        val loginResponse = repository.login(
            mapOf("user" to user.toString(), "password" to passwd.toString())
        )
        loginListener?.onSuccess(view, loginResponse)


    }
}