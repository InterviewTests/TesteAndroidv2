package com.develop.zupp_bank.presentation.data

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.develop.zupp_bank.R
import com.develop.zupp_bank.domain.models.StatementList
import com.develop.zupp_bank.framework.extension.formatMask
import com.develop.zupp_bank.infrastructure.base.ZuppBaseActivity
import com.develop.zupp_bank.infrastructure.base.ZuppBaseFragment
import com.develop.zupp_bank.presentation.login.NavigationRegistration
import com.develop.zupp_bank.presentation.main.ZupApplication
import kotlinx.android.synthetic.main.fragment_datas.*

class DataView: ZuppBaseFragment<ZuppBaseActivity<*>>(), DataContract.View {

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
        val i = Intent(ZupApplication.instance.baseContext, NavigationRegistration::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        ZupApplication.instance.baseContext.startActivity(i)
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