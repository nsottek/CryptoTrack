package com.nathansottek.cryptotrack.module;

import com.nathansottek.cryptotrack.ui.main.MainViewModel;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Single;

import javax.inject.Singleton;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Module
public class TestViewModelModule {

  @Provides
  @Singleton
  MainViewModel providesMainViewModel() {
    MainViewModel mockViewModel = mock(MainViewModel.class);
    when(mockViewModel.getBcc()).thenReturn(Single.never());
    when(mockViewModel.getBtc()).thenReturn(Single.never());
    when(mockViewModel.getEth()).thenReturn(Single.never());
    when(mockViewModel.getLtc()).thenReturn(Single.never());
    when(mockViewModel.getNeo()).thenReturn(Single.never());
    return mockViewModel;
  }
}
