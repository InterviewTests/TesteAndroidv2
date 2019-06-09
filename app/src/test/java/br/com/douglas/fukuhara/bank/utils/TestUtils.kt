package br.com.douglas.fukuhara.bank.utils

import com.google.gson.Gson
import io.reactivex.Observable
import java.io.File

object TestUtils {
    fun <T>fromJsonToObj(json: String, classType: Class<T>): T {
        val uri = this.javaClass.classLoader.getResource(json)
        val file = File(uri.path)
        val jsonString = String(file.readBytes())

        return Gson().fromJson(jsonString, classType)
    }

    fun <T> getGenericServer(): Observable<T> {
        return Observable.error(Throwable())
    }

    fun <T> getServerErrorWithMessage(serverErrorMessage: String) : Observable<T> {
        return Observable.error(generateThrowableError(serverErrorMessage))
    }

    private fun generateThrowableError(serverErrorMessage: String): Throwable {
        return object : Throwable() {
            override val message: String?
                get() = serverErrorMessage
        }
    }
}