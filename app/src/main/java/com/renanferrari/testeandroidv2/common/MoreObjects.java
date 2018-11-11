package com.renanferrari.testeandroidv2.common;

import androidx.annotation.Nullable;

public class MoreObjects {

  public static <T> T firstNonNull(@Nullable T first, @Nullable T second) {
    if (first != null) {
      return first;
    }
    if (second != null) {
      return second;
    }
    throw new NullPointerException("Both parameters are null");
  }
}