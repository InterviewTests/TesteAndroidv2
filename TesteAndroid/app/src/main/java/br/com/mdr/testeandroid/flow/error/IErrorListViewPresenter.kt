package br.com.mdr.testeandroid.flow.error

import br.com.mdr.testeandroid.model.business.ErrorList

interface IErrorListViewPresenter {
    val errorList: List<IErrorViewPresenter>

    fun withErrorList(errorList: ErrorList?): IErrorListViewPresenter
}
