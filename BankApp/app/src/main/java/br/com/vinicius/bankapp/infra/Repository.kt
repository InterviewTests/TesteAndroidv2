package br.com.vinicius.bankapp.infra

open class Repository(val data: RepositoryData = RepositoryData()) {

    class RepositoryData {
        fun restApi() = RetrofitClient.instance
    }
}