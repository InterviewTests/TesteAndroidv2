package io.github.maikotrindade.bankapp

import io.github.maikotrindade.bankapp.base.network.BaseNetwork
import io.github.maikotrindade.bankapp.base.util.SharedPrefsUtil
import io.github.maikotrindade.bankapp.statement.domain.StatementsInterface
import io.github.maikotrindade.bankapp.statement.model.StatementsResponse
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(RobolectricTestRunner::class)
class StatementsScreenTest {

    @Test @Throws(Exception::class)
    fun fragment_Should_Remove_User_Data() {
        
        // userId
        SharedPrefsUtil.save(SharedPrefsUtil.userId, 1)
        SharedPrefsUtil.remove(SharedPrefsUtil.userId)
        val userId = SharedPrefsUtil.get(SharedPrefsUtil.userId, -1)
        Assert.assertEquals(userId, -1)

        // name
        SharedPrefsUtil.save(SharedPrefsUtil.name, "My Fake name")
        SharedPrefsUtil.remove(SharedPrefsUtil.name)
        val name = SharedPrefsUtil.get(SharedPrefsUtil.name, "")
        Assert.assertEquals(name, "")

        // agency
        SharedPrefsUtil.save(SharedPrefsUtil.agency, "My Fake agency")
        SharedPrefsUtil.remove(SharedPrefsUtil.agency)
        val agency = SharedPrefsUtil.get(SharedPrefsUtil.agency, "")
        Assert.assertEquals(agency, "")

        // bankAccount
        SharedPrefsUtil.save(SharedPrefsUtil.bankAccount, "My Fake bank account")
        SharedPrefsUtil.remove(SharedPrefsUtil.bankAccount)
        val bankAccount = SharedPrefsUtil.get(SharedPrefsUtil.bankAccount, "")
        Assert.assertEquals(bankAccount, "")

    }

    @Test
    fun fragment_Should_Returns_StatementList() {
        val userId = 1
        val responseCall = BaseNetwork.get(StatementsInterface::class.java).getStatements(userId)
        responseCall.enqueue(object : Callback<StatementsResponse> {
            override fun onResponse(call: Call<StatementsResponse>, response: Response<StatementsResponse>) {
                if (response.isSuccessful) {
                    val statementsResponse = response.body()
                    Assert.assertNotNull(statementsResponse)
                    Assert.assertNotEquals(statementsResponse?.statementList?.size, 0)
                }
            }
            override fun onFailure(call: Call<StatementsResponse>, t: Throwable) {}
        })
    }

}
