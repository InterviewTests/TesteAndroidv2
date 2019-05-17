package com.testeandroidv2.view.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.testeandroidv2.R;
import com.testeandroidv2.view.ui.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    private LoginFragment loginFragment;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_activity);

        loginFragment = new LoginFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, loginFragment, LoginFragment.TAG)
        .commitNowAllowingStateLoss();
    }

    public void changeFragment(Fragment fragment, String tag){

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(tag);
        ft.replace(R.id.fragment_container, fragment, tag);
        ft.commit();
    }
}
