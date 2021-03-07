package br.com.silas.testeandroidv2.br.com.silas.testeandroidv2.mocks

import br.com.silas.domain.statements.Statements

class StatementsMock {
    companion object {
        fun getListStatements() =
            listOf(Statements("Pagamento", "Conta de Luz", "2018-08-15", 745.03))
    }
}