package com.renanferrari.testeandroidv2.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.renanferrari.testeandroidv2.common.MoreObjects.firstNonNull;
import static java.util.Objects.requireNonNull;

public class Optional<T> {

  private final T value;

  private Optional() {
    this.value = null;
  }

  private Optional(final T value) {
    this.value = requireNonNull(value);
  }

  public static <T> Optional<T> absent() {
    return new Optional<>();
  }

  public static <T> Optional<T> of(@NonNull final T value) {
    return new Optional<>(value);
  }

  public static <T> Optional<T> fromNullable(@Nullable final T value) {
    return (value == null) ? Optional.absent() : new Optional<>(value);
  }

  public boolean isPresent() {
    return value != null;
  }

  public T get() {
    return requireNonNull(value);
  }

  @NonNull public T or(@NonNull T defaultValue) {
    return firstNonNull(value, defaultValue);
  }

  @Nullable public T orNull() {
    return value;
  }

  public <V> Optional<V> transform(final Function<? super T, V> function) {
    return new Optional<>(function.apply(value));
  }

  public interface Function<F, T> {
    @Nullable T apply(@Nullable F input);
  }
}