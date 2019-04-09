package br.com.rms.bankapp

import br.com.rms.bankapp.utils.validations.user.UserValidations
import org.junit.Test

class TestUserValidations {

    val userValidations = UserValidations()

    @Test
    fun loginEmpty(){
        val testUser = ""
        val testPassword = ""
        userValidations.validateLoginData(testUser, testPassword)
    }

    @Test
    fun loginEmptyUser(){
        val testUser = ""
        val testPassword = "@Test1"
        userValidations.validateLoginData(testUser, testPassword)
    }

    @Test
    fun loginPasswordEmpty(){
        val testUser = ""
        val testPassword = "@Test1"
        userValidations.validateLoginData(testUser, testPassword)
    }

    @Test
    fun validLoginEmail() {
        val testUser = "test@email.com"
        val testPassword = "Teste@1"
        userValidations.validateLoginData(testUser, testPassword)
     }


    @Test
    fun validLoginCpf(){
        val testUser = "39300378074"
        val testPassword = "Teste@1"
        userValidations.validateLoginData(testUser, testPassword)
    }

    @Test
    fun invalidCpf(){
        val testUser = "3930037807"
        val testPassword = "Teste@1"
        userValidations.validateLoginData(testUser, testPassword)
    }

    @Test
    fun invalidEmail() {
        val testUser = "test"
        val testPassword = "Teste@1"
        userValidations.validateLoginData(testUser, testPassword)
    }

    @Test
    fun invalidPassword() {
        val testUser = "test"
        val testPassword = "gggggg"
        userValidations.validateLoginData(testUser, testPassword)
    }


}