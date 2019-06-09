package com.androiddeveloper.santanderTest.ui.login

import com.androiddeveloper.santanderTest.data.api.statement.StatementService
import com.androiddeveloper.santanderTest.data.model.statements.ItemDTO
import com.androiddeveloper.santanderTest.data.model.statements.Statements
import com.androiddeveloper.santanderTest.data.model.userdata.Data
import com.androiddeveloper.santanderTest.data.model.userdata.DataDTO
import com.androiddeveloper.santanderTest.data.model.userdata.UserDao
import com.androiddeveloper.santanderTest.manager.EncryptManager
import com.androiddeveloper.santanderTest.shared.database.MockDatabase
import com.androiddeveloper.santanderTest.ui.statements.IStatementContract
import io.reactivex.disposables.CompositeDisposable
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
class StatementInteractorTest {

    val activitySpy = BankInfoActivitySpy()
    val presenterSpy = BankInfoPresenterSpy()
    lateinit var userDao: UserDao
    lateinit var db: MockDatabase
    var compositeDisposable: CompositeDisposable? = null

    @Before
    @Throws(Exception::class)
    fun setup() {
        db = MockDatabase.getInstance(RuntimeEnvironment.application)
        userDao = db.userDataDao()
    }

    @After
    @Throws(IOException::class)
    fun close() {
        compositeDisposable?.clear()
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun test_get_data_from_db_success() {
        val disposable = userDao.getUser()
            .subscribe { res ->
                assertTrue("isParseDataCalled", presenterSpy.isParseDataCalled)
            }
        compositeDisposable?.add(disposable)
    }

    @Test
    @Throws(Exception::class)
    fun test_get_data_from_db_error() {
        userDao.deleteUser()
        val disposable = userDao.getUser()
            .subscribe({}, { err -> assertTrue("isOnUserDbErrorCalled", activitySpy.isUserDbErrorCalled) })

        compositeDisposable?.add(disposable)
    }

    @Test
    @Throws(Exception::class)
    fun test_request_statement_success() {
        val disposable = StatementService.requestStatement(1)
            .subscribe { res ->
                if (res.code() == 200)
                    assertTrue("isPrepareBalanceCalled", presenterSpy.isPrepareBalanceCalled)
            }

        compositeDisposable?.add(disposable)
    }

    @Test
    @Throws(Exception::class)
    fun test_request_statement_error() {
        val disposable = StatementService.requestMockStatement(1)
            .subscribe { res ->
                if (res.code() == 404)
                    assertTrue("is404Error", true)
            }
        compositeDisposable?.add(disposable)
    }

    class BankInfoActivitySpy : IStatementContract.StatementInput {

        var isUserDbErrorCalled = false
        var isShowDataCalled = false
        var isOnBalanceResponseErrorCalled = false
        var isShowBalanceListCalled = false
        var isOnDeleteDbSuccessCalled = false

        override fun onUserDbError(message: String) {
            isUserDbErrorCalled = true
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

    class BankInfoPresenterSpy : IStatementContract.StatementPresenterInput {

        var isParseDataCalled = false
        var isPrepareBalanceCalled = false

        override fun parseData(data: Data) {
            isParseDataCalled = true
        }

        override fun prepareBalance(statements: Statements) {
            isPrepareBalanceCalled = true
        }
    }
}