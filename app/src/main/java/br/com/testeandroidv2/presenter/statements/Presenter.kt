package br.com.testeandroidv2.presenter.statements

import android.content.Context
import android.view.View

import br.com.testeandroidv2.model.statements.Model
import br.com.testeandroidv2.model.statements.gson.StatementList

class Presenter : MVP.Presenter {
    private lateinit var view: MVP.View

    private val model: Model = Model(this)
    private var list: MutableList<StatementList> = emptyList<StatementList>().toMutableList()

    override val context: Context
        get() = view as Context
    override val items: MutableList<StatementList>
        get() = list

    override fun setView(view: MVP.View) { this.view = view }
    override fun showProgressBar(status: Boolean) {
        val visible: Int = if (status) View.VISIBLE else View.GONE
        view.showProgressBar(visible)
    }

    override fun loadList(userId: Int) {
        model.loadList(userId)
    }

    override fun updateListRecycler(list: MutableList<StatementList>?) {
        this.list.clear()
        this.list.addAll(list!!)
        view.updateListRecycler()
    }
}