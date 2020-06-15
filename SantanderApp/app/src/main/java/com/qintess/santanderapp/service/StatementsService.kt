package com.qintess.santanderapp.service

import com.qintess.santanderapp.helper.forEach
import com.qintess.santanderapp.model.StatementModel
import com.qintess.santanderapp.model.StatementType

open class StatementsService: ServiceInterface {
    override fun getHttpClient(): HttpInterface {
        return Http()
    }

    fun getStatements(userId: Int, onSuccess: SuccessCallback<ArrayList<StatementModel>>, onFailure: FailureCallback) {
        getHttpClient().get("statements/$userId",
            onSuccess = {
                val statements = ArrayList<StatementModel>()
                var jsonArray = it.getJSONArray("statementList")
                jsonArray.forEach {
                    val value = it.getDouble("value")
                    statements.add(
                        StatementModel(
                            title = it.getString("title"),
                            desc = it.getString("desc"),
                            date = it.getString("date"),
                            value = if (value >= 0) value else value * -1,
                            type = if (value >= 0) StatementType.Credit else StatementType.Debit
                        )
                    )
                }
                onSuccess(statements)
            },
            onFailure = onFailure
        )
    }
}