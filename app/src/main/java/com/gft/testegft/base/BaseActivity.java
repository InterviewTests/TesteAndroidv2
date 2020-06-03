package com.gft.testegft.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(layoutRes());
        ViewModelProviders.of(this).get(viewModel().getClass());

        attachViewModel();
        observe();
    }

    @LayoutRes
    protected abstract int layoutRes();

    protected abstract BaseViewModel viewModel();

    protected abstract void observe();

    protected abstract void attachViewModel();
}
