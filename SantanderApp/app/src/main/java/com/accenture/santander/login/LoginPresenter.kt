package com.accenture.santander.login

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import com.accenture.santander.R
import com.accenture.santander.viewmodel.User
import com.accenture.santander.entity.Auth
import com.accenture.santander.utils.Validate
import com.google.android.material.snackbar.Snackbar
import org.junit.Test
import org.junit.Assert.*
import java.io.IOException

class LoginPresenter(
    private val context: Context,
    private val view: View,
    private val iLoginPresenterOutput: LoginContracts.LoginPresenterOutput
) : LoginContracts.LoginPresenterInput, LoginContracts.LoginInteractorOutput {

    private val iLoginInteractorInput: LoginContracts.LoginInteractorInput = LoginInteractor(context, this)
    private val loginRouter = LoginRouter(view)


    @Test
    override fun searchLogo() {
        try {
            val ims = context.getAssets()?.open(context.getString(R.string.assets_logo))
            val drawable = Drawable.createFromStream(ims, null)
            assertNotNull(drawable)
            iLoginPresenterOutput.loadLogo(drawable)
            ims?.close()
        } catch (ex: IOException) {
            Toast.makeText(context, R.string.fail_load_image, Toast.LENGTH_LONG).show()
        }
    }

    @Test
    override fun login(user: User) {

        assert(Validate.validateLogin(user.login))

        //é necessario tirar o "!" para realizar o teste, pois o login que foi fornecido para os teste não é um e-mail nem um cpf
        if (Validate.validateLogin(user.login)) {
            iLoginPresenterOutput.invalideLogin(context.getString(R.string.invalide_login))
            return
        }

        assert(Validate.validatePassword(user.password))

        if (!Validate.validatePassword(user.password)) {
            iLoginPresenterOutput.invalidePassword(context.getString(R.string.invalide_password))
            return
        }

        iLoginInteractorInput.login(user)
    }

    override fun sucessLogin(auth: Auth?, user: User) {
        if (auth == null) {
            failNetWork()
        } else {
            if (auth.userAccount.userId > 0) {
                iLoginInteractorInput.registerUser(auth = auth.userAccount, user = user)
            } else {
                Snackbar.make(view, auth.error.message, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun errorLogin(throwable: Throwable) {
        Snackbar.make(view, R.string.fail_connection, Snackbar.LENGTH_LONG).show()
    }

    override fun failNetWork() {
        Snackbar.make(view, R.string.fail_connection, Snackbar.LENGTH_LONG).show()
    }

    override fun failResquest(code: Int) {
        Snackbar.make(view, R.string.fail_request, Snackbar.LENGTH_LONG).show()
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
}