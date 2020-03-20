package dev.ornelas.bankapp.domain.model

import dev.ornelas.banckapp.domain.model.datatype.Result
import dev.ornelas.banckapp.domain.model.datatype.ResultType
import org.junit.Assert
import org.junit.Test

class ResultTest {

    @Test
    fun successResult() {

        //Given
        val sucessType = ResultType.SUCCESS
        val data = Any()

        //When
        val success = Result.success(data)

        //Then
        Assert.assertEquals(sucessType, success.resultType)
        Assert.assertNull(success.error)
    }

    @Test
    fun erroResult() {

        //Given
        val erroType = ResultType.ERROR
        val ex = RuntimeException()

        //When
        val failure = Result.error<Exception>(ex)

        //Then
        Assert.assertEquals(erroType, failure.resultType)
        Assert.assertEquals(ex, failure.error)
    }
}