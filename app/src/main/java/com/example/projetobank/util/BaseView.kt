package com.example.projetobank.util

interface BaseView<T> {
    var presenter: T

    /**
     * Inicialize a variável stática url da classe RetrofitInicializador
     * Utilize o método de extensão do fragment pegaUrlDeAcesso()
     * **/
    fun configuraUrlRetrofit()

}