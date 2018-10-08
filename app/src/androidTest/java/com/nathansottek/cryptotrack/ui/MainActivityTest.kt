package com.nathansottek.cryptotrack.ui

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.nathansottek.cryptotrack.BaseActivityTest
import com.nathansottek.cryptotrack.R
import com.nathansottek.cryptotrack.ui.base.GenericViewModelFactory
import com.nathansottek.cryptotrack.ui.main.MainActivity
import com.nathansottek.cryptotrack.ui.main.MainViewModel
import javax.inject.Inject
import kotlin.test.Test


class MainActivityTest: BaseActivityTest<MainActivity>() {

  override fun getActivityClass() = MainActivity::class.java

  @Inject lateinit var viewModelFactory: GenericViewModelFactory<MainViewModel>

  private lateinit var mockViewModel: MainViewModel

  override fun setUp() {
    super.setUp()
    component.inject(this)
    mockViewModel = viewModelFactory.create(MainViewModel::class.java)
  }

  @Test fun viewsAreDisplayed() {
    launchActivity()

    onView(withId(R.id.bcc)).check(matches(isDisplayed()))
    onView(withId(R.id.btc)).check(matches(isDisplayed()))
    onView(withId(R.id.eth)).check(matches(isDisplayed()))
    onView(withId(R.id.ltc)).check(matches(isDisplayed()))
    onView(withId(R.id.neo)).check(matches(isDisplayed()))
  }
}