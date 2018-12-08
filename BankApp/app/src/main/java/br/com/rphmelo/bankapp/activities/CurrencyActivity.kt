package br.com.rphmelo.bankapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.Toolbar
import android.view.Menu
import br.com.rphmelo.bankapp.R
import br.com.rphmelo.bankapp.adapters.StatementListAdapter
import br.com.rphmelo.bankapp.models.StatementModel
import br.com.rphmelo.bankapp.presentation.login.LoginActivity
import kotlinx.android.synthetic.main.activity_currency.*

class CurrencyActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)
        toolbar = findViewById(R.id.toolbar)
        setupCurrencyToolbar()

        val recyclerView = rvList
        recyclerView.adapter = StatementListAdapter(getList(), this)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
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

    private fun setupCurrencyToolbar() {
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
}
