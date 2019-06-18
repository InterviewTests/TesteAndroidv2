package com.bilulo.androidtest04.ui.list.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bilulo.androidtest04.ui.list.configurator.ListConfigurator;
import com.bilulo.androidtest04.ui.list.contract.ListContract;

public class ListActivity extends AppCompatActivity implements ListContract.ActivityContract {
    public ListContract.InteractorContract interactor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListConfigurator.configure(this);
    }
}
