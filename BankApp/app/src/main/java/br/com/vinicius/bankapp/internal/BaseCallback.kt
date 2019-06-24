package br.com.vinicius.bankapp.internal


interface BaseCallback<T> {
    fun onSuccessful(value : T)
    fun onUnsuccessful(error: String)
}