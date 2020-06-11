package projects.kevin.bankapp.user.detail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.header_user_detail.*
import projects.kevin.bankapp.R
import projects.kevin.bankapp.base.BaseActivity
import projects.kevin.bankapp.user.login.UserAccount
import projects.kevin.bankapp.user.sharedPref.UserDataSharedPref
import projects.kevin.bankapp.utils.formatMoney
import projects.kevin.bankapp.utils.validateMaterialDialog

class DetailActivity : BaseActivity(), DetailView {

    private lateinit var presenter: DetailPresenter
    private lateinit var userPreferences: UserDataSharedPref

    companion object {
        const val MONEY_TYPE = "R$"

        fun startDetail(activity: Activity) {
            val start = Intent(activity, DetailActivity::class.java)
            activity.startActivity(start)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        presenter = DetailPresenter(this)
        userPreferences = UserDataSharedPref(this)

        logoutBtnAccount.setOnClickListener {
            onLogoutClick()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadUserData(userPreferences)
    }

    override fun loadStatements(bankStatements: ArrayList<BankStatements>?) {
        initStatementRecycler(bankStatements!!)
        hideLoading()
    }

    override fun showLoading() {
        loadingStatements.visibility = VISIBLE
    }

    override fun hideLoading() {
        loadingStatements.visibility = GONE
    }

    @SuppressLint("SetTextI18n")
    override fun loadUserAccountPreferences(userAccount: UserAccount) {
        with(userAccount) {
            presenter.fetchUserStatements(userId)
            nameDetailAccount.text = name
            agencyDetailAccount.text = "$bankAccount ${getString(R.string.account_agency_separator)} $agency"
            balanceDetailAccount.text = "$MONEY_TYPE${formatMoney(balance)}"
        }
    }

    private fun onLogoutClick() {
        validateMaterialDialog(this)?.show {
            title(res = R.string.logout_dialog_title)
            message(res = R.string.logout_dialog_desc)
            cancelable(true)
            cornerRadius(literalDp = 8f)
            positiveButton(res = R.string.yes) { dialog ->
                userPreferences.clearPreferences()
                this@DetailActivity.finish()
                dialog.dismiss()
            }
            negativeButton(res = R.string.no) { dialog ->
                dialog.dismiss()
            }
        }

    }

    private fun initStatementRecycler(bankStatements: ArrayList<BankStatements>) {
        val fastingTipsAdapter = StatementsAdapter()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        bankStatementRecyclerView.layoutManager = layoutManager
        bankStatementRecyclerView.adapter = fastingTipsAdapter
        bankStatementRecyclerView.setHasFixedSize(false)
        fastingTipsAdapter.setListView(bankStatements)
    }
}
