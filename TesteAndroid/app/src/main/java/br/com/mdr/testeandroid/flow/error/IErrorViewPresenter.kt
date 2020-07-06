package br.com.mdr.testeandroid.flow.error

import br.com.mdr.testeandroid.model.business.Error

interface IErrorViewPresenter {
    val messageIdName: String
    val messageId: Int?
    val param: String?

    fun withError(error: Error): IErrorViewPresenter
    fun messageId(): Int
}
