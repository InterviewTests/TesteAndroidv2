package com.jeanjnap.data.mapper

import com.jeanjnap.data.model.response.UserAccountResponse
import com.jeanjnap.data.model.response.UserDataResponse
import com.jeanjnap.domain.entity.UserAccount
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class UserDataResponseToUserAccountMapperTest : BaseMapperTest<UserDataResponse, UserAccount>(
    UserDataResponseToUserAccountMapper::class.java.name
) {
    override fun mockClassIn() = UserDataResponse(
        userAccount = UserAccountResponse(
            userId = 1L,
            name = "Jose da Silva Teste",
            bankAccount = "1234",
            agency = "123456",
            balance = BigDecimal.TEN
        )
    )

    override fun mockClassOut() = UserAccount(
        userId = 1L,
        name = "Jose da Silva Teste",
        bankAccount = "1234",
        agency = "123456",
        balance = BigDecimal.TEN
    )

    @Test
    fun withNullValues() {
        assertEquals(
            mapper.transform(
                UserDataResponse(userAccount = null)
            ),
            UserAccount(
                userId = null,
                name = null,
                bankAccount = null,
                agency = null,
                balance = null
            )
        )
    }
}