package br.com.rms.bankapp.ui.home

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rms.bankapp.R
import br.com.rms.bankapp.base.view.BaseFragment
import br.com.rms.bankapp.data.local.database.entity.Account
import br.com.rms.bankapp.data.local.database.entity.Statement
import br.com.rms.bankapp.utils.EndlessRecyclerViewScrollListener
import br.com.rms.bankapp.utils.UtilsMoneyFormatting
import br.com.rms.bankapp.utils.extensions.formatAgencyMask
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<HomeContract.View, HomeContract.Presenter>(), HomeContract.View {

    private val statementAdapter = StatementAdapter()

    override fun getViewInstance(): HomeContract.View = this

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initViews() {
        val layoutManager = LinearLayoutManager(context)
        rvStatement.layoutManager = layoutManager
        rvStatement.adapter = statementAdapter
        rvStatement.itemAnimator = null

        initExitButton()

        rvStatement.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                presenter.loadMoreStatements()
            }
        })
    }

    override fun accountIsReady(userAccountInfo: Account) {
        tvUserName.text = userAccountInfo.name
        tvUserAccount.text = getString(
            R.string.user_account_format,
            userAccountInfo.agency?.formatAgencyMask(),
            userAccountInfo.bankAccount
        )
        tvUserBalance.text = userAccountInfo.balance?.let { it1 -> UtilsMoneyFormatting.simpleMoneyFormmat(it1) }
    }

    override fun onMoreStatementsReady(statements: List<Statement>) {
        statementAdapter.addStatements(statements)
    }

    override fun showErrorMessage(errorMessage: Int) {
        showToastLong(errorMessage)
    }

    override fun showLoading() {
        pbLoad.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbLoad.visibility = View.GONE
    }

    private fun initExitButton() {
        ivExit.setOnClickListener {
            activity?.apply {
                setResult(Activity.RESULT_CANCELED)
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }

        }
    }
}