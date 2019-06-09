package com.androiddeveloper.santanderTest.ui.login

import com.androiddeveloper.santanderTest.data.api.login.LoginRequest
import com.androiddeveloper.santanderTest.data.api.login.LoginService
import com.androiddeveloper.santanderTest.data.model.userdata.Data
import com.androiddeveloper.santanderTest.data.model.userdata.EncryptedData
import com.androiddeveloper.santanderTest.data.model.userdata.LoginError
import com.androiddeveloper.santanderTest.data.model.userdata.UserDao
import com.androiddeveloper.santanderTest.manager.EncryptManager
import com.androiddeveloper.santanderTest.shared.preferences.SharedPreferenceManager
import com.androiddeveloper.santanderTest.shared.preferences.SharedPreferencesEnum
import com.androiddeveloper.santanderTest.shared.database.MockDatabase
import io.reactivex.disposables.CompositeDisposable
import junit.framework.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
class LoginInteractorTest {

    val spy = LoginActivitySpy()
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
        db.close()
        compositeDisposable?.clear()
    }

    @Test
    @Throws(Exception::class)
    fun fetch_userData_success() {
        val disposable = LoginService.requestClientData(LoginRequest("12345678911", "1aA@9"))
            .subscribe { res ->
                res.body()?.userAccount?.let { data ->
                    if (res.code() == 200)
                        assertTrue("success", spy.isLoginSuccessCalled)
                }
            }
        compositeDisposable?.add(disposable)
    }

    @Test
    @Throws(Exception::class)
    fun fetch_userData_invalid_login() {
        val disposable = LoginService.requestClientData(LoginRequest("12345678911", "1aA"))
            .subscribe { res ->
                res.body()?.userAccount?.let {
                } ?: run {
                    res.body()?.error?.let { err ->
                        if (res.code() == 200)
                            assertTrue("error", spy.isLoginErrorCalled)
                    }
                }
            }
        compositeDisposable?.add(disposable)
    }

    @Test
    @Throws(Exception::class)
    fun fetch_userData_error() {
        val disposable = LoginService.requestMockClientData(LoginRequest("12345678911", "1aA"))
            .subscribe { res ->
                if (res.code() == 404)
                    assertTrue("error", spy.isLoginErrorCalled)
            }
        compositeDisposable?.add(disposable)
    }

    @Test
    @Throws(Exception::class)
    fun save_in_db() {
        val data = Data(1, "Jose", "123", "12", 1200.0)
        val encrypted = EncryptManager.encrypt(data)
        val dataEncrypted = EncryptedData()
        dataEncrypted.data = encrypted
        userDao.insertUserData(dataEncrypted)
        val disposable = userDao.getUser().subscribe { data ->
            val decrypted = EncryptManager.decrypt(data.data)
            assertTrue("dataWasSavedInDb", decrypted!!.agency == "12")
        }
        compositeDisposable?.add(disposable)
    }

    @Test
    @Throws(Exception::class)
    fun test_encrypt() {
        val data = Data(1, "Jose", "123", "12", 1200.0)
        val encrypted = EncryptManager.encrypt(data)
        assertNotNull(encrypted)
    }

    @Test
    @Throws(Exception::class)
    fun test_decrypt() {
        val data = Data(1, "Jose", "123", "12", 1200.0)
        val encrypted = EncryptManager.encrypt(data)
        val decrypted = EncryptManager.decrypt(encrypted)
        assertTrue(decrypted?.agency == "12")
    }

    @Test
    @Throws(Exception::class)
    fun test_sharedPreferences_user_is_logged() {
        SharedPreferenceManager.setLoggedUserData(
            RuntimeEnvironment.application,
            SharedPreferencesEnum.KEY_USER_LOGGED,
            true
        )
        assertTrue(
            "dataWasSavedInSP",
            SharedPreferenceManager.isUserLogged(RuntimeEnvironment.application, SharedPreferencesEnum.KEY_USER_LOGGED)
        )
    }

    @Test
    @Throws(Exception::class)
    fun test_sharedPreferences_last_username() {
        SharedPreferenceManager.setUsername(
            RuntimeEnvironment.application,
            SharedPreferencesEnum.KEY_USERNAME,
            "test_name"
        )
        assertEquals(
            "test_name",
            SharedPreferenceManager.getUsername(RuntimeEnvironment.application, SharedPreferencesEnum.KEY_USERNAME)
        )
    }

    @Test
    @Throws(Exception::class)
    fun test_encrypt_username() {
        val username = "12345678911"
        val encrypted = EncryptManager.encryptLastUser(username)
        assertNotNull(encrypted)
    }

    @Test
    @Throws(Exception::class)
    fun test_decrypt_username() {
        val username = "12345678911"
        val encrypted = EncryptManager.encryptLastUser(username)
        val decrypted = EncryptManager.decryptUsername(encrypted)
        assertTrue(decrypted == "12345678911")
    }

    class LoginActivitySpy : ILoginContract.LoginActInput {
        var isLoginErrorCalled = false
        var isLoginSuccessCalled = false
        var isOnInvalidLoginCalled = false
        var isOnValidLoginCalled = false

        override fun onLoginError(err: LoginError) {
            isLoginErrorCalled = true
        }

        override fun onLoginSuccess() {
            isLoginSuccessCalled = true
        }

        override fun onInvalidLogin(message: Int) {
            isOnInvalidLoginCalled = true
        }

        override fun onValidLogin(username: String, password: String) {
            isOnValidLoginCalled = true
        }

    }
}