package com.nathansottek.cryptotrack.ui.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Lazy;

import javax.inject.Inject;

/**
 * Allows lazy injection for {@link ViewModel} construction
 * @param <VM>
 */
public class GenericViewModelFactory<VM extends ViewModel> implements ViewModelProvider.Factory {

  private final Lazy<VM> lazyViewModel;

  @Inject
  public GenericViewModelFactory(Lazy<VM> viewModel) {
    this.lazyViewModel = viewModel;
  }

  @NonNull
  @Override
  @SuppressWarnings("unchecked")
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    if (modelClass.isAssignableFrom(lazyViewModel.get().getClass())) {
      return (T) lazyViewModel.get();
    } else {
      throw new IllegalArgumentException("ViewModel Not Found");
    }
  }
}
