package com.androiddeveloper.santanderTest.ui.login

import com.androiddeveloper.santanderTest.data.model.statements.ItemDTO
import com.androiddeveloper.santanderTest.data.model.statements.Statements
import com.androiddeveloper.santanderTest.data.model.userdata.Data
import com.androiddeveloper.santanderTest.data.model.userdata.DataDTO
import com.androiddeveloper.santanderTest.ui.statements.IStatementContract
import com.androiddeveloper.santanderTest.ui.statements.StatementPresenterInput
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.lang.ref.WeakReference

@RunWith(RobolectricTestRunner::class)
class StatementPresenterTest {

    val presenter = StatementPresenterInput()
    val spy = BankInfoSpy()

    @Before
    @Throws(Exception::class)
    fun setup() {
        val weakReference = WeakReference<IStatementContract.StatementInput>(spy)
        presenter.bankInfo = weakReference
    }

    @Test
    @Throws(Exception::class)
    fun test_parse_data() {
        val data = Data(1, "Jose", "123", "012314564", 1200.0)
        presenter.parseData(data)
        Assert.assertTrue("isShowDataCalled", spy.isShowDataCalled)
    }

    @Test
    @Throws(Exception::class)
    fun test_prepare_balance() {
        val statements = Statements(arrayListOf())
        presenter.prepareBalance(statements)
        Assert.assertTrue("isShowBalanceListCalled", spy.isShowBalanceListCalled)
    }

    @Test
    @Throws(Exception::class)
    fun test_format_date() {
        Assert.assertEquals(
            "31/05/2019", presenter.formatDate("2019-05-31")
        )
    }

    @Test
    @Throws(Exception::class)
    fun test_format_bank_account() {
        Assert.assertEquals(
            "01.231456-4", presenter.formatBankAccount("012314564")
        )
    }

    @Test
    @Throws(Exception::class)
    fun test_format_balance() {
        Assert.assertEquals(
            "BRL 1.20", presenter.formatBalance(1.2)
        )
    }

    class BankInfoSpy : IStatementContract.StatementInput {
        var isOnUserDbErrorCalled = false
        var isShowDataCalled = false
        var isOnBalanceResponseErrorCalled = false
        var isShowBalanceListCalled = false
        var isOnDeleteDbSuccessCalled = false

        override fun onUserDbError(message: String) {
            isOnUserDbErrorCalled = true
        }

        override fun showData(data: DataDTO) {
            isShowDataCalled = true
        }

        override fun onBalanceResponseError() {
            isOnBalanceResponseErrorCalled = true
        }

        override fun showBalanceList(balanceList: ArrayList<ItemDTO>) {
            isShowBalanceListCalled = true
        }

        override fun onDeleteDbSuccess() {
            isOnDeleteDbSuccessCalled = true
        }
    }
}