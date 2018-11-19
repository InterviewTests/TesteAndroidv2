package com.rafaelpereiraramos.testeandroidv2.repo

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.rafaelpereiraramos.testeandroidv2.api.*
import com.rafaelpereiraramos.testeandroidv2.core.AppExecutors

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */

abstract class NetworkBoundResource<ApiResponseType, ReturnType>(
    private val appExecutors: AppExecutors
) {
    private val _result = MediatorLiveData<ResourceWrapper<ReturnType>>()
    val result: LiveData<ResourceWrapper<ReturnType>>
        get() = _result

    init {
        @Suppress("LeakingThis")
        val dbSource: LiveData<ReturnType?> = loadFromDb()

        _result.addSource(dbSource) { dbData ->
            _result.removeSource(dbSource)

            if (shouldFetch(dbData)) {
                fetchFromNetwork(dbSource)
            } else {
                _result.value = ResourceWrapper.success(dbData!!)
            }
        }
    }

    protected abstract fun loadFromDb(): LiveData<ReturnType?>

    protected abstract fun shouldFetch(result: ReturnType?): Boolean

    @WorkerThread protected abstract fun makeCall(): ApiResponseLiveData<ApiResponseType>

    @WorkerThread protected abstract fun saveCallResult(callResult: ApiResponseType)

    protected open fun onFetchFailed() {}

    private fun fetchFromNetwork(dbSource: LiveData<ReturnType?>) {
        val apiResponse = makeCall()

        _result.addSource(apiResponse) { fetchedData ->
            _result.removeSource(apiResponse)

            if (!apiResponse.isSuccessful) {

                onFetchFailed()

                _result.addSource(dbSource) { newData ->
                    _result.postValue(ResourceWrapper.error(getErrorMessage(apiResponse), newData))
                }
                return@addSource
            }

            if (fetchedData == null || apiResponse.code == 204) {
                _result.postValue(ResourceWrapper.error(getErrorMessage(apiResponse), null))
                return@addSource
            }

            appExecutors.diskIO.execute {
                saveCallResult(fetchedData)

                val newFetched = loadFromDb()

                appExecutors.mainThread.execute {
                    _result.addSource(newFetched) { newData ->
                        if (newData == null) {
                            ResourceWrapper.error("")
                        }
                        _result.postValue(ResourceWrapper.success(newData!!))
                    }
                }
            }
        }
    }

    private fun getErrorMessage(apiResponse: ApiResponseLiveData<ApiResponseType>): String {
        val msg = apiResponse.errorBody?.string()

        return  if (msg.isNullOrEmpty()) {
            apiResponse.message
        } else {
            msg
        }
    }
}
