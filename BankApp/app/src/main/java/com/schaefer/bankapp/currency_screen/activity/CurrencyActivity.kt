package com.schaefer.bankapp.currency_screen.activity

import FormatValue
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.schaefer.bankapp.R
import com.schaefer.bankapp.currency_screen.CurrencyConfigurator
import com.schaefer.bankapp.login_screen.activity.LoginActivity
import com.schaefer.bankapp.login_screen.presenter.CurrencyPresenterInput
import com.schaefer.bankapp.login_screen.router.CurrencyRouter
import com.schaefer.bankapp.model.ErrorResult
import com.schaefer.bankapp.model.statement.StatementResult
import com.schaefer.bankapp.model.user.UserModel
import com.schaefer.bankapp.util.Constants.Companion.EXTRA_USER
import kotlinx.android.synthetic.main.activity_currency.*
import kotlinx.android.synthetic.main.frame_progress.*
import kotlinx.android.synthetic.main.toolbar_general.*

class CurrencyActivity : AppCompatActivity(), CurrencyActivityInput {
    var presenter: CurrencyPresenterInput? = null
    var router: CurrencyRouter? = null
    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)
        this.overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave)

        CurrencyConfigurator.INSTANCE.configure(this)

        configClicks()
        if (intent.hasExtra(EXTRA_USER)) {
            initView(intent.getParcelableExtra(EXTRA_USER))
        } else {
            presenter?.logout()
        }
    }

    private fun configClicks() {
        image_logout.setOnClickListener {
            presenter?.logout()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initView(user: UserModel) {
        title_toolbar.text = user.name
        textView_account_number.text = user.bankAccount + " / " + user.agency
        textView_balance_value.text = FormatValue.formatFloatToString(user.balance)
        presenter?.getListStatement(user.userId)
    }

    override fun showProgress() {
        frame_progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        frame_progress.visibility = View.GONE
    }

    override fun errorGeneric(message: String) {
        toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast?.show()
    }

    override fun successGetListStatement(result: StatementResult) {
        recycler_statements.adapter = StatementAdapter(result.list, this)
        recycler_statements.layoutManager = LinearLayoutManager(this)
    }

    override fun errorGetListStatement(error: ErrorResult) {
        toast = Toast.makeText(this, error.message, Toast.LENGTH_LONG)
        toast?.show()
    }

    override fun successLogout() {
        toast = Toast.makeText(this, getString(R.string.msg_success_logout), Toast.LENGTH_LONG)
        toast?.show()
        router?.passDataToNextScene(Intent(this, LoginActivity::class.java))
    }

    override fun errorLogout() {
        toast = Toast.makeText(this, getString(R.string.error_generic), Toast.LENGTH_LONG)
        toast?.show()
    }

    override fun onPause() {
        super.onPause()
        if (isFinishing) this.overridePendingTransition(R.anim.animation_enterback, R.anim.animation_back)
    }
}