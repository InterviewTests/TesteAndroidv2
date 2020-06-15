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

    class StatementsActivityOutputSpy: StatementsInteractorInput {
        var fetchStatementsIsCalled = false

        override fun fetchStatements(user_id: Int) {
            fetchStatementsIsCalled = true
        }

    }
}