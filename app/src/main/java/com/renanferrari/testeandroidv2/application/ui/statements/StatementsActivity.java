package com.renanferrari.testeandroidv2.application.ui.statements;

import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import com.renanferrari.testeandroidv2.R;
import com.renanferrari.testeandroidv2.application.common.items.SubheaderItem;
import com.renanferrari.testeandroidv2.application.common.utils.Navigator;
import com.renanferrari.testeandroidv2.application.ui.login.LoginActivity;
import com.renanferrari.testeandroidv2.databinding.ActivityStatementsBinding;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Section;
import dagger.android.support.DaggerAppCompatActivity;
import javax.inject.Inject;

public class StatementsActivity extends DaggerAppCompatActivity {

  @Inject protected ViewModelProvider.Factory viewModelFactory;

  protected ActivityStatementsBinding binding;
  protected StatementsViewModel viewModel;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = DataBindingUtil.setContentView(this, R.layout.activity_statements);
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(StatementsViewModel.class);

    binding.toolbar.inflateMenu(R.menu.menu_statements);
    binding.toolbar.setOnMenuItemClickListener(item -> {
      switch (item.getItemId()) {
        case R.id.action_logout:
          viewModel.onLogoutRequested();
          return true;
        default:
          return false;
      }
    });

    final Section statementsSection = new Section();
    statementsSection.setHeader(SubheaderItem.create(R.string.label_recent));

    final GroupAdapter groupAdapter = new GroupAdapter();
    groupAdapter.add(statementsSection);

    binding.recyclerView.setAdapter(groupAdapter);

    viewModel.getObservableUserState().observe(this, userState -> {
      if (userState == null) {
        Navigator.of(this).clearTask().fade().go(LoginActivity.class);
        return;
      }

      binding.toolbar.setTitle(userState.getName());
      binding.accountTextView.setText(userState.getBankAccount());
      binding.balanceTextView.setText(userState.getBalance());
    });

    viewModel.getObservableStatementsState().observe(this, statementsState -> {
      if (statementsState.isLoading()) {
        // TODO
      }
      statementsSection.update(statementsState.getStatementItems());
    });

    viewModel.onUserRequested();
    viewModel.onStatementsRequested();
  }
}