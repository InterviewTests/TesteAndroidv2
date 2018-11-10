package br.com.ibm.teste.android.utils

import android.arch.persistence.room.TypeConverter
import br.com.ibm.teste.android.services.models.UserRequest
import br.com.ibm.teste.android.services.models.UserResponse
import com.google.gson.Gson

/**
 * Created by paulo.
 * Date: 10/11/18
 * Time: 18:56
 */
class Converter {

    companion object {

        @TypeConverter
        fun convertToJson(user: UserResponse): String {
            return Gson().toJson(user)
        }

        @TypeConverter
        fun convertToObject(json: String): UserResponse? {
            return Gson().fromJson(json, UserResponse::class.java)
        }

        @TypeConverter
        fun convertUserRequestToJson(user: UserRequest): String {
            return Gson().toJson(user)
        }

        @TypeConverter
        fun convertJsonToUserRequest(json: String): UserRequest? {
            return Gson().fromJson(json, UserRequest::class.java)
        }
    }
}