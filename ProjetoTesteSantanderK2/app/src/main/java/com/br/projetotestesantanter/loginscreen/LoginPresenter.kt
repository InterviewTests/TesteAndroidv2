package com.br.projetotestesantanter.loginscreen

import android.widget.EditText
import com.br.projetotestesantanter.R
import com.br.projetotestesantanter.ValidationUtil
import com.br.projetotestesantanter.api.Endpoint
import com.br.projetotestesantanter.api.RetrofitConfiguration
import com.br.projetotestesantanter.api.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter : LoginContract.Presenter {



    var edtUser: EditText? = null
     var edtPassword: EditText? = null
    private var view : LoginContract.View ? = null



    override fun setEditUser(editUser: EditText) {
        edtUser = editUser
    }

    override fun setEditPassword(editPassword: EditText) {
        edtPassword = editPassword
    }

    override fun doLogin(loginModel: LoginModel) {
    }

    override fun start() {
    }


    override fun attachView(view: LoginContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun logar() {

        this.view?.showProgressBar()

        var user  =  edtUser?.text.toString()

        if(!ValidationUtil.isCPF(user) && !ValidationUtil.isValidEmail(user)) {
            this.view?.hiddenProgressBar()

            this.view?.showErroMsg(this.view!!.getContext().getString(R.string.error_user))
            edtUser?.error = this.view!!.getContext().getString(R.string.error_user)
            return

        }

        if(!ValidationUtil.validationPassword(edtPassword?.text.toString())){
            this.view?.hiddenProgressBar()

            this.view?.showErroMsg(this.view!!.getContext().getString(R.string.error_password))
            edtPassword?.error = this.view!!.getContext().getString(R.string.error_password)
            return
        }

        createUserLogin()


    }

    private fun createUserLogin() {
        var loginModel = LoginModel(edtUser?.text.toString(),edtPassword?.text.toString())
        dataLogin(loginModel)

    }

    private fun dataLogin(loginModel: LoginModel) {

        val retrofitClient = RetrofitConfiguration
            .getRetrofitInstance()

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.login(loginModel.user,loginModel.password)

        callback.enqueue(object : Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                t.message?.let { view?.showErroMsg(it) }
                view?.hiddenProgressBar()

            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                response.body()?.let { view?.openScreenStatement(it) }
                view?.hiddenProgressBar()


            }

        })


    }


}