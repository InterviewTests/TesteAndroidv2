package com.renanferrari.testeandroidv2.application.common.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.ActivityOptionsCompat;

public class Navigator {

  private final Context context;
  private boolean clearTask;
  private boolean fade;

  private Navigator(final Context context) {
    this.context = context;
    this.clearTask = false;
    this.fade = false;
  }

  public static Navigator of(final Context context) {
    return new Navigator(context);
  }

  public Navigator clearTask() {
    return clearTask(true);
  }

  public Navigator clearTask(final boolean clearTask) {
    this.clearTask = clearTask;
    return this;
  }

  public Navigator fade() {
    return fade(true);
  }

  public Navigator fade(final boolean fade) {
    this.fade = fade;
    return this;
  }

  public void go(final Class<?> activityClass) {
    final Intent intent = new Intent(context, activityClass);
    if (clearTask) {
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    }
    final Bundle options;
    if (fade) {
      options = ActivityOptionsCompat.makeCustomAnimation(context, android.R.anim.fade_in,
          android.R.anim.fade_out).toBundle();
    } else {
      options = null;
    }
    context.startActivity(intent, options);
  }
}
