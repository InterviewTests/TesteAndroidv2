package br.com.mdr.testeandroid.model.business

import java.io.Serializable

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
): Serializable
