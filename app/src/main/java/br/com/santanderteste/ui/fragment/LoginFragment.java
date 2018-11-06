package br.com.santanderteste.ui.fragment;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import br.com.santanderteste.R;
import br.com.santanderteste.presenter.LoginPresenter;
import br.com.santanderteste.repository.UserRepository;
import br.com.santanderteste.repository.db.UserDatabase;
import br.com.santanderteste.ui.BaseFragment;
import br.com.santanderteste.ui.activity.MainActivity;
import br.com.santanderteste.ui.interfaces.ILoginView;
import br.com.santanderteste.utils.Const;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author JhonnyBarbosa
 * @version 1.0
 */
public class LoginFragment extends BaseFragment implements ILoginView {

    private LoginPresenter loginPresenter;
    private UserRepository userRepository;

    private Unbinder unbinder;
    private UserDatabase userDatabase;

    @BindView(R.id.login_button)
    Button loginButton;

    @BindView(R.id.username_login)
    EditText userNameLogin;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.login_progressbar)
    ProgressBar loginProgressBar;

    @BindView(R.id.error_message)
    TextView errorMessage;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hideActionBar((MainActivity) getActivity());

        userDatabase = Room.databaseBuilder(getActivity(),
                UserDatabase.class, Const.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

        userRepository = new UserRepository(userDatabase);
        loginPresenter = new LoginPresenter(this, userRepository);

        userNameLogin.setText("51544156049");
        password.setText("Test@1");

        loginPresenter.loadUserData();

    }

    @Override
    public boolean isViewAdde() {
        return isAdded();
    }

    @Override
    public void callStatementsFragment(Bundle bundle) {
        NavOptions options = new NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build();
        Navigation.findNavController(getActivity(), R.id.main_nav_host).navigate(R.id.StatementFragment, bundle, options);
    }

    @Override
    public void showProgress() {
        loginProgressBar.setVisibility(View.VISIBLE);
        loginButton.setText(Const.EMPTY_STRING);
    }

    @Override
    public void hideProgress() {
        loginProgressBar.setVisibility(View.GONE);
        loginButton.setText(Const.LOGIN);
    }

    @Override
    public void disableEditText() {
        userNameLogin.setEnabled(false);
        password.setEnabled(false);
    }


    @Override
    public void enableEditText() {
        userNameLogin.setEnabled(true);
        password.setEnabled(true);
    }

    @Override
    public void showErrorMessage(String message) {
        errorMessage.setText(message);
        errorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorMessage() {
        errorMessage.setVisibility(View.GONE);
    }

    @OnClick(R.id.login_button)
    public void onClickLogin(View view) {

        String u = userNameLogin.getText().toString();
        String p = password.getText().toString();

        loginPresenter.checkLoginData(u, p);

    }

}
