package br.com.ibm.teste.android.services.api.statement

/**
 * Created by paulo.
 * Date: 12/11/18
 * Time: 11:41
 */
interface IStatementService {

    /**
     * The service for retrieve all statemtents
     */
    fun statements(userId: Int)
}