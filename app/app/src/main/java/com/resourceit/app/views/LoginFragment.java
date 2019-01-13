package com.resourceit.app.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.resourceit.app.R;

import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.login)
    void Login() {
        ((MainActivity) getActivity()).Loading(true);
        ((MainActivity) getActivity()).updateFragment(new StatementsFragment());
        ((MainActivity) getActivity()).Loading(false);
    }
}
