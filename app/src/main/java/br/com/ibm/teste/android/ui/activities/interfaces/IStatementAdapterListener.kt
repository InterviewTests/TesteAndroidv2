package br.com.ibm.teste.android.ui.activities.interfaces

import br.com.ibm.teste.android.services.models.Statement

/**
 * Created by paulo.
 * Date: 12/11/18
 * Time: 11:56
 */
interface IStatementAdapterListener {
    fun postAdapterListener(item: Statement)
}