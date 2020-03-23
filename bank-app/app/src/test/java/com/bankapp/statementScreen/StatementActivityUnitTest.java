package com.bankapp.statementScreen;


import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.appcompat.widget.AppCompatImageButton;

import com.bankapp.R;
import com.bankapp.helper.ConstantsHelper;
import com.bankapp.loginScreen.UserAccount;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.KITKAT)
public class StatementActivityUnitTest {

    private StatementActivity activity;
    UserAccount userAccount;
    StatementActivityOutputSpy statementActivityOutputSpy;
    StatementRouterOutputSpy statementRouterOutputSpy;

    @Before
    public void setUp(){
        createWithIntent();
        statementActivityOutputSpy = new StatementActivityOutputSpy();
        activity.output = statementActivityOutputSpy;
        statementRouterOutputSpy = new StatementRouterOutputSpy();
        activity.router = statementRouterOutputSpy;

    }
    private void createWithIntent() {
        userAccount = new UserAccount();
        userAccount.userId = 1;
        userAccount.bankAccount = "0002";
        userAccount.agency = "12345678";
        userAccount.balance = "200";
        userAccount.name = "Teste";

        Intent intent = new Intent(RuntimeEnvironment.application, StatementActivity.class);
        intent.putExtra(ConstantsHelper.USER_DETAILS, userAccount);
        activity = Robolectric.buildActivity(StatementActivity.class, intent).setup().get();
    }

    @Test
    public void StatementActivity_ShouldNOT_be_Null(){
        assertNotNull(activity);
    }

    @Test
    public void bindFields_with_invalid_data_shouldCall_error() {
        activity.userAccount = null;
        activity.bindFields();
        assertTrue(ShadowToast.showedToast("Ops! Não conseguimos carregar algumas informações."));
    }

    @Test
    public void btnLoginClick_shouldCall_logout_and_passDataToNextScene() {
        AppCompatImageButton btLogout = activity.findViewById(R.id.btLogout);
        btLogout.performClick();

        assertTrue(statementActivityOutputSpy.logoutIsCalled);
        assertTrue(statementRouterOutputSpy.passDataToNextSceneIsCalled);
    }

    @Test
    public void onCreate_shouldCall_getStatements() {
        activity.getStatements();
        assertTrue(statementActivityOutputSpy.getStatementsIsCalled);
    }

    private class StatementActivityOutputSpy implements StatementInteractorInput {

        boolean logoutIsCalled = false;
        boolean getStatementsIsCalled = false;

        @Override
        public void getStatements(StatementRequest statementRequest) {
            getStatementsIsCalled = true;
        }

        @Override
        public void logout(Context context) {
            logoutIsCalled = true;
        }
    }

    private class StatementRouterOutputSpy implements StatementRouterInput {

        boolean passDataToNextSceneIsCalled = false;
        boolean determineNextScreenIsCalled = false;

        @Override
        public void passDataToNextScene() {
            passDataToNextSceneIsCalled = true;
        }

        @Override
        public Intent determineNextScreen() {
            return null;
        }
    }
}
