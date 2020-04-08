package pt.felipegouveia.bankapp

import com.google.gson.Gson
import pt.felipegouveia.bankapp.data.login.model.LoginResponse
import pt.felipegouveia.bankapp.data.statements.model.StatementsResponse

object Util {

    private var gson: Gson = Gson()

    fun createLoginResponseMockSingle(path: String): LoginResponse {
        return gson.fromJson(
            getJson(path), LoginResponse::class.java)
    }

    fun createStatementsResponseMockSingle(path: String): StatementsResponse {
        return gson.fromJson(
            getJson(path), StatementsResponse::class.java)
    }

    fun getJson(path: String): String? {
        return this::class.java.classLoader?.getResource(path)?.readText(Charsets.UTF_8)
    }
}