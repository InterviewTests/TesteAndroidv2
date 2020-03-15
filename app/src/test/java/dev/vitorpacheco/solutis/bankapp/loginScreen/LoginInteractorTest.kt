package dev.vitorpacheco.solutis.bankapp.loginScreen

import io.mockk.MockKAnnotations
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class LoginInteractorTest {

    @MockK
    lateinit var worker: LoginWorker

    @SpyK
    var output = LoginPresenter()

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun `test doLogin with null values`() {
        val interactor = LoginInteractor()
        interactor.output = output
        interactor.worker = worker

        interactor.doLogin(LoginRequest(null, null))

        val expectedResponse = LoginResponse(
            error = UserError("Campo obrigatório", UserFormFields.USER)
        )

        verify {
            output.presentLoginData(expectedResponse)
        }

        confirmVerified(output)
    }

    @Test
    fun `test doLogin with null user`() {
        val interactor = LoginInteractor()
        interactor.output = output
        interactor.worker = worker

        interactor.doLogin(LoginRequest(null, "123456"))

        val expectedResponse = LoginResponse(
            error = UserError("Campo obrigatório", UserFormFields.USER)
        )

        verify {
            output.presentLoginData(expectedResponse)
        }

        confirmVerified(output)
    }

    @Test
    fun `test doLogin with null password`() {
        val interactor = LoginInteractor()
        interactor.output = output
        interactor.worker = worker

        interactor.doLogin(LoginRequest("test_user", null))

        val expectedResponse = LoginResponse(
            error = UserError("Campo obrigatório", UserFormFields.PASSWORD)
        )

        verify {
            output.presentLoginData(expectedResponse)
        }

        confirmVerified(output)
    }

    @Test
    fun `test doLogin with valid user and password`() {
        val interactor = LoginInteractor()
        interactor.output = output
        interactor.worker = worker

        interactor.doLogin(LoginRequest("test_user", "123456"))

//        verify {
//            output.presentLoginData(any())
//        }

        verify {
            worker.login(LoginRequest("test_user", "123456"), any())
        }

        confirmVerified(output)
        confirmVerified(worker)
    }

}
