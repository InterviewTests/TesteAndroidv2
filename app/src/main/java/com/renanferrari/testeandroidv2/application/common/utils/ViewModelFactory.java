package com.renanferrari.testeandroidv2.application.common.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton public class ViewModelFactory implements ViewModelProvider.Factory {
  private final Map<Class<? extends ViewModel>, Provider<ViewModel>> providers;

  @Inject
  public ViewModelFactory(final Map<Class<? extends ViewModel>, Provider<ViewModel>> providers) {
    this.providers = providers;
  }

  @Override
  @NonNull
  @SuppressWarnings("unchecked")
  public <T extends ViewModel> T create(@NonNull final Class<T> modelClass) {
    Provider<? extends ViewModel> provider = providers.get(modelClass);
    if (provider == null) {
      for (Map.Entry<Class<? extends ViewModel>, Provider<ViewModel>> entry : providers.entrySet()) {
        if (modelClass.isAssignableFrom(entry.getKey())) {
          provider = entry.getValue();
          break;
        }
      }
    }
    if (provider == null) {
      throw new IllegalArgumentException("unknown model class " + modelClass);
    }
    try {
      return (T) provider.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}