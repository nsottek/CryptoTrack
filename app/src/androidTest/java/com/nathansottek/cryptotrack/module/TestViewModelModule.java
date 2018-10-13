package com.nathansottek.cryptotrack.module;

import com.nathansottek.cryptotrack.ui.main.MainViewModel;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Single;

import javax.inject.Singleton;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Module
public class TestViewModelModule {

  @Provides
  @Singleton
  MainViewModel providesMainViewModel() {
    MainViewModel mockViewModel = mock(MainViewModel.class);
    when(mockViewModel.getSymbols()).thenReturn(Single.never());
    when(mockViewModel.getData(anyInt())).thenReturn(Single.never());
    return mockViewModel;
  }
}
