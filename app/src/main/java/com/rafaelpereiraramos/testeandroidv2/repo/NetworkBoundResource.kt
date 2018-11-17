package com.rafaelpereiraramos.testeandroidv2.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
abstract class NetworkBoundResource<RequestType, ReturnType> {

    private val result = MediatorLiveData<ReturnType>()

    protected abstract fun loadFromDb(): LiveData<ReturnType>

    protected abstract fun shouldFetch(result: ReturnType?): Boolean

    protected abstract fun saveCallResult(callResult: ReturnType?)

    protected abstract fun makeCall(request: RequestType)

    fun asLiveData() = result
}