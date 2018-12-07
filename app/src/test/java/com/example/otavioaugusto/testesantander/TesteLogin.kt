package com.example.otavioaugusto.testesantander

import com.example.otavioaugusto.testesantander.login.LoginActivity
import com.example.otavioaugusto.testesantander.login.LoginContrato
import com.example.otavioaugusto.testesantander.login.LoginPresenter
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class TesteLogin {

    val login = Mockito.mock(LoginActivity::class.java)

//    @Test
//    fun verificar_user_ou_pass_esta_vazio(){
//        //Assegura que retorna false se estiver user ou pass vazios
//        val user = ""
//        val pass = ""
//        val presenter = LoginPresenter(login)
//        val r:Boolean = presenter.validar(user, pass)
//        Assert.assertFalse(r)
//
//    }
//
//    @Test
//    fun verificar_user_se_e_cpf(){
//        //Assegura se o cpf esteja errado não deixa passar
//        val user = "04953388"
//        val presenter = LoginPresenter(login)
//        val resultado :Boolean = presenter.validarCPF(user)
//        Assert.assertFalse(resultado)
//
//    }

    @Test
    fun verifacar_password(){
        val pass = "1"
        val presenter = LoginPresenter(login)
        val resultado :Boolean = presenter.validarPassword(pass)
        Assert.assertFalse(resultado)

    }
}