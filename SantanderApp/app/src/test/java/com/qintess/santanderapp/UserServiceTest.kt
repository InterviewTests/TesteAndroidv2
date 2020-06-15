package com.qintess.santanderapp

import android.os.Build
import com.qintess.santanderapp.model.CredentialsModel
import com.qintess.santanderapp.model.UserModel
import com.qintess.santanderapp.service.*
import org.json.JSONObject
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.runners.MockitoJUnitRunner
import org.robolectric.annotation.Config

@RunWith(MockitoJUnitRunner::class)
@Config(sdk = [Build.VERSION_CODES.KITKAT])
class UserServiceTest {
    @Mock
    private lateinit var userService: UserService

    @Test
    fun onRequestLogin_shouldPopulateAModel() {
        val credentialsModel = CredentialsModel("raphael@email.com", "Santander@1")
        val successCallback: SuccessCallback<UserModel> = {
            Assert.assertNotNull(it)
        }
        val failureCallback: FailureCallback = {
            throw it
        }
        `when`(userService.getHttpClient())
            .thenReturn(FakeHttpClient())

        userService.login(credentialsModel, successCallback, failureCallback)
    }

    open class FakeHttpClient: HttpInterface {
        override fun post(url: String, bodyParameters: RequestParameters, onSuccess: SuccessCallback<JSONObject>, onFailure: FailureCallback) {
            onSuccess(JSONObject("""
                {
                    "userAccount": {
                        "userId": 1,
                        "name": "Jose da Silva Teste",
                        "bankAccount": "2050",
                        "agency": "012314564",
                        "balance": 3.3445
                    },
                    "error": {}
                }
            """))
        }

        override fun get(url: String, urlParameters: RequestParameters, onSuccess: SuccessCallback<JSONObject>, onFailure: FailureCallback) {
            onFailure(Exception("Nenhum método get para usuários"))
        }
    }
}