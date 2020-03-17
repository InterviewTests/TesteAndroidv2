package dev.ornelas.bankapp.domain.interactors

import dev.ornelas.banckapp.domain.interactors.ValidateUserName
import dev.ornelas.banckapp.domain.model.datatype.Result
import dev.ornelas.banckapp.domain.model.datatype.ResultType
import org.junit.Assert
import org.junit.Test

class ValidateUserNameTest {

    @Test
    fun verifySucessWhenValidEmailUserName() {

        //Given
        val userName = "example@provider.com.br"
        val validateUser = ValidateUserName()

        //When
        val result = validateUser(userName)

        //Then
        Assert.assertEquals(ResultType.SUCCESS, result.resultType)
    }

    @Test
    fun verifyErrorWhenInvalidEmailUserName() {

        //Given
        val userName = "invalid@provider"
        val validateUser = ValidateUserName()

        //When
        val result = validateUser(userName)

        //Then
        Assert.assertEquals(ResultType.ERROR, result.resultType)
    }

    @Test
    fun verifySucessWhenValidCPFUserName() {

        //Given
        val userName = "97316505044"
        val validateUser = ValidateUserName()

        //When
        val result = validateUser(userName)

        //Then
        Assert.assertEquals(ResultType.SUCCESS, result.resultType)
    }

    @Test
    fun verifyErrorWhenInValidCPFUserName() {

        //Given
        val userName = "invalidusername"
        val validateUser = ValidateUserName()

        //When
        val result = validateUser(userName)

        //Then
        Assert.assertEquals(ResultType.ERROR, result.resultType)
    }
}