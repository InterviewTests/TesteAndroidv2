package com.example.androidtest.domain.repositories
import com.example.androidtest.data.api.ResponseEntity
import com.example.androidtest.domain.entities.LoginEntity
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginRepository {

//    fun getLogin(): Flowable<ResponseEntity>

    @Headers("Content-Type: application/json")
    @POST("login")
    fun getLogin(@Body loginPostData: LoginEntity): Call<ResponseEntity>
}
/*

data class LoginPostData(
    @SerializedName("UserId") var userID: String,
    @SerializedName("Password") var userPassword: String
)*/
