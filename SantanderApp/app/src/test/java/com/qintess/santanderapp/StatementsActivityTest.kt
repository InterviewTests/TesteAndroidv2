package com.qintess.santanderapp

import android.os.Build
import androidx.test.core.app.ActivityScenario.launch
import com.qintess.santanderapp.model.UserModel
import com.qintess.santanderapp.ui.view.statementsScreen.StatementsActivity
import com.qintess.santanderapp.ui.view.statementsScreen.StatementsInteractorInput
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.KITKAT])
class StatementsActivityTest {
    private val userModel = UserModel(1, "Raphael", "000", "000", 100.0)

    // displayUser deve chamar loadUser
    @Test
    fun onDisplayUser_shouldCallLoadUser() {
        launch(StatementsActivity::class.java).onActivity { activity ->
            val statementsActivityOutputSpy = StatementsActivityOutputSpy()
            activity.output = statementsActivityOutputSpy

            activity.displayUser(userModel)

            Assert.assertTrue(statementsActivityOutputSpy.loadUserIsCalled)
        }
    }

    // displayUser passa o usuário correto para o loadUser
    @Test
    fun displayUser_passCorrectParamenter() {
        launch(StatementsActivity::class.java).onActivity { activity ->
            val statementsActivityOutputSpy = StatementsActivityOutputSpy()
            activity.output = statementsActivityOutputSpy

            activity.displayUser(userModel)

            Assert.assertEquals(userModel, statementsActivityOutputSpy.userCopy)
        }
    }

    // displayStatements deve chamar fetchStatements
    @Test
    fun displayStatements_shouldCallFetchStatements() {
        launch(StatementsActivity::class.java).onActivity { activity ->
            val statementsActivityOutputSpy = StatementsActivityOutputSpy()
            activity.output = statementsActivityOutputSpy

            activity.displayStatements(userModel.userId)

            Assert.assertTrue(statementsActivityOutputSpy.fetchStatementsIsCalled)
        }
    }

    // Clique botão logout deve chamar logout
    @Test
    fun onLogoutClick_shouldCouldLogout() {

    }

    class StatementsActivityOutputSpy: StatementsInteractorInput {
        var loadUserIsCalled = false
        var userCopy: UserModel? = null

        var fetchStatementsIsCalled = false

        override fun fetchStatements(user_id: Int) {
            fetchStatementsIsCalled = true
        }

    }
}