package com.luizroseiro.testeandroidv2.mainScreen;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luizroseiro.testeandroidv2.R;
import com.luizroseiro.testeandroidv2.databinding.FragmentMainBinding;
import com.luizroseiro.testeandroidv2.models.UserModel;

interface HomeFragmentInput {
    void displayHomeMetaData(UserModel userModel);
}

public class MainFragment extends Fragment implements HomeFragmentInput {

    private FragmentMainBinding binding;

    public MainFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container,
                false);



        return binding.getRoot();

    }

    @Override
    public void displayHomeMetaData(UserModel userModel) {
        // TODO: prepare layout with UserModel data
    }

}
