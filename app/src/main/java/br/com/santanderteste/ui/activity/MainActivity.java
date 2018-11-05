package br.com.santanderteste.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import androidx.navigation.Navigation;
import br.com.santanderteste.R;
import br.com.santanderteste.ui.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * @author JhonnyBarbosa
 * @version 1.0
 */
public class MainActivity extends BaseActivity {

    @Nullable
    @BindView(R.id.login_button)
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @Optional
    @OnClick(R.id.login_button)
    public void onClickLogin(View view) {
        Navigation.findNavController(this, R.id.main_nav_host).navigate(R.id.StatementFragment);
    }


    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.main_nav_host).navigateUp();
    }
}
