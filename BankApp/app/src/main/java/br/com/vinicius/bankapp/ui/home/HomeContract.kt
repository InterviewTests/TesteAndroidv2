package br.com.vinicius.bankapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import br.com.vinicius.bankapp.data.remote.model.StatementModel

class HomeContract {

    interface View {
        fun notification(message: String)

        fun initRecyclerView(models: List<StatementModel>)

        fun showProgressRecycler(show: Boolean)

        fun getActivity(): AppCompatActivity

        fun logout()
    }

    interface Presenter {
        fun fetchListStatements(idUser:Int)

        fun validateNetwork(activity:AppCompatActivity)
    }
}