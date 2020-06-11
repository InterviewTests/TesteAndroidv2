package projects.kevin.bankapp.user.detail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.header_user_detail.*
import projects.kevin.bankapp.R
import projects.kevin.bankapp.user.login.UserAccount
import projects.kevin.bankapp.user.sharedPref.UserDataSharedPref
import projects.kevin.bankapp.utils.formatMoney

class DetailActivity : AppCompatActivity(), DetailView {

    override fun loadStatements(bankStatements: ArrayList<BankStatements>?) {
        initStatementRecycler(bankStatements!!)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

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
            userPreferences.clearPreferences()
            this.finish()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.loadUserData(userPreferences)
    }

    fun initStatementRecycler(bankStatements: ArrayList<BankStatements>) {
        val fastingTipsAdapter = StatementsAdapter()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        bankStatementRecyclerView.layoutManager = layoutManager
        bankStatementRecyclerView.adapter = fastingTipsAdapter
        bankStatementRecyclerView.setHasFixedSize(false)
        fastingTipsAdapter.setListView(bankStatements)
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
}
