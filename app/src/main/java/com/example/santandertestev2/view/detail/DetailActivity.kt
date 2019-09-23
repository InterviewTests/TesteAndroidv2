package com.example.santandertestev2.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.santandertestev2.R
import com.example.santandertestev2.domain.Util.AppUtil
import com.example.santandertestev2.domain.controller.detail.DetailController
import com.example.santandertestev2.domain.controller.detail.IDetail
import com.example.santandertestev2.domain.model.controller.InvoiceItem
import com.example.santandertestev2.domain.model.controller.UserAccount
import com.example.santandertestev2.view.adapter.InvoiceAdapter
import okhttp3.internal.Util
import java.text.NumberFormat
import java.util.*

class DetailActivity : AppCompatActivity(),
    IDetail {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var labelUserName: TextView
    private lateinit var labelBalance: TextView
    private lateinit var labelBankAccount: TextView
    private lateinit var btnLogout: ImageView
    private lateinit var load : ProgressBar
    private var user : UserAccount? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        this.user = intent.getSerializableExtra("user") as UserAccount
        val controller = DetailController(this)

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

        this.setHeader()
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

    private fun setHeader(){
        this.user?.let {
            this.labelUserName.text = it.name
            this.labelBankAccount.text = "${it.bankAccount} / " + AppUtil.formatAgency(it.agency.toString())
            it.balance?.let {
                this.labelBalance.text = AppUtil.formatMoneyBr(it, true)
            }

        }
    }

}
