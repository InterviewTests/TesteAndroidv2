package com.rafaelpereiraramos.testeandroidv2.repo

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.rafaelpereiraramos.testeandroidv2.api.*
import com.rafaelpereiraramos.testeandroidv2.core.AppExecutors

/**
 * Created by Rafael P. Ramos on 17/11/2018.
 */
/*
abstract class NetworkBoundResource<ApiResponseType, ReturnType> (
    private val appExecutors: AppExecutors
) {

    private val result = MediatorLiveData<ResourceWrapper<ReturnType?>>()

    init {
        val dbSource: LiveData<ReturnType?> = loadFromDb()

        result.addSource(dbSource) {dbData ->
            //If is already added as a source but with a different Observer, throws an Exception
            result.removeSource(dbSource)

            if (shouldFetch(dbData)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) {newData ->
                    setValue(ResourceWrapper.success(newData))
                }
            }
        }
    }

    fun asLiveData():LiveData<ResourceWrapper<ReturnType?>> = result

    protected abstract fun loadFromDb(): LiveData<ReturnType?>

    protected abstract fun shouldFetch(result: ReturnType?): Boolean

    protected abstract fun makeCall(): LiveData<ResponseWrapper<ApiResponseType>>

    protected abstract fun saveCallResult(callResult: ApiResponseType)

    protected open fun onFetchFailed() {}

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<ApiResponseType>) = response.body

    private fun fetchFromNetwork(dbSource: LiveData<ReturnType?>) {
        val apiResponse = makeCall()

        // we re-attach dbSource as a new source, it will dispatch its latest value quickly
        result.addSource(dbSource) { newData ->
            setValue(ResourceWrapper.loading(newData))
        }

        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)

            when (response) {

                is ApiSuccessResponse -> {
                    appExecutors.diskIO.execute {
                        saveCallResult(processResponse(response))

                        appExecutors.mainThread.execute {

                            // Maybe the fetched obj from network is different from the return type
                            result.addSource(loadFromDb()) { newData ->
                                setValue(ResourceWrapper.success(newData))
                            }
                        }
                    }
                }

                is ApiEmptyResponse -> {
                    appExecutors.mainThread.execute {
                        // reload from disk whatever we had
                        result.addSource(loadFromDb()) { newData ->
                            if (shouldFetch(newData)) {
                                setValue(ResourceWrapper.error("response returned empty", newData))
                            } else {
                                setValue(ResourceWrapper.success(newData))
                            }
                        }
                    }
                }

                is ApiErrorResponse -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        setValue(ResourceWrapper.error(response.errorMessage, newData))
                    }
                }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: ResourceWrapper<ReturnType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }
}*/

abstract class NetworkBoundResource<ApiResponseType, ReturnType>(
    private val appExecutors: AppExecutors
) {
    private val _result = MediatorLiveData<ReturnType>()
    val result: LiveData<ReturnType?>
        get() = _result

    init {
        @Suppress("LeakingThis")
        val dbSource: LiveData<ReturnType?> = loadFromDb()

        _result.addSource(dbSource) { dbData ->
            _result.removeSource(dbSource)

            if (shouldFetch(dbData)) {
                fetchFromNetwork()
            } else {
                _result.value = dbData
            }
        }
    }

    protected abstract fun loadFromDb(): LiveData<ReturnType?>

    protected abstract fun shouldFetch(result: ReturnType?): Boolean

    @WorkerThread protected abstract fun makeCall(): ApiResponseLiveData<ApiResponseType>

    @WorkerThread protected abstract fun saveCallResult(callResult: ApiResponseType)

    protected open fun onFetchFailed() {}

    private fun fetchFromNetwork() {
        val apiResponse = makeCall()

        _result.addSource(apiResponse) { fetchedData ->
            _result.removeSource(apiResponse)

            if (!apiResponse.isSuccessful)
                return@addSource

            if (fetchedData == null)
                return@addSource

            appExecutors.diskIO.execute {
                saveCallResult(fetchedData)

                val newFetched = loadFromDb()

                _result.addSource(newFetched) { newData ->
                    _result.postValue(newData)
                }
            }
        }
    }
}
