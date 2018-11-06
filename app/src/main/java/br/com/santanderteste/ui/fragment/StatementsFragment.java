package br.com.santanderteste.ui.fragment;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import br.com.santanderteste.R;
import br.com.santanderteste.model.Statement;
import br.com.santanderteste.model.User;
import br.com.santanderteste.presenter.BankStatementsPresenter;
import br.com.santanderteste.repository.UserRepository;
import br.com.santanderteste.repository.db.UserDatabase;
import br.com.santanderteste.ui.BaseFragment;
import br.com.santanderteste.ui.activity.MainActivity;
import br.com.santanderteste.ui.adapter.StatementsAdapter;
import br.com.santanderteste.ui.interfaces.IStatementView;
import br.com.santanderteste.utils.Const;
import br.com.santanderteste.utils.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class StatementsFragment extends BaseFragment implements IStatementView {

    private Unbinder unbinder;
    private UserRepository userRepository;
    private UserDatabase userDatabase;

    @Nullable
    @BindView(R.id.statement_recyclerview)
    RecyclerView statementRecyclerview;

    @BindView(R.id.user_name)
    TextView userName;

    @BindView(R.id.account)
    TextView account;

    @BindView(R.id.balance)
    TextView balance;

    @BindView(R.id.statements_progress)
    ProgressBar statementsProgress;

    @BindView(R.id.log_out_button)
    ImageView logOut;

    private StatementsAdapter statementsAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private BankStatementsPresenter bankStatementsPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bank_statement_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hideActionBar((MainActivity) getActivity());

        userDatabase = Room.databaseBuilder(getActivity(),
                UserDatabase.class, Const.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

        userRepository = new UserRepository(userDatabase);

        bankStatementsPresenter = new BankStatementsPresenter(this, userRepository);

        statementsAdapter = new StatementsAdapter();
        bankStatementsPresenter.loadStatements();

        statementRecyclerview.setAdapter(statementsAdapter);

        layoutManager = new LinearLayoutManager(getActivity());
        statementRecyclerview.setLayoutManager(layoutManager);

        User user = (User) getArguments().get(Const.USER_DATA);

        userName.setText(user.getName());
        account.setText(Utils.formatStringWithMask(user.getBankAccount() + user.getAgency(), Const.PATTERN));
        balance.setText(Utils.numberFormat(user.getBalance(), true, getActivity()));

    }

    @OnClick(R.id.log_out_button)
    public void logOut() {
        bankStatementsPresenter.logUserOut();
        NavOptions options = new NavOptions.Builder().setPopUpTo(R.id.StatementFragment, true).build();
        Navigation.findNavController(getActivity(), R.id.main_nav_host).navigate(R.id.loginFragment, null, options);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void updateStatementList(List<Statement> statements) {
        this.statementsAdapter.setData(statements);
        this.statementsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showProgress() {
        statementsProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        statementsProgress.setVisibility(View.GONE);
    }

    @Override
    public boolean isViewAdded() {
        return isAdded();
    }

}
