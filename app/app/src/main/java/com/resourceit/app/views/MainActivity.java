package com.resourceit.app.views;

import android.os.Bundle;
import android.view.View;

import com.resourceit.app.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    @BindView(R.id.loading)
    ConstraintLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Fragment fragment = new LoginFragment();
        updateFragment(fragment);
        updateFragment(fragment);
    }

    public void updateFragment(Fragment destFragment) {
        fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragments, destFragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    public void Loading(Boolean visibility){
        loading.setVisibility(visibility?View.VISIBLE:View.GONE);
    }
}
