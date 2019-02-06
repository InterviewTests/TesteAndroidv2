package com.luizroseiro.testeandroidv2.mainScreen;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luizroseiro.testeandroidv2.MainActivity;
import com.luizroseiro.testeandroidv2.R;
import com.luizroseiro.testeandroidv2.databinding.FragmentMainBinding;
import com.luizroseiro.testeandroidv2.loginScreen.LoginFragment;
import com.luizroseiro.testeandroidv2.models.StatementModel;
import com.luizroseiro.testeandroidv2.models.UserModel;
import com.luizroseiro.testeandroidv2.utils.AppPreferences;
import com.luizroseiro.testeandroidv2.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.luizroseiro.testeandroidv2.utils.Statics.BUNDLE_USER_MODEL;

interface HomeFragmentInput {
    void displayHomeMetaData(List<StatementModel> statements);
}

public class MainFragment extends Fragment implements HomeFragmentInput {

    private FragmentMainBinding binding;

    private List<StatementModel> statements;
    private StatementsAdapter statementsAdapter;

    private UserModel mUserModel;

    public MainFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
            mUserModel = (UserModel) getArguments().getSerializable(BUNDLE_USER_MODEL);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container,
                false);

        statements = new ArrayList<>();

        binding.rvStatements.setHasFixedSize(true);
        binding.rvStatements.setLayoutManager(new LinearLayoutManager(MainActivity.getContext(),
                LinearLayoutManager.VERTICAL, false));

        statementsAdapter = new StatementsAdapter(statements);
        binding.rvStatements.setAdapter(statementsAdapter);

        setListeners();

        displayHomeUserMetaData(mUserModel);
        fetchHomeMetaData();

        return binding.getRoot();

    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppPreferences.clearUser();
                Utils.replaceFragment(new LoginFragment(), null);
            }
        });
    }

    private void displayHomeUserMetaData(UserModel userModel) {
        binding.tvUserName.setText(userModel.getName());
        binding.tvAccount.setText(MainActivity.getContext().getString(R.string.account_value,
                userModel.getBankAccount(), userModel.getFormattedAgency()));
        binding.tvBalance.setText(MainActivity.getContext().getString(R.string.balance_value,
                userModel.getBalance()));
    }

    private void fetchHomeMetaData() {
        // TODO: fetch user statements
    }

    @Override
    public void displayHomeMetaData(List<StatementModel> statementsList) {
        if (statementsList.size() > 0) {
            statements.clear();
            statements.addAll(statementsList);
            statementsAdapter.notifyDataSetChanged();
        }
    }

}