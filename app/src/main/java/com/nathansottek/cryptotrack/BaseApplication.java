package com.nathansottek.cryptotrack;

import android.app.Application;
import com.nathansottek.cryptotrack.module.ApplicationComponentHolder;
import com.nathansottek.cryptotrack.module.ContextModule;
import com.nathansottek.cryptotrack.module.DaggerApplicationComponent;
import com.nathansottek.cryptotrack.module.NetworkModule;

public class BaseApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    ApplicationComponentHolder.getInstance().setApplicationComponent(DaggerApplicationComponent.builder()
    .contextModule(new ContextModule(this))
    .networkModule(new NetworkModule())
    .build());
  }
}
