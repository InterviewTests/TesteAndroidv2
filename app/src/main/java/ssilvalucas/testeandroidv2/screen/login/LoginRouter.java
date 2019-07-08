package ssilvalucas.testeandroidv2.screen.login;

import android.content.Intent;
import java.lang.ref.WeakReference;
import ssilvalucas.testeandroidv2.data.model.UserAccount;
import ssilvalucas.testeandroidv2.screen.home.HomeActivity;

interface LoginRouterInput {
    void startHomeActivity(UserAccount userAccount);
}

public class LoginRouter implements LoginRouterInput {

    public static final String TAG = LoginRouter.class.getSimpleName();

    public WeakReference<LoginActivity> activity;

    @Override
    public void startHomeActivity(UserAccount userAccount) {
        Intent intent = new Intent(activity.get(), HomeActivity.class);
        intent.putExtra("userAccount", userAccount);
        activity.get().startActivity(intent);
        activity.get().finish();
    }
}
