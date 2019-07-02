package io.github.maikotrindade.bankapp

import io.github.maikotrindade.bankapp.base.network.BaseNetwork
import io.github.maikotrindade.bankapp.base.ui.MainActivity
import io.github.maikotrindade.bankapp.login.ui.LoginFragment
import io.github.maikotrindade.bankapp.login.domain.LoginInterface
import io.github.maikotrindade.bankapp.login.model.LoginResponse
import io.github.maikotrindade.bankapp.login.model.User
import kotlinx.android.synthetic.main.fragment_login.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@RunWith(RobolectricTestRunner::class)
class LoginScreenTest {

    private lateinit var activity: MainActivity
    private lateinit var fragment: LoginFragment
    private lateinit var validPassword: String
    private lateinit var validEmail: String

    @Before
    @Throws(Exception::class)
    fun setUp() {
        activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .resume()
            .get()

        val navHostFragment = activity.supportFragmentManager.findFragmentById(R.id.navHost)
        fragment = navHostFragment!!.childFragmentManager.fragments[0] as LoginFragment

        validEmail = "validEmail@bankapp.com"
        validPassword = "qweQ1!"

    }

    @Test
    fun logInFragment_Should_be_Created() {
        val isCreated = fragment.isAdded
        Assert.assertTrue(isCreated)
    }

    @Test
    fun validate_Login_Email_Field() {
        val presenter = fragment.interactor?.presenter
        val userWrongEmail = User("wrongemail@", validPassword)
        presenter?.logIn(userWrongEmail)

        val errorInputMessage = fragment.txtInputEmail.error.toString()
        val errorMessage = fragment.getString(R.string.error_invalid_email)
        Assert.assertEquals(errorInputMessage, errorMessage)
    }

    @Test
    fun validate_Login_Password_Field() {
        val presenter = fragment.interactor?.presenter
        val userWrongPassword = User(validEmail, "wrongpassword")
        presenter?.logIn(userWrongPassword)

        val errorInputMessage = fragment.txtInputPassword.error.toString()
        val errorMessage = fragment.getString(R.string.error_invalid_password)
        Assert.assertEquals(errorInputMessage, errorMessage)
    }

    @Test
    fun validate_Login_Request() {
        val loginResponseCall = BaseNetwork.get(LoginInterface::class.java).performLogIn(User(validEmail, validPassword))
        loginResponseCall.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    Assert.assertNotNull(loginResponse?.userAccount?.name)
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {}
        })
    }

    @Test
    fun validate_Wrong_Login_Request() {
        val loginResponseCall = BaseNetwork.get(LoginInterface::class.java).performLogIn(User("", ""))
        loginResponseCall.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    Assert.assertNotNull(loginResponse?.error?.code)
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {}
        })
    }

}
