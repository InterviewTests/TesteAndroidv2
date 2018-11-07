package com.renanferrari.testeandroidv2.application.ui.statements;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import com.renanferrari.testeandroidv2.R;
import com.renanferrari.testeandroidv2.application.common.items.SubheaderItem;
import com.renanferrari.testeandroidv2.application.common.utils.Navigator;
import com.renanferrari.testeandroidv2.application.ui.login.LoginActivity;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Section;

public class StatementsActivity extends AppCompatActivity {

  private Toolbar toolbar;
  private RecyclerView recyclerView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_statements);

    toolbar = findViewById(R.id.toolbar);
    toolbar.inflateMenu(R.menu.menu_statements);
    toolbar.setOnMenuItemClickListener(item -> {
      switch (item.getItemId()) {
        case R.id.action_logout:
          Navigator.of(this).clearTask().fade().go(LoginActivity.class);
          return true;
        default:
          return false;
      }
    });

    final Section statementsSection = new Section();
    statementsSection.setHeader(SubheaderItem.create(R.string.label_recent));
    statementsSection.add(StatementItem.create());
    statementsSection.add(StatementItem.create());
    statementsSection.add(StatementItem.create());
    statementsSection.add(StatementItem.create());
    statementsSection.add(StatementItem.create());
    statementsSection.add(StatementItem.create());
    statementsSection.add(StatementItem.create());
    statementsSection.add(StatementItem.create());
    statementsSection.add(StatementItem.create());

    final GroupAdapter groupAdapter = new GroupAdapter();
    groupAdapter.add(statementsSection);

    recyclerView = findViewById(R.id.recycler_view);
    recyclerView.setAdapter(groupAdapter);
  }
}
