package br.com.vinicius.bankapp.internal

open class Repository(val data: RepositoryData = RepositoryData()) {

    class RepositoryData {
        fun restApi() = RetrofitClient.instance
    }
}