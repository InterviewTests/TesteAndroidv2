package com.renanferrari.testeandroidv2.application.common.items;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import com.renanferrari.testeandroidv2.R;
import com.renanferrari.testeandroidv2.databinding.ItemSubheaderBinding;
import com.xwray.groupie.databinding.BindableItem;
import java.util.Objects;

public class SubheaderItem extends BindableItem<ItemSubheaderBinding> {

  @StringRes private final int titleRes;

  private SubheaderItem(@StringRes int titleRes) {
    this.titleRes = titleRes;
  }

  public static SubheaderItem create(@StringRes final int titleRes) {
    return new SubheaderItem(titleRes);
  }

  @Override public int getLayout() {
    return R.layout.item_subheader;
  }

  @Override public void bind(@NonNull ItemSubheaderBinding binding, int position) {
    binding.titleView.setText(titleRes);
  }

  @Override public boolean equals(final Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final SubheaderItem that = (SubheaderItem) o;
    return titleRes == that.titleRes;
  }

  @Override public int hashCode() {
    return Objects.hash(titleRes);
  }
}
