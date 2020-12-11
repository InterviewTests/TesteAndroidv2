package br.com.testeandroidv2.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import br.com.testeandroidv2.R
import br.com.testeandroidv2.model.bean.LoginBean
import br.com.testeandroidv2.presenter.statements.Presenter
import br.com.testeandroidv2.presenter.statements.MVP
import br.com.testeandroidv2.utils.Utils
import br.com.testeandroidv2.view.adapter.ItemAdapter
import br.com.testeandroidv2.view.components.Progress

class StatementsActivity : DefaultActivity(), MVP.View {
    private lateinit var progress: Progress
    private lateinit var accountNumber: TextView
    private lateinit var accountSaldo: TextView
    private lateinit var presenter: MVP.Presenter
    private lateinit var adapter: ItemAdapter
    private lateinit var rv: RecyclerView

    private var loginBean: LoginBean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_statements)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            loginBean = bundle.getParcelable("LOGIN_DATA")
        }

        setupToolBar(R.id.toolbar)
        setActionBarTitle(loginBean!!.name)
        setActionBarSubTitle("")

        progress = findViewById(R.id.progress)
        progress.hide()

        accountNumber = findViewById(R.id.accountNumber)
        accountSaldo  = findViewById(R.id.accountSaldo)

        accountNumber.text = loginBean!!.bankAccount + " / " + Utils.getAgency(loginBean!!.agency)
        accountSaldo.text = "R$" + Utils.formatDecimal(loginBean!!.balance)

        presenter = Presenter()
        presenter.setView(this)
        presenter.loadList(loginBean!!.userId)

        Handler().postDelayed({ onLoad() }, 100)
    }

    private fun onLoad() {
        val layoutManager = LinearLayoutManager(this)
            layoutManager.orientation = LinearLayoutManager.VERTICAL

        adapter = ItemAdapter(presenter.items)

        rv = findViewById(R.id.rv)
        rv.setHasFixedSize(true)
        rv.layoutManager = layoutManager
        rv.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_logout -> {
                back(Activity.RESULT_OK)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showProgressBar(visible: Int) {
        when(visible) {
            View.VISIBLE -> progress.show()
            View.GONE    -> progress.hide()
        }
    }

    override fun updateListRecycler() {
        adapter.notifyDataSetChanged()
    }

    override fun back(resultCode: Int) {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
        animLeftToRight()
    }
}