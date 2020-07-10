package br.com.mdr.testeandroid.model.api

import br.com.mdr.testeandroid.model.business.Error
import br.com.mdr.testeandroid.model.business.Statement

/**
 * @author Marlon D. Rocha
 * @since 04/07/20
 */

data class DashboardApiModel(
    var statementList: MutableList<Statement>? = null,
    var error: Error? = null
)
