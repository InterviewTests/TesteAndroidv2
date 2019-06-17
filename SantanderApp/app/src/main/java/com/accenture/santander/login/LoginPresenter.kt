package com.accenture.santander.login

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import com.accenture.santander.R
import com.accenture.santander.viewmodel.User
import com.accenture.santander.entity.Auth
import com.accenture.santander.entity.Error
import com.accenture.santander.utils.Validate
import java.io.IOException
import javax.inject.Inject

class LoginPresenter(
    private val activity: Activity,
    private val view: View,
    private val iLoginPresenterOutput: LoginContracts.LoginPresenterOutput
) : LoginContracts.LoginPresenterInput, LoginContracts.LoginInteractorOutput {

    @Inject
    lateinit var iLoginInteractorInput: LoginContracts.LoginInteractorInput

    @Inject
    lateinit var loginRouter: LoginRouter

    init {
        DaggerLoginComponents
            .builder()
            .loginModulo(LoginModulo(context = activity, view = view, loginPresenter = this))
            .build()
            .inject(this)
    }

    override fun searchLogo(context: Activity) {
        try {
            val ims = context.getAssets()?.open(context.getString(R.string.assets_logo))
            val drawable = Drawable.createFromStream(ims, null)
            ims?.close()
            iLoginPresenterOutput.loadLogo(drawable)
        } catch (ex: IOException) {
            iLoginPresenterOutput.failLoadImage()
        }
    }

    override fun login(user: User) {

        if (!Validate.validateLogin(user.login)) {
            iLoginPresenterOutput.invalideLogin()
            return
        }

        if (!Validate.validatePassword(user.password)) {
            iLoginPresenterOutput.invalidePassword()
            return
        }

        iLoginPresenterOutput.visibleProgress()
        iLoginInteractorInput.login(user)
    }

    override fun sucessLogin() {
        iLoginPresenterOutput.goneProgress()
    }

    override fun errorLogin(throwable: Throwable) {
        iLoginPresenterOutput.goneProgress()
        iLoginPresenterOutput.errorLogin()
    }

    override fun failNetWork() {
        iLoginPresenterOutput.goneProgress()
        iLoginPresenterOutput.failNetWork()
    }

    override fun failResquest(code: Int) {
        iLoginPresenterOutput.goneProgress()
        iLoginPresenterOutput.failRequest()
    }

    override fun startLogged() {
        loginRouter.navigationToDashBoard()
    }

    override fun searchData() {
        iLoginInteractorInput.searchData()
    }

    override fun resultData(login: String, password: String) {
        val user = User()
        user.login = login
        user.password = password
        iLoginPresenterOutput.resultData(user)
    }

    override fun errorMensage(mensage: String?) {
        iLoginPresenterOutput.goneProgress()
        iLoginPresenterOutput.errorService(mensage)
    }
}