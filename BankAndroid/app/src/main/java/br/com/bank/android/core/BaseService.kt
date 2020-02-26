package br.com.bank.android.core

import br.com.bank.android.exceptions.BusinessError
import br.com.bank.android.exceptions.ConnectionException
import br.com.bank.android.exceptions.UndefinedException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseService {

    fun <T> verifyResponse(response: Response<T>): T? {
        if (response.code() == 200) {
            try {
                val messageError =
                    JSONObject(response.body()?.toString() ?: "{}").getJSONObject("error")
                        .getString("message")
                messageError.let {
                    throw BusinessError(messageError)
                }
            } catch (e: Exception) {
                if (e.message?.isBlank() != false) {
                    throw UndefinedException()
                }

                if (e is BusinessError) throw e

                if (e is JSONException) return response.body()

                throw BusinessError(e.message)
            }
        }
        return response.body()
    }

    fun onError(error: Exception): BusinessError {
        if (error is BusinessError) return error

        //For connection
        if (error is UnknownHostException) return ConnectionException()
        if (error is SocketTimeoutException) return ConnectionException()

        return UndefinedException()
    }
}