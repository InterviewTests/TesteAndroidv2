package com.br.natanfelipe.bankapp.model

data class Statement (val title : String, val desc : String, val date: String, val value : Double) {
}

data class StatementList(val statementList: List<Statement>){

}