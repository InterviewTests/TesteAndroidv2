package br.com.douglas.fukuhara.bank.home.interactor

import br.com.douglas.fukuhara.bank.home.presenter.HomePresenter
import br.com.douglas.fukuhara.bank.network.RestClient
import br.com.douglas.fukuhara.bank.network.vo.LoginVo
import br.com.douglas.fukuhara.bank.network.vo.StatementListVo
import br.com.douglas.fukuhara.bank.utils.TestUtils
import io.mockk.*
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.Executor

class HomeInteractorTest {
    private val mClient: RestClient = mockk(relaxed = true)
    private val mOutput: HomePresenter = mockk(relaxed = true)
    private lateinit var mInteractor: HomeInteractor

    @Before
    fun setUp() {
        object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, false)
            }
        }.run {
            RxJavaPlugins.setInitIoSchedulerHandler { this }
            RxJavaPlugins.setInitComputationSchedulerHandler { this }
            RxJavaPlugins.setInitNewThreadSchedulerHandler { this }
            RxJavaPlugins.setInitSingleSchedulerHandler { this }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { this }
        }

        mInteractor = HomeInteractor(mClient)
        mInteractor.setPresenter(mOutput)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `Given An Empty UserAccount Object In SetHomeHeader, Then Interactor Should Notify Presenter Of No Info`() {
        mInteractor.setHomeHeader(null)

        verify(exactly = 1) {
            mOutput.noUserInfo()
        }

        confirmVerified(mOutput)
    }

    @Test
    fun `Given A Valid UserAccount Object, Then Interactor Should Pass The Necessary Info To Presenter`() {
        val loginVo = TestUtils.fromJsonToObj("json/successful_user_account.json", LoginVo::class.java)
        val userAccount = loginVo.userAccount

        mInteractor.setHomeHeader(userAccount)

        verify(exactly = 1) {
            mOutput.setHomeHeaderInfo(
                    userAccount.name,
                    userAccount.agency,
                    userAccount.bankAccount,
                    userAccount.balance)
        }

        confirmVerified(mOutput)
    }

    @Test
    fun `Given An Empty UserAccount Info In FetchUserData, Then Interactor Should Notify Presenter Of No Info`() {
        mInteractor.fetchUserData()

        verify(exactly = 1) {
            mOutput.noUserInfo()
        }

        confirmVerified(mOutput)
    }

    @Test
    fun `Given A Valid UserAccount Info In FetchUserData, Then Rest Call Should Be Done To Get Statement List`() {
        val loginVo = TestUtils.fromJsonToObj("json/successful_user_account.json", LoginVo::class.java)
        val userAccount = loginVo.userAccount

        mInteractor.setHomeHeader(userAccount)
        mInteractor.fetchUserData()

        verify(inverse = true) {
            mOutput.noUserInfo()
        }
        verify(exactly = 1) {
            mOutput.setHomeHeaderInfo(any(), any(), any(), any())
            mOutput.onDataFetch()
        }

        confirmVerified(mOutput)
    }

    @Test
    fun `Given A Successful Response And Complete Response, Then Presenter Should Be Informed With The Statement List`() {
        val loginVo = TestUtils.fromJsonToObj("json/successful_user_account.json", LoginVo::class.java)
        val userAccount = loginVo.userAccount
        val statementListVo = TestUtils.fromJsonToObj("json/successful_statement_list.json", StatementListVo::class.java)
        every { mClient.api.getUserData(userAccount.userId) } returns Observable.just(statementListVo)

        mInteractor.setHomeHeader(userAccount)
        mInteractor.fetchUserData()

        verify(exactly = 1) {
            mOutput.setHomeHeaderInfo(any(), any(), any(), any())
            mOutput.onDataFetch()
            mOutput.setUserDataInfo(statementListVo.statementList)
        }

        confirmVerified(mOutput)
    }

    @Test
    fun `Given A Successful Response With Empty List, Then Presenter Should Be Informed With No Available Data`() {
        val loginVo = TestUtils.fromJsonToObj("json/successful_user_account.json", LoginVo::class.java)
        val userAccount = loginVo.userAccount
        val statementListVo = TestUtils.fromJsonToObj("json/successful_statement_empty_list.json", StatementListVo::class.java)
        every { mClient.api.getUserData(userAccount.userId) } returns Observable.just(statementListVo)

        mInteractor.setHomeHeader(userAccount)
        mInteractor.fetchUserData()

        verify(exactly = 1) {
            mOutput.setHomeHeaderInfo(any(), any(), any(), any())
            mOutput.onDataFetch()
            mOutput.setNoUserDataAvailable()
        }

        confirmVerified(mOutput)
    }

    @Test
    fun `Given A Successful Response But With Server Error, Then Presenter Should Be Informed With Error`() {
        val loginVo = TestUtils.fromJsonToObj("json/successful_user_account.json", LoginVo::class.java)
        val userAccount = loginVo.userAccount
        val statementListVo = TestUtils.fromJsonToObj("json/wrong_user_or_password.json", StatementListVo::class.java)
        val errorMessage = statementListVo.userError.message
        val errorCode = statementListVo.userError.code
        every { mClient.api.getUserData(userAccount.userId) } returns Observable.just(statementListVo)

        mInteractor.setHomeHeader(userAccount)
        mInteractor.fetchUserData()

        verify(exactly = 1) {
            mOutput.setHomeHeaderInfo(any(), any(), any(), any())
            mOutput.onDataFetch()
            mOutput.showUserDataErrorMessage("$errorMessage ($errorCode)")
        }

        confirmVerified(mOutput)
    }

    @Test
    fun `Given A Generic Rest Server Error, Then Presenter Should Be Informed With About Generic Error`() {
        val loginVo = TestUtils.fromJsonToObj("json/successful_user_account.json", LoginVo::class.java)
        val userAccount = loginVo.userAccount
        every { mClient.api.getUserData(userAccount.userId) } returns TestUtils.getGenericServer()

        mInteractor.setHomeHeader(userAccount)
        mInteractor.fetchUserData()

        verify(exactly = 1) {
            mOutput.setHomeHeaderInfo(any(), any(), any(), any())
            mOutput.onDataFetch()
            mOutput.showUserDataGenericError()
        }

        confirmVerified(mOutput)
    }

    @Test
    fun `Given A Rest Server Error With Message, Then Presenter Should Be Informed With Error Message From Server`() {
        val loginVo = TestUtils.fromJsonToObj("json/successful_user_account.json", LoginVo::class.java)
        val userAccount = loginVo.userAccount
        val serverMessageError = "Algo inesperado aconteceu. Tente novamente mais tarde"
        every { mClient.api.getUserData(userAccount.userId) } returns TestUtils.getServerErrorWithMessage(serverMessageError)

        mInteractor.setHomeHeader(userAccount)
        mInteractor.fetchUserData()

        verify(exactly = 1) {
            mOutput.setHomeHeaderInfo(any(), any(), any(), any())
            mOutput.onDataFetch()
            mOutput.showUserDataErrorMessage(serverMessageError)
        }

        confirmVerified(mOutput)
    }
}