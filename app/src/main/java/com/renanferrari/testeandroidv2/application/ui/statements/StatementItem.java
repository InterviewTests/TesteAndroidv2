package com.renanferrari.testeandroidv2.application.ui.statements;

import androidx.annotation.NonNull;
import com.renanferrari.testeandroidv2.R;
import com.renanferrari.testeandroidv2.application.common.utils.DateFormatter;
import com.renanferrari.testeandroidv2.application.common.utils.MoneyFormatter;
import com.renanferrari.testeandroidv2.databinding.ItemStatementBinding;
import com.renanferrari.testeandroidv2.domain.model.statements.Statement;
import com.xwray.groupie.databinding.BindableItem;

public class StatementItem extends BindableItem<ItemStatementBinding> {

  private final Statement statement;
  private final MoneyFormatter moneyFormatter;
  private final DateFormatter dateFormatter;

  private StatementItem(final Statement statement,
      final MoneyFormatter moneyFormatter, final DateFormatter dateFormatter) {
    this.statement = statement;
    this.moneyFormatter = moneyFormatter;
    this.dateFormatter = dateFormatter;
  }

  public static StatementItem create(final Statement statement, final MoneyFormatter moneyFormatter,
      final DateFormatter dateFormatter) {
    return new StatementItem(statement, moneyFormatter, dateFormatter);
  }

  @Override public int getLayout() {
    return R.layout.item_statement;
  }

  @Override public void bind(@NonNull ItemStatementBinding binding, int position) {
    binding.titleView.setText(statement.getTitle());
    binding.descriptionView.setText(statement.getDescription());
    binding.dateView.setText(dateFormatter.format(statement.getDate()));
    binding.amountView.setText(moneyFormatter.format(statement.getValue()));
  }
}