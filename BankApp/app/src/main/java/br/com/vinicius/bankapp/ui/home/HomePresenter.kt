package br.com.vinicius.bankapp.ui.home

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import br.com.vinicius.bankapp.data.remote.model.StatementModel
import br.com.vinicius.bankapp.data.repository.StatementRepository
import br.com.vinicius.bankapp.internal.BaseCallback
import br.com.vinicius.bankapp.internal.CONNECTION_INTERNTET_ERROR
import br.com.vinicius.bankapp.internal.NotConnectionNetwork
import java.lang.Exception

class HomePresenter(val view: HomeContract.View): HomeContract.Presenter {

    override fun fetchListStatements(idUser: Int) {
        try {
            validateNetwork(view.getActivity())
            view.showProgressRecycler(true)
            StatementRepository().startStatements(idUser,
                object:BaseCallback<List<StatementModel>>{
                    override fun onSuccessful(value: List<StatementModel>) {
                        view.initRecyclerView(value)
                        view.showProgressRecycler(false)
                    }

                    override fun onUnsuccessful(error: String) {
                        view.notification(error)
                        view.showProgressRecycler(false)
                    }

                } )
        }catch (e: Exception) {
            e.message?.let { view.notification(it) }
        }

    }

    override fun validateNetwork(activity:AppCompatActivity){
        try {
            verifyNetwork(activity)
        }catch (e: NotConnectionNetwork) {
            view.notification(CONNECTION_INTERNTET_ERROR)
            view.logout()
        }
    }

    private fun verifyNetwork(activity:AppCompatActivity){
        val connectivityManager = activity.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if(!(networkInfo!= null && networkInfo.isConnected)) throw NotConnectionNetwork()
    }
}