package br.com.santanderteste.ui.activity;

import android.os.Bundle;

import androidx.navigation.Navigation;
import br.com.santanderteste.R;
import br.com.santanderteste.ui.BaseActivity;
import butterknife.ButterKnife;

/**
 * @author JhonnyBarbosa
 * @version 1.0
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.main_nav_host).navigateUp();
    }
}
