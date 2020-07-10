package br.com.mdr.testeandroid.model.business

import br.com.mdr.testeandroid.extensions.formatDateBr
import java.text.NumberFormat
import java.util.*

/**
 * @author Marlon D. Rocha
 * @since 04/07/20
 */
data class Statement(
    var title: String = "",
    var desc: String = "",
    var date: String = "",
    var value: Double? = 0.0
) {
    fun getFormatedValue(): String {
        return NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            .format(value)
    }

    fun getFormatedDate(): String? {
        return date.formatDateBr()
    }
}
