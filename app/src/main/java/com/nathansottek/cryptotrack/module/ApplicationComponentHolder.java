package com.nathansottek.cryptotrack.module;

public class ApplicationComponentHolder {

  private static final ApplicationComponentHolder INSTANCE = new ApplicationComponentHolder();

  private ApplicationComponent applicationComponent;

  private ApplicationComponentHolder() {
    // Empty constructor
  }

  public static ApplicationComponentHolder getInstance() {
    return INSTANCE;
  }

  public ApplicationComponent getComponent() {
    return applicationComponent;
  }

  public void setApplicationComponent(ApplicationComponent applicationComponent) {
    this.applicationComponent = applicationComponent;
  }
}
