package br.com.douglas.fukuhara.bank.login.interactor

import br.com.douglas.fukuhara.bank.login.presenter.LoginPresenter
import br.com.douglas.fukuhara.bank.network.RestClient
import br.com.douglas.fukuhara.bank.network.vo.LoginVo
import br.com.douglas.fukuhara.bank.persistance.Storage
import br.com.douglas.fukuhara.bank.utils.TestUtils.fromJsonToObj
import br.com.douglas.fukuhara.bank.utils.TestUtils.getGenericServer
import br.com.douglas.fukuhara.bank.utils.TestUtils.getServerErrorWithMessage
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

class LoginInteractorTest {
    private val mClient: RestClient = mockk(relaxed = true)
    private val mPresenter: LoginPresenter = mockk(relaxed = true)
    private val mStorage: Storage = mockk(relaxed = true)
    private lateinit var mInteractor: LoginInteractor

    companion object {
        const val EMPTY_USERNAME = ""
        const val EMPTY_PASSWORD = ""
        const val VALID_PATTERN_EMAIL_USERNAME = "address@domain.com"
        const val VALID_PATTERN_FORMATTED_CPF_USERNAME = "123.456.789-09"
        const val VALID_PATTERN_PLAIN_CPF_USERNAME = "12345678909"
        const val VALID_PATTERN_PASSWORD = "P@s5"
        const val INVALID_PASSWORD_PATTERN_NO_CAPITAL = "p@s5"
        const val INVALID_PASSWORD_PATTERN_NO_SPECIAL = "Pas5"
        const val INVALID_PASSWORD_PATTERN_NO_NUMBER = "P@ss"
        const val INVALID_FORMATTED_CPF = "123.456.789-01"
        const val INVALID_PLAIN_CPF = "12345678901"
        const val INVALID_EMAIL_OR_CPF_USERNAME = "123456789@email"
    }

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

        mInteractor = LoginInteractor(mClient, mStorage)
        mInteractor.setPresenter(mPresenter)
    }

    @Test
    fun `Given An Empty Username, Then Presenter Should Notify Activity About Empty Username`() {
        mInteractor.onLogin(EMPTY_USERNAME, EMPTY_PASSWORD)

        verify(exactly = 1) {
            mPresenter.emptyUsername()
        }

        confirmVerified(mPresenter)
    }

    @Test
    fun `Given A Non Empty Username But Empty Password, Then Presenter Should Notify Activity About Empty Password`() {
        mInteractor.onLogin(VALID_PATTERN_EMAIL_USERNAME, EMPTY_PASSWORD)

        verify(exactly = 1) {
            mPresenter.emptyPassword()
        }

        confirmVerified(mPresenter)
    }

    @Test
    fun `Given An Invalid Formatted CPF Value, Then Presenter Should Notify Activity About Wrong CPF Number`() {
        mInteractor.onLogin(INVALID_FORMATTED_CPF, VALID_PATTERN_PASSWORD)

        verify(exactly = 1) {
            mPresenter.invalidCpf()
        }

        confirmVerified(mPresenter)
    }

    @Test
    fun `Given An Invalid Plain CPF Value, Then Presenter Should Notify Activity About Wrong CPF Number`() {
        mInteractor.onLogin(INVALID_PLAIN_CPF, VALID_PATTERN_PASSWORD)

        verify(exactly = 1) {
            mPresenter.invalidCpf()
        }

        confirmVerified(mPresenter)
    }

    @Test
    fun `Given An Username That Is Not Email Or Cpf Pattern, Then Presenter Should Notify Activity About Wrong Username Pattern`() {
        mInteractor.onLogin(INVALID_EMAIL_OR_CPF_USERNAME, VALID_PATTERN_PASSWORD)

        verify(exactly = 1) {
            mPresenter.invalidEmailCpf()
        }

        confirmVerified(mPresenter)
    }

    @Test
    fun `Given A Password Without Captial Letter, Then Presenter Should Notify Activity About Wrong Password Pattern`() {
        mInteractor.onLogin(VALID_PATTERN_FORMATTED_CPF_USERNAME, INVALID_PASSWORD_PATTERN_NO_CAPITAL)

        verify(exactly = 1) {
            mPresenter.invalidPasswordType()
        }

        confirmVerified(mPresenter)
    }

    @Test
    fun `Given A Password Without Number, Then Presenter Should Notify Activity About Wrong Password Pattern`() {
        mInteractor.onLogin(VALID_PATTERN_PLAIN_CPF_USERNAME, INVALID_PASSWORD_PATTERN_NO_NUMBER)

        verify(exactly = 1) {
            mPresenter.invalidPasswordType()
        }

        confirmVerified(mPresenter)
    }

    @Test
    fun `Given A Password Without Special Character, Then Presenter Should Notify Activity About Wrong Password Pattern`() {
        mInteractor.onLogin(VALID_PATTERN_EMAIL_USERNAME, INVALID_PASSWORD_PATTERN_NO_SPECIAL)

        verify(exactly = 1) {
            mPresenter.invalidPasswordType()
        }

        confirmVerified(mPresenter)
    }

    @Test
    fun `Given A Server Error Without Message, Then Presenter Should Notify Activity To Display A Generic Server Error`() {
        every {
            mClient.api.doLogin(VALID_PATTERN_EMAIL_USERNAME, VALID_PATTERN_PASSWORD)
        } returns getGenericServer()

        mInteractor.onLogin(VALID_PATTERN_EMAIL_USERNAME, VALID_PATTERN_PASSWORD)

        verify(exactly = 1) {
            mPresenter.showLoginGenericError()
        }

        confirmVerified(mPresenter)
    }

    @Test
    fun `Given A Server Error With Message, Then Presenter Should Notify Activity To Display A Generic Server Error`() {
        val messageErrorFromServer = "Something Just Went Wrong"
        every {
            mClient.api.doLogin(VALID_PATTERN_EMAIL_USERNAME, VALID_PATTERN_PASSWORD)
        } returns getServerErrorWithMessage(messageErrorFromServer)

        mInteractor.onLogin(VALID_PATTERN_EMAIL_USERNAME, VALID_PATTERN_PASSWORD)

        verify(exactly = 1) {
            mPresenter.showLoginErrorMessage(messageErrorFromServer)
        }

        confirmVerified(mPresenter)
    }

    @Test
    fun `Given A Server Response With Non Empty User Account, Then Presenter Should Pass User Account To Activity`() {
        val loginVoResponse = fromJsonToObj("json/successful_user_account.json", LoginVo::class.java)
        val userAccount = loginVoResponse.userAccount
        every {
            mClient.api.doLogin(VALID_PATTERN_EMAIL_USERNAME, VALID_PATTERN_PASSWORD)
        } returns Observable.just(loginVoResponse)

        mInteractor.onLogin(VALID_PATTERN_EMAIL_USERNAME, VALID_PATTERN_PASSWORD)

        verify(exactly = 1) {
            mPresenter.onSuccessfulLoginResponse(userAccount)
        }

        confirmVerified(mPresenter)
    }

    @Test
    fun `Given A Server Response With Non Error Response, Then Presenter Should Notify Activity With Error Message And Error Code`() {
        val loginVoResponse = fromJsonToObj("json/wrong_user_or_password.json", LoginVo::class.java)
        val userError = loginVoResponse.userError
        val formattedErrorMessage = "${userError.message} (${userError.code})"
        every {
            mClient.api.doLogin(VALID_PATTERN_EMAIL_USERNAME, VALID_PATTERN_PASSWORD)
        } returns Observable.just(loginVoResponse)

        mInteractor.onLogin(VALID_PATTERN_EMAIL_USERNAME, VALID_PATTERN_PASSWORD)

        verify(exactly = 1) {
            mPresenter.showLoginErrorMessage(formattedErrorMessage)
        }

        confirmVerified(mPresenter)
    }


    @Test
    fun `Given A Server Error Without Message, Then The Username Should Not Be Stored In SharedPreferences`() {
        every {
            mClient.api.doLogin(VALID_PATTERN_EMAIL_USERNAME, VALID_PATTERN_PASSWORD)
        } returns getGenericServer()

        mInteractor.onLogin(VALID_PATTERN_EMAIL_USERNAME, VALID_PATTERN_PASSWORD)

        verify(inverse = true) {
            mStorage.saveLogin(any())
        }

        confirmVerified(mStorage)
    }

    @Test
    fun `Given A Server Error With Message, Then The Username Should Not Be Stored In SharedPreferences`() {
        val messageErrorFromServer = "Something Just Went Wrong"
        every {
            mClient.api.doLogin(VALID_PATTERN_EMAIL_USERNAME, VALID_PATTERN_PASSWORD)
        } returns getServerErrorWithMessage(messageErrorFromServer)

        mInteractor.onLogin(VALID_PATTERN_EMAIL_USERNAME, VALID_PATTERN_PASSWORD)

        verify(inverse = true) {
            mStorage.saveLogin(any())
        }

        confirmVerified(mStorage)
    }

    @Test
    fun `Given A Server Response With Non Empty User Account, Then The Username Should Be Stored In SharedPreferences`() {
        val loginVoResponse = fromJsonToObj("json/successful_user_account.json", LoginVo::class.java)
        every {
            mClient.api.doLogin(VALID_PATTERN_EMAIL_USERNAME, VALID_PATTERN_PASSWORD)
        } returns Observable.just(loginVoResponse)

        mInteractor.onLogin(VALID_PATTERN_EMAIL_USERNAME, VALID_PATTERN_PASSWORD)

        verify(exactly = 1) {
            mStorage.saveLogin(any())
        }

        confirmVerified(mStorage)
    }

    @Test
    fun `Given A Server Response With Non Error Response, Then The Username Should Not Be Stored In SharedPreferences`() {
        val loginVoResponse = fromJsonToObj("json/wrong_user_or_password.json", LoginVo::class.java)
        every {
            mClient.api.doLogin(VALID_PATTERN_EMAIL_USERNAME, VALID_PATTERN_PASSWORD)
        } returns Observable.just(loginVoResponse)

        mInteractor.onLogin(VALID_PATTERN_EMAIL_USERNAME, VALID_PATTERN_PASSWORD)

        verify(inverse = true) {
            mStorage.saveLogin(any())
        }

        confirmVerified(mStorage)
    }

    @Test
    fun `Given A Non Empty Username Returned From SharedPreferences, Then Presenter Should Be Notified About Username`() {
        val returnedUsername = "address@domain.com"
        every { mStorage.login } returns returnedUsername

        mInteractor.checkForPreviousLoggedUser()

        verify(exactly = 1) {
            mPresenter.setLoginFromPreviousLoggedUser(returnedUsername)
        }

        confirmVerified(mPresenter)
    }

    @Test
    fun `Given An Empty Username Returned From SharedPreferences, Then Presenter Should Be Notified About Username`() {
        val returnedUsername = ""
        every { mStorage.login } returns returnedUsername

        mInteractor.checkForPreviousLoggedUser()

        verify(inverse = true) {
            mPresenter.setLoginFromPreviousLoggedUser(returnedUsername)
        }

        confirmVerified(mPresenter)
    }

    @Test
    fun `Given A Null Username Returned From SharedPreferences, Then Presenter Should Be Notified About Username`() {
        val returnedUsername: String? = null
        every { mStorage.login } returns returnedUsername

        mInteractor.checkForPreviousLoggedUser()

        verify(inverse = true) {
            mPresenter.setLoginFromPreviousLoggedUser(returnedUsername)
        }

        confirmVerified(mPresenter)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}