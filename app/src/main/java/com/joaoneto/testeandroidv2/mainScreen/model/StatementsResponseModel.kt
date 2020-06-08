package com.joaoneto.testeandroidv2.mainScreen.model

import java.io.Serializable

class StatementsResponseModel : Serializable{
    var statementList: List<StatementModel>? = null
    var error: Void? = null
}