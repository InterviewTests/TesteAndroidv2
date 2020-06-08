import com.joaoneto.testeandroidv2.api.LoginService
import com.joaoneto.testeandroidv2.api.StatementsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer {
    private val retrofit: Retrofit

    init {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://bank-app-test.herokuapp.com/api/")
            .addConverterFactory(JacksonConverterFactory.create())
            .client(client)
            .build()
    }

    fun loginService(): LoginService = retrofit.create(
        LoginService::class.java)

    fun statementsService(): StatementsService = retrofit.create(
        StatementsService::class.java)
}