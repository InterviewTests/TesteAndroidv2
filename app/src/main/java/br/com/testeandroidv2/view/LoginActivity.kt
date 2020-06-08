package br.com.testeandroidv2.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import br.com.testeandroidv2.R
import br.com.testeandroidv2.model.bean.LoginBean
import br.com.testeandroidv2.presenter.login.MVP
import br.com.testeandroidv2.presenter.login.Presenter
import br.com.testeandroidv2.utils.Utils
import br.com.testeandroidv2.view.components.Progress
import br.com.testeandroidv2.view.listener.OnCallback
import com.google.android.material.textfield.TextInputEditText


class LoginActivity : DefaultActivity(), MVP.View {
    private lateinit var progress: Progress
    private lateinit var btnLoginEnter: Button
    private lateinit var userLogin: TextInputEditText
    private lateinit var passLogin: TextInputEditText
    private lateinit var presenter: MVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        progress = findViewById(R.id.progressLogin)
        progress.hide()

        userLogin = findViewById(R.id.userLogin)
        passLogin = findViewById(R.id.passLogin)
        passLogin.setOnEditorActionListener{ textView, actionId, keyEvent ->
            when(actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    enter()
                    false
                }
                else -> false
            }
        }

        btnLoginEnter = findViewById(R.id.btnLoginEnter)
        btnLoginEnter.setOnClickListener { enter() }

        presenter = Presenter()
        presenter.setView(this)
    }

    private fun enter() {
        var user: String = userLogin.editableText.toString()
        var password: String = passLogin.editableText.toString()

        if (user.isEmpty()) {
            msgBox(getString(R.string.login_error1), object : OnCallback {
                override fun onSuccess() {}
            })
            return
        }
        else if (user.isEmpty()) {
            msgBox(getString(R.string.login_error2), object : OnCallback {
                override fun onSuccess() {}
            })
            return
        }
        else if (!Utils.isValidPassword(password)) {
            msgBox(getString(R.string.login_error3), object : OnCallback {
                override fun onSuccess() {}
            })
            return
        }

        presenter.loadLogin(user, password)
    }

    private fun statements(loginBean: LoginBean) {
        val bundle = Bundle()
            bundle.putParcelable("LOGIN_DATA", loginBean)

        val intent = Intent(this, StatementsActivity::class.java)
            intent.putExtras(bundle)

        startActivity(intent)
        finish()
        animRightToLeft()
    }

    override fun showProgressBar(visible: Int) {
        when(visible) {
            View.VISIBLE -> progress.show()
            View.GONE    -> progress.hide()
        }
    }

    override fun updateData(loginBean: LoginBean) {
        msgBox(getString(R.string.login_ok), object : OnCallback {
            override fun onSuccess() { statements(loginBean) }
        })
    }

    override fun back(resultCode: Int) { finish() }
}