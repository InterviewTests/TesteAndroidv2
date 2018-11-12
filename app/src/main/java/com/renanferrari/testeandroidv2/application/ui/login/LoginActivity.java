package com.renanferrari.testeandroidv2.application.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import com.renanferrari.testeandroidv2.R;
import com.renanferrari.testeandroidv2.application.common.utils.Navigator;
import com.renanferrari.testeandroidv2.application.ui.statements.StatementsActivity;
import com.renanferrari.testeandroidv2.databinding.ActivityLoginBinding;
import dagger.android.support.DaggerAppCompatActivity;
import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.renanferrari.testeandroidv2.application.common.utils.TextWatcherFactory.createListener;

public class LoginActivity extends DaggerAppCompatActivity {

  @Inject protected ViewModelProvider.Factory viewModelFactory;

  protected ActivityLoginBinding binding;
  protected LoginViewModel viewModel;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);

    binding.userEditText.addTextChangedListener(createListener(viewModel::onUsernameChanged));
    binding.passwordEditText.addTextChangedListener(createListener(viewModel::onPasswordChanged));
    binding.loginButton.setOnClickListener(v -> viewModel.onLoginRequested());

    viewModel.getObservableLoginState().observe(this, state -> {
      if (state.isLoggedIn()) {
        Navigator.of(this).clearTask().fade().go(StatementsActivity.class);
        return;
      }

      binding.userTextInput.setEnabled(!state.isLoading());
      binding.passwordTextInput.setEnabled(!state.isLoading());
      binding.loginButton.setEnabled(!state.isLoading());
      binding.loginButton.setText(state.isLoading() ? "" : getString(R.string.action_login));
      binding.progressBar.setVisibility(state.isLoading() ? VISIBLE : GONE);

      if (TextUtils.isEmpty(state.getUsernameError())) {
        binding.userTextInput.setErrorEnabled(false);
      } else {
        binding.userTextInput.setError(state.getUsernameError());
      }
      //binding.userTextInput.setError(state.getUsernameError());
      binding.passwordTextInput.setError(state.getPasswordError());
    });

    viewModel.onUserRequested();
  }

  @Override protected void onDestroy() {
    binding.unbind();
    super.onDestroy();
  }
}