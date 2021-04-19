package com.example.appbanksantander_accenturetalentacquisition

import com.example.appbanksantander_accenturetalentacquisition.Utils.Validation
import org.junit.Assert.assertEquals
import org.junit.Test

class ValidationUnitTest {

    @Test
    fun isEmail(){
        val user = "henrique@henrique.com"
        val validation = Validation()
        val isValid = validation.isEmail(user)
        if (isValid){
            System.out.println(isValid)
        }else{
            System.out.println(isValid)
        }
    }

    @Test
    fun isNotEmail(){
        val user = "henrique"
        val validation = Validation()
        val isValid = validation.isEmail(user)
        if (isValid){
            System.out.println(isValid)
        }else{
            System.out.println(isValid)
        }
    }

    @Test
    fun isPassword(){
        val password = "Abcdef#1"
        val validation = Validation()
        val isValid = validation.verifyPassword(password)
        if (isValid){
            System.out.println(isValid)
        }else{
            System.out.println(isValid)
        }
    }

    @Test
    fun isNotPassword(){
        val password = "Ab#1"
        val validation = Validation()
        val isValid = validation.verifyPassword(password)
        if (isValid){
            System.out.println(isValid)
        }else{
            System.out.println(isValid)
        }
    }

    @Test
    fun isCPF() {
        val cpf = "00000000000"
        val validation = Validation()
        val isValid = validation.isCPF(cpf)
        if (isValid){
            System.out.println(isValid)
        }else{
            System.out.println(isValid)
        }
    }

    @Test
    fun isNotCPF() {
        val cpf = "1111111"
        val validation = Validation()
        val isValid = validation.isCPF(cpf)
        if (isValid){
            System.out.println(isValid)
        }else{
            System.out.println(isValid)
        }
    }

    @Test
    fun isNotCPF2() {
        val cpf = "sssssss"
        val validation = Validation()
        val isValid = validation.isCPF(cpf)
        if (isValid){
            System.out.println(isValid)
        }else{
            System.out.println(isValid)
        }
    }

    @Test
    fun isNotCP3() {
        val cpf = ""
        val validation = Validation()
        val isValid = validation.isCPF(cpf)
        if (isValid){
            System.out.println(isValid)
        }else{
            System.out.println(isValid)
        }
    }
}