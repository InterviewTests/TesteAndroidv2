package com.renanferrari.testeandroidv2.application.common.utils;

import android.text.Editable;
import android.text.TextWatcher;
import androidx.annotation.NonNull;

import static com.renanferrari.testeandroidv2.common.MoreObjects.firstNonNull;

public class TextWatcherFactory implements TextWatcher {

  private final Listener listener;

  private TextWatcherFactory(final Listener listener) {
    this.listener = listener;
  }

  public static TextWatcherFactory createListener(final Listener listener) {
    return new TextWatcherFactory(listener);
  }

  @Override
  public void beforeTextChanged(final CharSequence s, final int start, final int count,
      final int after) {}

  @Override
  public void onTextChanged(final CharSequence s, final int start, final int before,
      final int count) {
    listener.onTextChanged(firstNonNull(s, "").toString());
  }

  @Override public void afterTextChanged(final Editable s) {}

  public interface Listener {
    void onTextChanged(@NonNull final String text);
  }
}