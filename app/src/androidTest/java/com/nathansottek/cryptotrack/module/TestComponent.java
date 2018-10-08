package com.nathansottek.cryptotrack.module;

import com.nathansottek.cryptotrack.ui.MainActivityTest;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
    ContextModule.class,
    TestModule.class,
    TestViewModelModule.class
})

public interface TestComponent extends ApplicationComponent {

  void inject(MainActivityTest mainActivityTest);
}
