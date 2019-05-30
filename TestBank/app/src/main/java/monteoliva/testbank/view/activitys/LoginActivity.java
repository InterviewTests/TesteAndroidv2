package monteoliva.testbank.view.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.FrameLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import monteoliva.testbank.R;
import monteoliva.testbank.controller.OnRestLoginListener;
import monteoliva.testbank.controller.RestLogin;
import monteoliva.testbank.controller.UserAccount;
import monteoliva.testbank.utils.Constantes;
import monteoliva.testbank.utils.UtilsHelper;
import monteoliva.testbank.view.components.Progress;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity implements OnRestLoginListener {
    protected RestLogin restLogin;

    @ViewById(R.id.login_user)
    EditText edtUser;

    @ViewById(R.id.login_password)
    EditText edtPassword;

    @ViewById(R.id.login_progress)
    Progress progress;

    @ViewById(R.id.login_layout)
    FrameLayout layoutLogin;

    @AfterViews
    public void afterViews() {
        String login = UtilsHelper.getConfig(this, "user_login");

        edtUser.setText(login);

        progress.hide();

        restLogin = new RestLogin();
        restLogin.setOnRestLoginListener(this);
    }

    @Click(R.id.login_button)
    protected void actionLogin() {
        String fieldUser     = edtUser.getEditableText().toString();
        String fieldPassword = edtPassword.getEditableText().toString();
        boolean isLogin      = true;

        if (fieldUser.trim().isEmpty()) {
            isLogin = false;
            UtilsHelper.showSnackBar(layoutLogin, getString(R.string.login_error1));
        }
        else if (fieldPassword.trim().isEmpty()) {
            isLogin = false;
            UtilsHelper.showSnackBar(layoutLogin, getString(R.string.login_error2));
        }
        else {
            if (!UtilsHelper.verifyPassword(fieldPassword)) {
                isLogin = false;
                UtilsHelper.showSnackBar(layoutLogin, getString(R.string.login_error3));
            }
        }

        if (isLogin) {
            progress.show();
            UtilsHelper.saveConfig(this, "user_login", fieldUser);
            loginAsync(fieldUser, fieldPassword);
        }
    }

    @Background
    protected void loginAsync(String user, String password) {
        restLogin.send(user, password);
    }

    @UiThread
    protected void result(@NonNull UserAccount userAccount) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constantes.ACCOUNT_KEY, userAccount);

        Intent intent = new Intent(getBaseContext(), StatementActivity_.class);
        intent.putExtras(bundle);

        startActivity(intent);
        finish();
        overridePendingTransition( R.anim.righttoleft, R.anim.stable );
    }

    @Override
    public void onSuccess(@NonNull UserAccount userAccount) {
        result(userAccount);
    }

    @Override
    public void onError() { }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { finish(); return false; }
        else                                  { return super.onKeyDown(keyCode, event); }
    }
}