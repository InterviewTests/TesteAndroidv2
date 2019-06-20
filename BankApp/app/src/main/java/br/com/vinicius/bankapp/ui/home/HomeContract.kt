package br.com.vinicius.bankapp.ui.home

class HomeContract {

    interface View {
        fun notification(message: String)
    }

    interface Presenter {
        fun fetchListStatements(idUser:Int)
    }
}