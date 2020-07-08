package br.com.mdr.testeandroid.model.business

import br.com.mdr.testeandroid.extensions.formattedCurrency
import java.io.Serializable
import java.text.NumberFormat
import java.util.*

/**
 * @author Marlon D. Rocha
 * @since 04/07/20
 */
data class User(
    var userId: Int? = null,
    var name: String? = null,
    var bankAccount: String? = null,
    var agency: String? = null,
    var balance: Double? = null
): Serializable {

    fun getFullAccount(): String {
        return "$agency / $bankAccount"
    }

    fun getFormattedBalance(): String {
        balance?.let { return it.formattedCurrency() }
        return ""
    }
}
