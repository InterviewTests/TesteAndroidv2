package com.example.otavioaugusto.testesantander.login

interface LoginContrato {

    interface View{
        fun mensagensErro(msg:String)
        fun mensagemOk(msg: String)

    }

    interface Presenter{
        fun validarCPF(cpf:String):Boolean
        fun validar(user:String, password:String):Boolean
        fun validarPassword(password: String):Boolean
    }
}