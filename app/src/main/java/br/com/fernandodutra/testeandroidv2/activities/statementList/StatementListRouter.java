package br.com.fernandodutra.testeandroidv2.activities.statementList;

import android.content.Intent;

import java.lang.ref.WeakReference;

import br.com.fernandodutra.testeandroidv2.activities.login.LoginActivity;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 27/06/2019
 * Time: 20:02
 * TesteAndroidv2_CleanCode
 */

interface statemetListRouter {
    Intent createIntent();
    void loginRouterInput();
}

public class StatementListRouter implements statemetListRouter {

    public WeakReference<StatementListActivity> activity;
    //
    public static String TAG = StatementListRouter.class.getSimpleName();

    @Override
    public Intent createIntent() {
        return new Intent(activity.get().getBaseContext(), LoginActivity.class);
    }

    @Override
    public void loginRouterInput() {
        Intent intent = createIntent();
        activity.get().startActivity(intent);
        activity.get().finish();
    }

}
