package com.joaoneto.testeandroidv2.model

import com.joaoneto.testeandroidv2.model.StatementModel
import java.io.Serializable

class StatementsResponseModel : Serializable{
    var statementList: List<StatementModel>? = null
    var error: Void? = null
}