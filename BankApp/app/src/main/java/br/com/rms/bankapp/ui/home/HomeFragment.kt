package br.com.rms.bankapp.ui.home

import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.rms.bankapp.R
import br.com.rms.bankapp.base.view.BaseFragment
import br.com.rms.bankapp.data.local.database.entity.Statement
import br.com.rms.bankapp.utils.EndlessRecyclerViewScrollListener
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

        rvStatement.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager){
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                presenter.loadMoreStatemens()
            }

        })
    }

    override fun onMoreStatementsReady(statements: List<Statement>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoadStatementFailed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}