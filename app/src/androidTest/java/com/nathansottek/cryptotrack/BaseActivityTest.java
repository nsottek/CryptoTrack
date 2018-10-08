package com.nathansottek.cryptotrack;

import android.content.Intent;
import android.support.annotation.CallSuper;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.v7.app.AppCompatActivity;
import com.nathansottek.cryptotrack.module.ApplicationComponentHolder;
import com.nathansottek.cryptotrack.module.DaggerTestComponent;
import com.nathansottek.cryptotrack.module.TestComponent;
import com.nathansottek.cryptotrack.module.TestModule;
import org.junit.Before;
import org.junit.Rule;

public abstract class BaseActivityTest<T extends AppCompatActivity> {

  @Rule
  public IntentsTestRule<T> activityRule = new IntentsTestRule<>(getActivityClass(), true, false);

  private TestComponent testComponent;

  @Before
  @CallSuper
  public void setUp() {
    testComponent = DaggerTestComponent.builder()
        .testModule(new TestModule())
        .build();
    ApplicationComponentHolder.getInstance().setApplicationComponent(testComponent);
  }

  protected abstract Class<T> getActivityClass();

  protected TestComponent getComponent() {
    return testComponent;
  }

  public void launchActivity() {
    if (activityRule.getActivity() == null) {
      activityRule.launchActivity(new Intent());
    }
  }
}
