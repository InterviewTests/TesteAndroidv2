package com.example.ilealnod.SantanderProjectKotlin

import android.support.test.runner.AndroidJUnit4
import com.example.ilealnod.SantanderProjectKotlin.Todos.Dependencias.LoginValidate
import junit.framework.TestCase

import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LoginValidateUnitTest {

// Testes Da validação do login

    @Test
    fun testEmailisValid(){
        val email = "teste@gmail.com"
        val cpfValidate= LoginValidate()
        val result1:Boolean=cpfValidate.isValidEmail(email)
        TestCase.assertTrue(result1)

    }
    @Test
    fun testPassisValid(){
        val pass = "tT@1"
        val cpfValidate= LoginValidate()
        val result2:Boolean=cpfValidate.checkPassword(pass)
        TestCase.assertTrue(result2)

    }
    @Test
    fun testCpfisValid(){
        val cpf = "467906718-70"
        val cpfValidate= LoginValidate()
        val result3:Boolean=cpfValidate.isValidCPF(cpf)
        TestCase.assertTrue(result3)

    }
    @Test
    fun testPassisInvalid(){
        val pass2="111"
        val pass3="tttt"
        val pass4="t@1"
        val pass5="TTTT"
        val pass = "@1"
        val cpfValidate= LoginValidate()
        TestCase.assertFalse(cpfValidate.checkPassword(pass))
        TestCase.assertFalse(cpfValidate.checkPassword(pass2))
        TestCase.assertFalse(cpfValidate.checkPassword(pass3))
        TestCase.assertFalse(cpfValidate.checkPassword(pass4))
        TestCase.assertFalse(cpfValidate.checkPassword(pass5))
    }

    @Test
    fun testCpfisInvalid(){
        val cpf1="--"
        val cpf2="1112-"
        val cpf3="1113332222"
        val cpf4="-----//111"
        val cpfValidate= LoginValidate()
        TestCase.assertFalse(cpfValidate.isValid(cpf1))
        TestCase.assertFalse(cpfValidate.isValid(cpf2))
        TestCase.assertFalse(cpfValidate.isValid(cpf3))
        TestCase.assertFalse(cpfValidate.isValid(cpf4))
    }

    @Test
    fun testEmailisInvalid(){
        val email1="igor"
        val email2="igorlealnodari.com"
        val email3="igor.lealnodari@"
        val cpfValidate= LoginValidate()
        TestCase.assertFalse(cpfValidate.isValidEmail(email1))
        TestCase.assertFalse(cpfValidate.isValidEmail(email2))
        TestCase.assertFalse(cpfValidate.isValidEmail(email3))
    }


}
