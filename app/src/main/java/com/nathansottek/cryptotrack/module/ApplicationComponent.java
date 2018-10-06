package com.nathansottek.cryptotrack.module;

import com.nathansottek.cryptotrack.ui.main.MainActivity;
import dagger.Component;

import javax.inject.Singleton;

  @Singleton
  @Component(modules = {
      ContextModule.class,
      NetworkModule.class
  })

public interface ApplicationComponent {

    void inject(MainActivity mainActivity);
}
