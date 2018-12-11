package com.example.otavioaugusto.testesantander.login

import com.example.otavioaugusto.testesantander.model.UserAccount

interface LoginContrato {

    interface View{
        fun mensagensErro(msg:String)
        fun user(user:UserAccount)
        fun showProgressBar();
        fun hideProgressBar();

    }

    interface Presenter{
        fun validarCPF(cpf:String):Boolean
        fun validar(user:String, password:String):Boolean
        fun validarPassword(password: String):Boolean
        fun login(user: String, password: String)
    }
}