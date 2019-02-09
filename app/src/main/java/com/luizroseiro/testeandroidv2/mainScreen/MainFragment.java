package com.luizroseiro.testeandroidv2.mainScreen;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

interface MainFragmentInput {
    void displayStatementsMetaData(List<StatementModel> statements);
}

public class MainFragment extends Fragment implements MainFragmentInput {

    private static MainFragment mainFragment;
    private FragmentMainBinding binding;
    protected StatementsInteractorInput output;

    private List<StatementModel> statements;
    private StatementsAdapter statementsAdapter;

    private UserModel mUserModel;

    public MainFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainFragment = MainFragment.this;

        if (getArguments() != null)
            mUserModel = (UserModel) getArguments().getSerializable(BUNDLE_USER_MODEL);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container,
                false);

        output = new StatementsInteractor();
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
        binding.swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchHomeMetaData();
            }
        });

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppPreferences.clearUser();
                Utils.replaceFragment(new LoginFragment(), null);
            }
        });

        binding.rvStatements.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (manager != null && manager
                        .findFirstCompletelyVisibleItemPosition() != 0) {
                    binding.tvRecentsLabel.setVisibility(View.GONE);
                }
                else {
                    binding.tvRecentsLabel.setVisibility(View.VISIBLE);
                }
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
        StatementsRequest statementsRequest = new StatementsRequest();
        statementsRequest.setId(mUserModel.getUserId());

        binding.swipeRefresh.setRefreshing(false);
        binding.pbStatements.setVisibility(View.VISIBLE);
        binding.rvStatements.setVisibility(View.GONE);
        output.fetchStatements(statementsRequest, binding.pbStatements);
    }

    public static MainFragment getMainFragment() {
        return mainFragment;
    }

    @Override
    public void displayStatementsMetaData(List<StatementModel> statementsList) {
        if (statementsList.size() > 0) {
            binding.rvStatements.setVisibility(View.VISIBLE);
            statements.clear();
            statements.addAll(statementsList);
            statementsAdapter.notifyDataSetChanged();
        }
    }

}