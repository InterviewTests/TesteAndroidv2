package com.rafaelpereiraramos.testeandroidv2.repo

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
abstract class NetworkBoundResource<RequestType, ReturnType> {

    protected abstract fun loadFromDb(): ReturnType

    protected abstract fun shouldFetch(result: ReturnType?): Boolean

    protected abstract fun saveCallResult(callResult: ReturnType?)
}