package com.casasw.bankapp.ui.currency;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.casasw.bankapp.R;

public class CurrencyFragment extends Fragment {

    private com.casasw.bankapp.ui.currency.CurrencyViewModel mViewModel;

    public static CurrencyFragment newInstance() {
        return new CurrencyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.currency_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(com.casasw.bankapp.ui.currency.CurrencyViewModel.class);
        // TODO: Use the ViewModel
    }

}
