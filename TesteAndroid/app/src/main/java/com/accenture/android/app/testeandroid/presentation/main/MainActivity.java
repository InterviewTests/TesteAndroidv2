package com.accenture.android.app.testeandroid.presentation.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.accenture.android.app.testeandroid.databinding.ActivityMainBinding;

/**
 * Created by Denis Magno on 09/07/2020.
 * denis_magno16@hotmail.com
 */
public class MainActivity extends AppCompatActivity implements MainContract.View {
    private final static String TAG = "CustomLog - " + MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;

    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());

        this.presenter = new MainPresenter(this, this.getLifecycle(), this);

        setContentView(this.binding.getRoot());
    }
}