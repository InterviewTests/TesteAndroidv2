package br.com.testeandroidv2.presenter.statements

import android.content.Context
import br.com.testeandroidv2.model.statements.gson.StatementList

class MVP {
    interface Model {
        fun loadList(userId: Int)
    }

    interface Presenter {
        val context: Context
        val items: MutableList<StatementList>
        fun setView(view: View)
        fun showProgressBar(status: Boolean)
        fun loadList(userId: Int)
        fun updateListRecycler(list: MutableList<StatementList>?)
    }

    interface View {
        fun showProgressBar(visible: Int)
        fun updateListRecycler()
    }
}