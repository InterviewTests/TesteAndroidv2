package com.example.otavioaugusto.testesantander.statements

import com.example.otavioaugusto.testesantander.model.StatementListItem
import com.example.otavioaugusto.testesantander.model.Statements
import com.example.otavioaugusto.testesantander.model.UserAccount

interface StatementsContrato {

    interface View{

        fun listaStatements(lista:List<StatementListItem>)
        fun mensagensErro(msg:String)
        fun mensagemOk(msg: String)


    }

    interface Presenter{

        fun obterListadaAPI(id:String)

    }
}