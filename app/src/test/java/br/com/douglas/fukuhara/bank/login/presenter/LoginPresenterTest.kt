package br.com.douglas.fukuhara.bank.login.presenter

import br.com.douglas.fukuhara.bank.R
import br.com.douglas.fukuhara.bank.login.Contract
import br.com.douglas.fukuhara.bank.login.ui.LoginActivity
import br.com.douglas.fukuhara.bank.network.vo.UserAccount
import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.lang.ref.WeakReference
import java.math.BigDecimal

class LoginPresenterTest {
    private val mActivity: LoginActivity = mockk(relaxed = true)
    private lateinit var mOutput: WeakReference<Contract.LoginActivityInput>
    private lateinit var mPresenter: LoginPresenter

    @Before
    fun setUp() {
        mPresenter = LoginPresenter()
        mOutput = spyk(WeakReference<Contract.LoginActivityInput>(mActivity))
        mPresenter.setOutput(mOutput)
    }

    @After
    fun tearDown() {
        mOutput.clear()
        unmockkAll()
    }

    @Test
    fun `When Interactor Informs Empty Username, Then Presenter Must Send Username Resource To Activity Alert Dialog`() {
        mPresenter.emptyUsername()

        verify(exactly = 1) {
            mOutput.get()?.notifyResourceErrorToUser(R.string.login_empty_username)
        }

        mOutput.get()?.let { confirmVerified(it) }
    }

    @Test
    fun `When Interactor Informs Empty Password, Then Presenter Must Send Empty Resource To Activity Alert Dialog`() {
        mPresenter.emptyPassword()

        verify(exactly = 1) {
            mOutput.get()?.notifyResourceErrorToUser(R.string.login_empty_password)
        }

        mOutput.get()?.let { confirmVerified(it) }
    }

    @Test
    fun `When Interactor Informs Invalid CPF, Then Presenter Must Send Invalid CPF To Activity Alert Dialog`() {
        mPresenter.invalidCpf()

        verify {
            mOutput.get()?.notifyResourceErrorToUser(R.string.login_invalid_cpf)
        }

        mOutput.get()?.let { confirmVerified(it) }
    }

    @Test
    fun `When Interactor Informs Invalid CPF Or Email, Then Presenter Must Send Invalid CPF Or Email To Activity Alert Dialog`() {
        mPresenter.invalidEmailCpf()

        verify(exactly = 1) {
            mOutput.get()?.notifyResourceErrorToUser(R.string.login_invalid_email_cpf)
        }

        mOutput.get()?.let { confirmVerified(it) }
    }

    @Test
    fun `When Interactor Informs Wrong Password Pattern, Then Presenter Must Send Wrong Password Pattern To Activity Alert Dialog`() {
        mPresenter.invalidPasswordType()

        verify(exactly = 1) {
            mOutput.get()?.notifyResourceErrorToUser(R.string.login_invalid_password_pattern)
        }

        mOutput.get()?.let { confirmVerified(it) }
    }

    @Test
    fun `When Interactor Informs Generic Error, Then Presenter Must Send Generic Error To Activity Alert Dialog`() {
        mPresenter.showLoginGenericError()

        verify {
            mOutput.get()?.notifyResourceErrorToUser(R.string.login_generic_error)
        }

        mOutput.get()?.let { confirmVerified(it) }
    }

    @Test
    fun `When Interactor Informs Error From Server, Then Presenter Must Send This Server Message Error To Activity Alert Dialog`() {
        val stringMessage = "Server Message"
        mPresenter.showLoginErrorMessage(stringMessage)

        verify(exactly = 1) {
            mOutput.get()?.notifyErrorToUser(stringMessage)
        }

        mOutput.get()?.let { confirmVerified(it) }
    }

    @Test
    fun `When Interactor Gets The UserAccount Object, Then Presenter Must Pass this Obj To Activity To Route To The Next Screen`() {
        val referenceUserAccount = UserAccount(10, "Givenname Surname", "0001", "001", BigDecimal(10))
        mPresenter.onSuccessfulLoginResponse(referenceUserAccount)

        verify {
            mOutput.get()?.onSuccessfulLogin(referenceUserAccount)
        }

        mOutput.get()?.let { confirmVerified(it) }
    }
}