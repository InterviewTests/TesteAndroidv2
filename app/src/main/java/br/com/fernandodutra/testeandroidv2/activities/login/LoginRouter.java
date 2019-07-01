package br.com.fernandodutra.testeandroidv2.activities.login;

import android.content.Intent;

import java.io.Serializable;
import java.lang.ref.WeakReference;

import br.com.fernandodutra.testeandroidv2.activities.statementList.StatementListActivity;
import br.com.fernandodutra.testeandroidv2.models.UserAccount;
import br.com.fernandodutra.testeandroidv2.utils.Constants;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 26/06/2019
 * Time: 21:15
 * TesteAndroidv2_CleanCode
 */

interface LoginRouterInput {
    Intent createIntent(UserAccount userAccount);
    void statemetListRouter(UserAccount userAccount);
}

public class LoginRouter implements LoginRouterInput {

    public WeakReference<LoginActivity> activity;
    //
    public static String TAG = LoginRouter.class.getSimpleName();

    @Override
    public Intent createIntent(UserAccount userAccount) {
        Intent intent = new Intent(activity.get().getBaseContext(), StatementListActivity.class);
        intent.putExtra(Constants.USERACCOUNT_USERID, (Serializable) userAccount);
        return intent;
    }

    @Override
    public void statemetListRouter(UserAccount userAccount) {
        Intent intent = createIntent(userAccount);
        activity.get().startActivity(intent);
        activity.get().finish();
    }
}
