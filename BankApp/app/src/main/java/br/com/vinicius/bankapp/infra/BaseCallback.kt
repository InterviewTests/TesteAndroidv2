package br.com.vinicius.bankapp.infra

interface BaseCallback<T> {
    fun onSuccessful(value : T)
    fun onUnsuccessful(error: String)
}