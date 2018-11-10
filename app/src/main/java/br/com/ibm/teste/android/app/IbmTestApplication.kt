package br.com.ibm.teste.android.app

import android.app.Application
import br.com.ibm.teste.android.services.config.APIClient
import br.com.ibm.teste.android.services.config.ApiConfig

/**
 * Created by paulo.
 * Date: 10/11/18
 * Time: 16:31
 */
class IbmTestApplication : Application() {

    private lateinit var apiClient: APIClient

    companion object {

        private lateinit var ibmTestApplication: IbmTestApplication

        fun getInstance(): IbmTestApplication = ibmTestApplication
    }

    override fun onCreate() {
        super.onCreate()
        ibmTestApplication = this
        apiClient = APIClient(ApiConfig.BASE_URL)
    }

    fun getApiClient() = apiClient

    fun getRetrofit() = apiClient.getRetrofit()

}

