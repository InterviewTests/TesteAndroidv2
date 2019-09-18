package com.develop.tcs_bank.presentation.data

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.develop.tcs_bank.R
import com.develop.tcs_bank.domain.models.StatementList
import com.develop.tcs_bank.framework.extension.formatMask
import com.develop.tcs_bank.framework.base.TcsBaseActivity
import com.develop.tcs_bank.framework.base.TcsBaseFragment
import com.develop.tcs_bank.presentation.login.NavigationRegistration
import com.develop.tcs_bank.presentation.main.TcsApplication
import kotlinx.android.synthetic.main.fragment_datas.*

class DataView: TcsBaseFragment<TcsBaseActivity<*>>(), DataContract.View {

    lateinit var presenter: DataContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = DataPresenter(this)
        presenter.loadList()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgLogout.setOnClickListener {
            presenter.processLogout()
        }

        setText(presenter.setName(),presenter.setConta(),presenter.setBalance(),presenter.setAgency())

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_datas, container, false)
        return view
    }

    override fun navigate() {
        //activity.navigate(R.id.nav_login_home)
        val i = Intent(TcsApplication.instance.baseContext, NavigationRegistration::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        TcsApplication.instance.baseContext.startActivity(i)
    }

    override fun setText(nome: String, conta: Int, saldo: String, agency: String) {

        tvNome.text = nome
        tvConta.text = conta.toString()+" / "+agency.formatMask("##.######-#")
        tvSaldo.text = saldo

    }

    override fun configureList(statement: List<StatementList>) {

        val recyclerView = recyclerView
        recyclerView.adapter = DataAdapter(activity.context, statement)
        val layoutManager = LinearLayoutManager(activity.context)
        recyclerView.layoutManager = layoutManager

    }

    override fun showMessage(msg: String) {
        activity.showToast(msg)
    }

}