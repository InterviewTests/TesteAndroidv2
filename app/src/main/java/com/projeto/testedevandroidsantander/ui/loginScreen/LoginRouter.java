package com.projeto.testedevandroidsantander.ui.loginScreen;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.projeto.santander.ui.mainScreen.MainActivity;
import com.projeto.santander.util.Constants;

import java.lang.ref.WeakReference;

interface LoginRouterInput {
    void irParaMainActivity(LoginViewModel viewModel);

}

public class LoginRouter implements LoginRouterInput, AdapterView.OnItemClickListener {

    public static String TAG = LoginRouter.class.getSimpleName();
    public WeakReference<LoginActivity> activity;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void irParaMainActivity(LoginViewModel viewModel) {
        Intent intent = new Intent(activity.get(), MainActivity.class);
        intent.putExtra(Constants.OBJETO_USUARIO, viewModel.usuarioViewModel);

        activity.get().startActivity(intent);
        activity.get().finish();
    }
}
