package com.earaujo.santander.login

import com.earaujo.santander.statements.StatementsActivity
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.lang.ref.WeakReference
import com.earaujo.santander.repository.models.UserAccountModel
import com.nhaarman.mockito_kotlin.argThat
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest= Config.NONE)
class LoginRouterTest {

    @Mock
    lateinit var loginActivity: LoginActivity

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `when call startStatementScreen should launch StatementsActivity with parameters and finish loginActivity`() {
        //Prepare
        val loginRouter = LoginRouter()
        val userAccount = UserAccountModel(1, "Eduardo", "1234", "1234", 1.0)

        loginRouter.activity = WeakReference(loginActivity)

        whenever(loginActivity.userAccount).thenReturn(userAccount)

        //Action
        loginRouter.startStatementScreen()

        //Verification
        verify(loginActivity, times(1)).startActivity(argThat {
            ((this.component.className == StatementsActivity::class.java.name) and
                    this.hasExtra("user_data") and
                    ((this.getSerializableExtra("user_data") as UserAccountModel) == userAccount))
        })
        verify(loginActivity, times(1)).finish()
    }

}