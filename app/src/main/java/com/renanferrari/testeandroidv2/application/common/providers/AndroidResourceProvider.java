package com.renanferrari.testeandroidv2.application.common.providers;

import android.content.Context;

public class AndroidResourceProvider implements ResourceProvider {

  private final Context context;

  public AndroidResourceProvider(final Context context) {
    this.context = context;
  }

  @Override public String getString(final int stringId) {
    return context.getString(stringId);
  }
}