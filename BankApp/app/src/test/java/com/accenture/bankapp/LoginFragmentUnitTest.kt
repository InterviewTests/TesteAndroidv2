package com.accenture.bankapp

import android.os.Looper.getMainLooper
import android.widget.Button
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.isVisible
import com.accenture.bankapp.screens.login.LoginFragment
import com.accenture.bankapp.screens.login.LoginFragmentInput
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf

@RunWith(RobolectricTestRunner::class)
class LoginFragmentUnitTest {
    @Test
    fun mainActivity_shouldNot_beNull() {
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().get()

        Assert.assertNotNull(mainActivity)
    }

    @Test
    fun loginFragment_shouldNot_beNull() {
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().get()
        val fragmentManager = mainActivity.supportFragmentManager
        val loginFragment = LoginFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.layoutContainer, loginFragment)
        fragmentTransaction.commit()
        shadowOf(getMainLooper()).idle()

        Assert.assertNotNull(loginFragment)
    }

    @Test
    fun inputUser_should_beValid() {
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().get()
        val fragmentManager = mainActivity.supportFragmentManager
        val loginFragment = LoginFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.layoutContainer, loginFragment)
        fragmentTransaction.commit()
        shadowOf(getMainLooper()).idle()

        val inputUser = loginFragment.inputUser
        val inputUserEditText = loginFragment.inputUserEditText

        inputUserEditText.setText("email@email.com")
        loginFragment.verifyUser()

        Assert.assertTrue(!inputUser.isErrorEnabled)
    }

    @Test
    fun inputUser_should_beInvalid() {
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().get()
        val fragmentManager = mainActivity.supportFragmentManager
        val loginFragment = LoginFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.layoutContainer, loginFragment)
        fragmentTransaction.commit()
        shadowOf(getMainLooper()).idle()

        val inputUser = loginFragment.inputUser
        val inputUserEditText = loginFragment.inputUserEditText

        inputUserEditText.setText("email.com")
        loginFragment.verifyUser()

        Assert.assertTrue(inputUser.isErrorEnabled)
    }

    @Test
    fun inputPassword_should_beValid() {
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().get()
        val fragmentManager = mainActivity.supportFragmentManager
        val loginFragment = LoginFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.layoutContainer, loginFragment)
        fragmentTransaction.commit()
        shadowOf(getMainLooper()).idle()

        val inputPassword = loginFragment.inputPassword
        val inputPasswordEditText = loginFragment.inputPasswordEditText

        inputPasswordEditText.setText("Password@123")
        loginFragment.verifyPassword()

        Assert.assertTrue(!inputPassword.isErrorEnabled)
    }

    @Test
    fun inputPassword_should_beInvalid() {
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().get()
        val fragmentManager = mainActivity.supportFragmentManager
        val loginFragment = LoginFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.layoutContainer, loginFragment)
        fragmentTransaction.commit()
        shadowOf(getMainLooper()).idle()

        val inputPassword = loginFragment.inputPassword
        val inputPasswordEditText = loginFragment.inputPasswordEditText

        inputPasswordEditText.setText("Password123")
        loginFragment.verifyPassword()

        Assert.assertTrue(inputPassword.isErrorEnabled)
    }

    @Test
    fun progressLoading_should_beEnable() {
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().get()
        val fragmentManager = mainActivity.supportFragmentManager
        val loginFragment = LoginFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.layoutContainer, loginFragment)
        fragmentTransaction.commit()
        shadowOf(getMainLooper()).idle()

        val progressLoading = loginFragment.progressLoading

        (loginFragment as LoginFragmentInput).enableLoading()

        Assert.assertTrue(progressLoading.isVisible)
    }

    @Test
    fun progressLoading_should_beDisable() {
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().get()
        val fragmentManager = mainActivity.supportFragmentManager
        val loginFragment = LoginFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.layoutContainer, loginFragment)
        fragmentTransaction.commit()
        shadowOf(getMainLooper()).idle()

        val progressLoading = loginFragment.progressLoading

        loginFragment.enableLoading()
        loginFragment.disableLoading()

        Assert.assertTrue(!progressLoading.isVisible)
    }

    @Test
    fun textLoginError_should_beEnable() {
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().get()
        val fragmentManager = mainActivity.supportFragmentManager
        val loginFragment = LoginFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.layoutContainer, loginFragment)
        fragmentTransaction.commit()
        shadowOf(getMainLooper()).idle()

        val textLoginError = loginFragment.textLoginError
        val testError = "Test error"

        loginFragment.enableError(testError)

        Assert.assertEquals(textLoginError.text, testError)
        Assert.assertTrue(textLoginError.isVisible)
    }

    @Test
    fun textLoginError_should_beDisable() {
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().get()
        val fragmentManager = mainActivity.supportFragmentManager
        val loginFragment = LoginFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.layoutContainer, loginFragment)
        fragmentTransaction.commit()
        shadowOf(getMainLooper()).idle()

        val textLoginError = loginFragment.textLoginError
        val testError = "Test error"

        loginFragment.enableError(testError)
        loginFragment.disableError()

        Assert.assertEquals(textLoginError.text, "")
        Assert.assertTrue(!textLoginError.isVisible)
    }

    @Test
    fun buttonLogin_should_performLogin() {
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().get()
        val fragmentManager = mainActivity.supportFragmentManager
        val loginFragment = LoginFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.layoutContainer, loginFragment)
        fragmentTransaction.commit()
        shadowOf(getMainLooper()).idle()

        val inputUserEditText = loginFragment.inputUserEditText
        val inputPasswordEditText = loginFragment.inputPasswordEditText
        val buttonLogin = loginFragment.buttonLogin

        inputUserEditText.setText("email@email.com")
        inputPasswordEditText.setText("Password@123")
        buttonLogin.performClick()

        runBlocking {
            withContext(Dispatchers.Default) {
                delay(5000L)
            }
        }
        shadowOf(getMainLooper()).idle()

        Assert.assertNotNull(mainActivity.findViewById<LinearLayoutCompat>(R.id.layoutContainer).findViewById<Button>(R.id.buttonLogout))
    }

    @Test
    fun buttonLogin_shouldNot_performLogin() {
        val mainActivity = Robolectric.buildActivity(MainActivity::class.java).create().start().resume().get()
        val fragmentManager = mainActivity.supportFragmentManager
        val loginFragment = LoginFragment()
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.layoutContainer, loginFragment)
        fragmentTransaction.commit()
        shadowOf(getMainLooper()).idle()

        val inputUserEditText = loginFragment.inputUserEditText
        val inputPasswordEditText = loginFragment.inputPasswordEditText
        val buttonLogin = loginFragment.buttonLogin

        inputUserEditText.setText("emailemail.com")
        inputPasswordEditText.setText("Password123")
        buttonLogin.callOnClick()

        runBlocking {
            withContext(Dispatchers.Default) {
                delay(5000L)
            }
        }
        shadowOf(getMainLooper()).idle()

        Assert.assertNotNull(mainActivity.findViewById<LinearLayoutCompat>(R.id.layoutContainer).findViewById<Button>(R.id.buttonLogin))
    }
}