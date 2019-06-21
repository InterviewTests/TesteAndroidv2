package br.com.vinicius.bankapp.ui.home

import br.com.vinicius.bankapp.data.remote.model.StatementModel
import br.com.vinicius.bankapp.data.repository.StatementRepository
import br.com.vinicius.bankapp.internal.BaseCallback

class HomePresenter(val view: HomeContract.View): HomeContract.Presenter {

    override fun fetchListStatements(idUser: Int) {
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
    }
}