package br.com.rms.bankapp.ui.home

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rms.bankapp.R
import br.com.rms.bankapp.base.view.BaseFragment
import br.com.rms.bankapp.data.local.database.entity.Statement
import br.com.rms.bankapp.utils.EndlessRecyclerViewScrollListener
import br.com.rms.bankapp.utils.extensions.formatAccountMask
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
                presenter.loadMoreStatemens()
            }
        })
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

    override fun updateUserName(name: String) {
        tvUserName.text = name
    }

    override fun updateUserAccount(agency: String, account: String) {
        tvUserAccount.text = getString(R.string.user_account_format,agency,account.formatAccountMask())
    }

    override fun updateUserBalance(balance: String) {
        tvUserBalance.text = balance

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