package dev.ornelas.bankapp.login

import dev.ornelas.banckapp.domain.interactors.*
import dev.ornelas.banckapp.domain.model.User
import dev.ornelas.banckapp.domain.model.datatype.Result
import dev.ornelas.banckapp.domain.repository.UserRepository
import dev.ornelas.bankapp.BaseCoroutineTest
import dev.ornelas.bankapp.R
import dev.ornelas.bankapp.data.datasource.api.retrofit.exceptions.InvalidUserNamePasswordException
import dev.ornelas.bankapp.ui.login.LoginContract
import dev.ornelas.bankapp.ui.login.LoginPresenter
import dev.ornelas.bankapp.ui.login.LoginViewModel
import dev.ornelas.bankapp.ui.login.UserToUIMapper
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginPresenterTest : BaseCoroutineTest() {

    init {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @MockK
    lateinit var userRepository: UserRepository

    val user = User(id = 1, name = "teste", balance = 0.0, agency = "222", bankAccount = "222")

    val view = spyk<LoginContract.View>()

    var validateUserName = spyk(ValidateUserName())

    var validatePassword = spyk(ValidatePassword())

    val loginUser = LoginUser(userRepository)


    @Before
    fun setup() {
        coEvery {
            userRepository.GetUser(any(), any())
        } returns Result.success(user)

        every { validateUserName(any()) } returns Result.success(Unit)
        every { validatePassword(any()) } returns Result.success(Unit)
    }

    @Test
    fun verifyDisplayLoginResultSuccessfulFromLogin() = runBlockingTest {
        foo()
    }


    fun CoroutineScope.foo() {

        //Given
        val presenterTest = LoginPresenter(
            loginUser = loginUser,
            validateUserName = validateUserName,
            validatePassword = validatePassword,
            getLoggedUser = GetLoggedUser(userRepository),
            saveLoggedUser = SaveLoggedUser(userRepository),
            view = view
        )
        launch {  // CoroutineScope for launch is the TestCoroutineScope provided by runBlockingTest
            // ...

            //When
            presenterTest.onLogin("teste@teste.com", "teste")

            //Then
            coVerify {
                view.displayLoginResult(
                    LoginViewModel(success = UserToUIMapper.map(user))
                )
            }
        }
    }

    @Test
    fun verifyDisplayLoginResultFailFromLoginInvalidUserName() {
        runBlocking {
            launch(Dispatchers.Main) {
                val presenterTest = LoginPresenter(
                    loginUser = loginUser,
                    validateUserName = validateUserName,
                    validatePassword = validatePassword,
                    getLoggedUser = GetLoggedUser(userRepository),
                    saveLoggedUser = SaveLoggedUser(userRepository),
                    view = view
                )

                //Given
                every { validateUserName(any()) } returns Result.error()
                every { validatePassword(any()) } returns Result.error()

                //When
                presenterTest.onLogin("teste@teste.com", "teste")

                //Then
                coVerify {
                    view.displayLoginResult(
                        LoginViewModel(
                            passwordError = R.string.invalid_password,
                            usernameError = R.string.invalid_username
                        )
                    )
                }

            }
        }
    }
}
