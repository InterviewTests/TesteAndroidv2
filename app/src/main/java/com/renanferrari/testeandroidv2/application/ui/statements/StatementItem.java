package com.renanferrari.testeandroidv2.application.ui.statements;

import androidx.annotation.NonNull;
import com.renanferrari.testeandroidv2.R;
import com.renanferrari.testeandroidv2.databinding.ItemStatementBinding;
import com.xwray.groupie.databinding.BindableItem;
import java.util.Objects;

public class StatementItem extends BindableItem<ItemStatementBinding> {

  private final String title;
  private final String description;
  private final String date;
  private final String amount;

  private StatementItem() {
    title = "Pagamento";
    description = "Conta de luz";
    date = "12/12/2018";
    amount = "R$1.000,00";
  }

  public static StatementItem create() {
    return new StatementItem();
  }

  @Override public int getLayout() {
    return R.layout.item_statement;
  }

  @Override public void bind(@NonNull ItemStatementBinding binding, int position) {
    binding.titleView.setText(title);
    binding.descriptionView.setText(description);
    binding.dateView.setText(date);
    binding.amountView.setText(amount);
  }

  //@Override public boolean equals(final Object o) {
  //  if (this == o) return true;
  //  if (o == null || getClass() != o.getClass()) return false;
  //  final StatementItem that = (StatementItem) o;
  //  return Objects.equals(title, that.title) &&
  //      Objects.equals(description, that.description) &&
  //      Objects.equals(date, that.date) &&
  //      Objects.equals(amount, that.amount);
  //}
  //
  //@Override public int hashCode() {
  //  return Objects.hash(title, description, date, amount);
  //}
}
