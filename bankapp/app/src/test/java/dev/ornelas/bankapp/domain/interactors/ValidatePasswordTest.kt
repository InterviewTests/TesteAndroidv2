package dev.ornelas.bankapp.domain.interactors

import dev.ornelas.banckapp.domain.interactors.ValidatePassword
import dev.ornelas.banckapp.domain.model.datatype.ResultType
import org.junit.Assert
import org.junit.Test

class ValidatePasswordTest {


    @Test
    fun verifySucessWhenValidPassword() {
        //Given
        val validatePassword = ValidatePassword()
        val pass = "A@0"

        //When
        val result = validatePassword(pass)

        //Then
        Assert.assertEquals(ResultType.SUCCESS, result.resultType)
    }

    @Test
    fun verifyErrorWhenInvalidPassword() {
        //Given
        val validatePassword = ValidatePassword()
        val pass = "a@0"

        //When
        val result = validatePassword(pass)

        //Then
        Assert.assertEquals(ResultType.ERROR, result.resultType)
    }
}