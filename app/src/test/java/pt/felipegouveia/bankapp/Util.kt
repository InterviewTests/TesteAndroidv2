package pt.felipegouveia.bankapp

import com.google.gson.Gson
import pt.felipegouveia.bankapp.data.login.model.LoginData
import pt.felipegouveia.bankapp.data.statements.model.StatementsData
import pt.felipegouveia.bankapp.domain.model.login.Login
import pt.felipegouveia.bankapp.domain.model.statements.Statements

object Util {

    private var gson: Gson = Gson()

    fun createLoginDataMockSingle(path: String): LoginData {
        return gson.fromJson(
            getJson(path), LoginData::class.java)
    }

    fun createLoginDomainMockSingle(path: String): Login {
        return gson.fromJson(
            getJson(path), Login::class.java)
    }

    fun createStatementsDataMockSingle(path: String): StatementsData {
        return gson.fromJson(
            getJson(path), StatementsData::class.java)
    }

    fun createStatementsDomainMockSingle(path: String): Statements {
        return gson.fromJson(
            getJson(path), Statements::class.java)
    }

    fun getJson(path: String): String? {
        return this::class.java.classLoader?.getResource(path)?.readText(Charsets.UTF_8)
    }
}