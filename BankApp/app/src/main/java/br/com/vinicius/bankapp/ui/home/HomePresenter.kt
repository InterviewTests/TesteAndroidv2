package br.com.vinicius.bankapp.ui.home

import br.com.vinicius.bankapp.data.remote.model.StatementModel
import br.com.vinicius.bankapp.data.repository.StatementRepository
import br.com.vinicius.bankapp.internal.BaseCallback

class HomePresenter(val view: HomeContract.View): HomeContract.Presenter {

    override fun fetchListStatements(idUser: Int) {
        StatementRepository().startStatements(idUser,
            object:BaseCallback<List<StatementModel>>{
                override fun onSuccessful(value: List<StatementModel>) {
                    view.notification("LISTA CHEGOU")
                }

                override fun onUnsuccessful(error: String) {
                    view.notification(error)
                }

            } )
    }
}