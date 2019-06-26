package com.earaujo.santander.statements

import com.earaujo.santander.login.LoginActivity
import com.nhaarman.mockito_kotlin.argThat
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.lang.ref.WeakReference

@RunWith(RobolectricTestRunner::class)
@Config(manifest= Config.NONE)
class StatementsRouterTest {

    @Mock
    lateinit var statementsActivity: StatementsActivity

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `when call logout should launch LoginActivity and finish StatementsActivity`() {
        //Prepare
        val loginRouter = StatementsRouter()

        loginRouter.activity = WeakReference(statementsActivity)

        //Action
        loginRouter.logout()

        //Verification
        verify(statementsActivity, times(1)).startActivity(argThat {
            this.component.className == LoginActivity::class.java.name
        })
        verify(statementsActivity, times(1)).finish()
    }

}