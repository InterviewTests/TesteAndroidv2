package projects.kevin.bankapp.user.login

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import projects.kevin.bankapp.R
import projects.kevin.bankapp.base.BaseActivity
import projects.kevin.bankapp.user.detail.DetailActivity
import projects.kevin.bankapp.user.sharedPref.UserDataSharedPref
import projects.kevin.bankapp.utils.validatePassword


class LoginActivity : BaseActivity(), LoginView {

    private lateinit var presenter: LoginPresenter
    private lateinit var userPreferences: UserDataSharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(view = this)
        userPreferences = UserDataSharedPref(this)

        buttonLogin.setOnClickListener {
            if(validatePassword(passLoginText.text.toString(), this)) {
               val credentials = LoginApiRequest(userLoginText.text.toString(), passLoginText.text.toString())
                presenter.userLogin(credentials)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(userPreferences.getName() != null) {
            loggedState(userPreferences.getName()!!)
        } else {
            logoutState()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun onLoginSuccess(userAccount: UserAccount) {
        presenter.saveOnShared(userAccount, userPreferences)

    }

    override fun onLoginFailed() {
        if(isActive()) {
            Toast.makeText(this, "Erro senha incorreta!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onSaveUserData() {
       DetailActivity.startDetail(this)
    }

    override fun hideLoading() {
        progressLayout.visibility = GONE
    }

    override fun showLoading() {
        progressLayout.visibility = VISIBLE
        progressLayout.bringToFront()
    }

    override fun onRequestFailed() {
        if(isActive()) {
            Toast.makeText(this, "Erro tente novamente mais tarde!", Toast.LENGTH_LONG).show()
        }
    }

    private fun loggedState(name: String) {
        buttonLogin.visibility = GONE
        userLoginText.visibility = GONE
        passLoginText.visibility = GONE
        SingInButton.visibility = VISIBLE
        singInLabel.visibility = VISIBLE
        alreadyLoggedName.visibility = VISIBLE

        formatLoggedText(name)
        loggedStateButtons()
    }

    private fun formatLoggedText(name: String) {
        val content = SpannableString("${getString(R.string.login_screen_logout)} $name")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        alreadyLoggedName.text = content
        SingInButton.text = name
    }

    private fun loggedStateButtons() {
        SingInButton.setOnClickListener {
            DetailActivity.startDetail(this)
        }
        alreadyLoggedName.setOnClickListener {
            userPreferences.clearPreferences()
            logoutState()
        }
    }

    private fun logoutState() {
        SingInButton.visibility = GONE
        singInLabel.visibility = GONE
        alreadyLoggedName.visibility = GONE
        buttonLogin.visibility = VISIBLE
        userLoginText.visibility = VISIBLE
        passLoginText.visibility = VISIBLE
    }
}
