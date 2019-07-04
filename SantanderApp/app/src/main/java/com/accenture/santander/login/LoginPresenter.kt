package com.accenture.santander.login

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.accenture.santander.R
import com.accenture.santander.utils.StatusBar
import com.accenture.santander.viewmodel.User
import com.accenture.santander.utils.Validate
import java.io.IOException
import javax.inject.Inject

class LoginPresenter(
    activity: Activity,
    view: View,
    private val iLoginPresenterOutput: LoginContracts.LoginPresenterOutput
) : LoginContracts.LoginPresenterInput, LoginContracts.LoginInteractorOutput {

    @Inject
    lateinit var iLoginInteractorInput: LoginContracts.LoginInteractorInput

    @Inject
    lateinit var loginRouter: LoginRouter

    init {
        DaggerLoginComponents
            .builder()
            .loginModule(LoginModule(activity, view, loginPresenter = this))
            .build()
            .inject(this)
    }

    override fun searchLogo(context: Activity) =
        try {
            val ims = context.getAssets()?.open(context.getString(R.string.assets_logo))
            val drawable = Drawable.createFromStream(ims, null)
            ims?.close()
            iLoginPresenterOutput.loadLogo(drawable)
        } catch (ex: IOException) {
            iLoginPresenterOutput.failLoadImage()
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

    override fun sucessLogin() = iLoginPresenterOutput.goneProgress()


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

    override fun startLogged() = loginRouter.navigationToStatement()

    override fun searchData() = iLoginInteractorInput.searchData()

    override fun resultData(login: String, password: String) = iLoginPresenterOutput.resultData(login, password)

    override fun errorMensage(mensage: String?) {
        iLoginPresenterOutput.goneProgress()
        iLoginPresenterOutput.errorService(mensage)
    }

    override fun statusBarColor(activity: Activity) {
        StatusBar.setStatusBarColor(activity, Color.WHITE)
        (activity as AppCompatActivity).supportActionBar?.hide()
        activity.window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    }

    override fun onDestroyStatusBarColor(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }
}