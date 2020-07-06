package br.com.mdr.testeandroid.flow.error

import br.com.mdr.testeandroid.model.business.ErrorList

class ErrorListViewPresenter(
    override val errorList: MutableList<IErrorViewPresenter> = mutableListOf()
) : IErrorListViewPresenter {

    override fun withErrorList(errorList: ErrorList?): IErrorListViewPresenter {
        this.errorList.clear()
        errorList?.let {
            this.errorList.addAll(errorList.list.map { error ->
                ErrorViewPresenter().withError(error)
            })
        }
        return this
    }
}
