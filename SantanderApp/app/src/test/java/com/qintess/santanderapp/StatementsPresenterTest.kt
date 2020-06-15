package com.qintess.santanderapp

import android.os.Build
import com.qintess.santanderapp.helper.Validator
import com.qintess.santanderapp.model.StatementModel
import com.qintess.santanderapp.ui.view.statementsScreen.StatementsActivityInput
import com.qintess.santanderapp.ui.view.statementsScreen.StatementsPresenter
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.lang.ref.WeakReference

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.KITKAT])
class StatementsPresenterTest {
    // presentStatements deve chamar updateList
    @Test
    fun onPresentStatements_shouldCallUpdateList() {
        val presenter = StatementsPresenter()
        val statementsPresenterOutputSpy = StatementsPresenterOutputSpy()
        presenter.output = WeakReference(statementsPresenterOutputSpy)

        val statements = ArrayList<StatementModel>()
        presenter.presentStatements(statements)

        Assert.assertTrue(statementsPresenterOutputSpy.updateListIsCalled)
    }

    // presentErrorMsg deve chamar showAlert
    @Test
    fun onPresentErrorMsg_shouldCallShowAlert() {
        val presenter = StatementsPresenter()
        val statementsPresenterOutputSpy = StatementsPresenterOutputSpy()
        presenter.output = WeakReference(statementsPresenterOutputSpy)

        presenter.presentErrorMessage(Validator.STATEMENTS_TITLE_ERROR, "")

        Assert.assertTrue(statementsPresenterOutputSpy.showAlertIsCalled)
    }

    class StatementsPresenterOutputSpy: StatementsActivityInput {
        var updateListIsCalled = false
        var showAlertIsCalled = false


        override fun displayStatements(userId: Int) { return }

        override fun updateList(statements: ArrayList<StatementModel>) {
            updateListIsCalled = true
        }

        override fun showAlert(title: String, msg: String): Boolean {
            showAlertIsCalled = true
            return true
        }

        override fun createListeners() { return }

        override fun logout() { return }
    }
}