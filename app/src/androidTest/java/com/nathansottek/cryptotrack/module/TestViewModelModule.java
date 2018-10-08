package com.nathansottek.cryptotrack.module;

import com.nathansottek.cryptotrack.ui.main.MainViewModel;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

import static org.mockito.Mockito.mock;

@Module
public class TestViewModelModule {

  @Provides
  @Singleton
  MainViewModel providesMainViewModel() {
    return mock(MainViewModel.class);
  }
}
