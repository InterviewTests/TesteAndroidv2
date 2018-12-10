package br.com.rphmelo.bankapp.presentation.currency

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import br.com.rphmelo.bankapp.R
import br.com.rphmelo.bankapp.presentation.currency.adapters.StatementListAdapter
import br.com.rphmelo.bankapp.presentation.currency.models.CurrencyView
import br.com.rphmelo.bankapp.presentation.currency.models.StatementModel
import br.com.rphmelo.bankapp.presentation.login.LoginActivity
import kotlinx.android.synthetic.main.activity_currency.*

class CurrencyActivity : AppCompatActivity(), CurrencyView {

    private lateinit var toolbar: Toolbar
    private val presenter = CurrencyPresenter(this, CurrencyInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)

        toolbar = findViewById(R.id.toolbar)

        startToolbarSetup()
        startToolbarLoadData()
        startStatementLoadData()
    }

    override fun setToolbarData() {

    }

    override fun showProgress() {
        currency_progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        currency_progress.visibility = View.GONE
    }

    override fun setToolbarError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setStatementListData() {
        val recyclerView = rvList
        recyclerView.adapter = StatementListAdapter(getList(), this)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

    }

    override fun setStatementListError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setupToolbar() {
        toolbar.inflateMenu(R.menu.menu)
        toolbar.setOnMenuItemClickListener {
            val login = Intent(this, LoginActivity::class.java)
            startActivity(login)
            finish()
            return@setOnMenuItemClickListener true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    private fun startToolbarSetup() {
        presenter.setupToolbar()
    }

    private fun startToolbarLoadData() {
        presenter.toolbarLoadData(200)
    }

    private fun startStatementLoadData() {
        presenter.statementLoadData(200)
    }

    private fun getList(): List<StatementModel>{
        return listOf(
                StatementModel("Pagamento",
                        "Conta de luz",
                        "2018-08-15",
                        -50.7),
                StatementModel("Pagamento",
                        "Conta de luz",
                        "2018-08-15",
                        50.8),
                StatementModel("Pagamento",
                        "Conta de luz",
                        "2018-08-15",
                        -50.3),
                StatementModel("Pagamento",
                        "Conta de luz",
                        "2018-08-15",
                        50.3),
                StatementModel("Pagamento",
                        "Conta de luz",
                        "2018-08-15",
                        -50.3),
                StatementModel("Pagamento",
                        "Conta de luz",
                        "2018-08-15",
                        -5.2),
                StatementModel("Pagamento",
                        "Conta de luz",
                        "2018-08-15",
                        34.4),
                StatementModel("Pagamento",
                        "Conta de luz",
                        "2018-08-15",
                        50.0))
    }

}
