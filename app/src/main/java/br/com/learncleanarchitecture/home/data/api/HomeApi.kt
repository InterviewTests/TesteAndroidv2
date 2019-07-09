package br.com.learncleanarchitecture.home.data.api

import br.com.learncleanarchitecture.home.presentation.Statment
import br.com.learncleanarchitecture.util.GenericApi
import rx.Observable

class HomeApi {

    val service: HomeEndpointsApi = homeEndpointsApi()

    private fun homeEndpointsApi() = GenericApi.createApi(HomeEndpointsApi::class.java)

    fun getStatments(homeRequestApi: HomeRequestApi): Observable<Statment>? {
        return service.getStatements(homeRequestApi.id)
            .flatMap { listResult -> Observable.from(listResult.statementList) }
            .map {
                var statment = Statment()
                statment.title = it.title!!
                statment.desc = it.desc!!
                statment.date = it.date!!
                statment.value = it.value!!
                statment
            }
    }

    data class HomeRequestApi(var id: Int)
}
