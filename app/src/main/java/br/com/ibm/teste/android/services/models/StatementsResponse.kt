package br.com.ibm.teste.android.services.models

import com.google.gson.annotations.SerializedName

/**
 * Created by paulo.
 * Date: 12/11/18
 * Time: 11:38
 */
class StatementsResponse(@SerializedName("statementList") val statementList: List<Statement>,
                         @SerializedName("error") val error: Error)