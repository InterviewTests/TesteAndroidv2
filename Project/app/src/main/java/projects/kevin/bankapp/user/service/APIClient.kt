package projects.kevin.bankapp.user.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {

    companion object {

        private var protocol: ApiUserService? = null

        fun getReactiveService(): ApiUserService? {
            if (protocol == null){

                protocol = try {
                    val retrofit = Retrofit.Builder()
                        .baseUrl("https://bank-app-test.herokuapp.com/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()

                    retrofit.create(ApiUserService::class.java)
                }catch (e: IllegalArgumentException){
                    null
                }

            }
            return protocol
        }


    }
}