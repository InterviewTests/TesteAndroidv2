package br.com.mdr.testeandroid.model.api

import br.com.mdr.testeandroid.model.business.Error
import br.com.mdr.testeandroid.model.business.User

/**
 * @author Marlon D. Rocha
 * @since 04/07/20
 */

data class UserApiModel(
    var userAccount: User? = null,
    var error: Error? = null
)
