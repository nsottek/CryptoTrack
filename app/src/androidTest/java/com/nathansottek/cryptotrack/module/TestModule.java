package com.nathansottek.cryptotrack.module;

import com.nathansottek.cryptotrack.network.NetworkApi;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

import static org.mockito.Mockito.mock;

@Module
public class TestModule {

  @Provides
  @Singleton
  NetworkApi providesNetworkApi() {
    return mock(NetworkApi.class);
  }
}
