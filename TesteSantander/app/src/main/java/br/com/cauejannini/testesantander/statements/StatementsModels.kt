package br.com.cauejannini.testesantander.statements

data class StatementsRequestModel(val userId: String) {}

class StatementsResponseModel {

    var statementList: List<Statement>? = null
    var error: Any? = null
}


class UserDataModel {

    var userName: String? = null
    var agenciaConta: String? = null
    var saldo: String? = null
}

class Statement {

    constructor() {}

    constructor(title: String?, desc: String?, date: String?, value: Double?) {
        this.title = title
        this.desc = desc
        this.date = date
        this.value = value
    }

    var title: String? = null
    var desc: String? = null
    var date: String? = null
    var value: Double? = null
}