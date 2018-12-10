package com.accenture.primo.bankapp.model

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ErrorTest {
    lateinit var objError: Error

    @Before
    fun config() {
        objError = Error(0,"Mensagem de Erro")
    }

    @Test
    fun is_a_object_error_valid() {
        Assert.assertEquals(Error::class.java, objError::class.java)
        Assert.assertEquals(0, objError.code)
        Assert.assertEquals("Mensagem de Erro", objError.menssage)
    }
}