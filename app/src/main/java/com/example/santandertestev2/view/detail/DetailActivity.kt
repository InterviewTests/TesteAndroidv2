package com.example.santandertestev2.view.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.santandertestev2.R
import com.example.santandertestev2.domain.Util.AppUtil
import com.example.santandertestev2.domain.controller.detail.DetailController
import com.example.santandertestev2.domain.model.controller.InvoiceItem
import com.example.santandertestev2.domain.model.controller.UserAccount
import com.example.santandertestev2.domain.presenter.DetailPresenter
import com.example.santandertestev2.domain.presenter.IDetailPresenter
import com.example.santandertestev2.view.adapter.InvoiceAdapter

class DetailActivity : AppCompatActivity(), IDetailPresenter {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var labelUserName: TextView
    private lateinit var labelBalance: TextView
    private lateinit var labelBankAccount: TextView
    private lateinit var btnLogout: ImageView
    private lateinit var load : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val presenter = DetailPresenter(this)
        val controller = DetailController(this, presenter)

        this.load = findViewById(R.id.invoiceload)
        this.labelUserName = findViewById(R.id.labelFullName)
        this.labelBankAccount = findViewById(R.id.accountNumber)
        this.labelBalance = findViewById(R.id.balance)
        this.btnLogout = findViewById(R.id.btnLogout)
        this.btnLogout.setOnClickListener {
            controller.logout()
        }
        recyclerView = findViewById(R.id.recyclerviewInvoice)
        viewManager = LinearLayoutManager(this)
        controller.getUser(intent)
        controller.getUserInvoice()

    }

    override fun onInvoiceLoad(itens: List<InvoiceItem>) {

        viewAdapter = InvoiceAdapter(itens)
        load.visibility = View.GONE
        recyclerView.apply {

            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onLogout(intent: Intent) {
        startActivity(intent)
        this.finish()
    }

    override fun onLoadUser(userAccount: UserAccount) {
        userAccount.let {
            this.labelUserName.text = it.name
            this.labelBankAccount.text = "${it.bankAccount} / " + AppUtil.formatAgency(it.agency.toString())
            it.balance?.let {
                this.labelBalance.text = AppUtil.formatMoneyBr(it, true)
            }
        }
    }

    override fun onFetchInvoiceError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        this.load.visibility = View.GONE
    }

}
