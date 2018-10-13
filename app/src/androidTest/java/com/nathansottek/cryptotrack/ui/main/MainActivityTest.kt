package com.nathansottek.cryptotrack.ui.main

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.swipeRight
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.nathansottek.cryptotrack.BaseActivityTest
import com.nathansottek.cryptotrack.R
import com.nathansottek.cryptotrack.factory.DataFactory
import com.nathansottek.cryptotrack.factory.PrimitiveFactory
import com.nathansottek.cryptotrack.ui.base.GenericViewModelFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.reset
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
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

    whenever(mockViewModel.symbols).thenReturn(Single.just(List(5) { PrimitiveFactory.randomString() }))

    whenever(mockViewModel.getData(any())).thenReturn(Single.just(DataFactory.currencyData()))
  }

  @Test fun viewsAreDisplayed() {
    launchActivity()

    waitForUiThread()

    onView(withId(R.id.mid_label)).check(matches(isDisplayed()))
    onView(withId(R.id.mid_value)).check(matches(isDisplayed()))
    onView(withId(R.id.updated_at)).check(matches(isDisplayed()))
    onView(withId(R.id.center_section_background)).check(matches(isDisplayed()))
    onView(withId(R.id.low_high_indicator)).check(matches(isDisplayed()))
    onView(withId(R.id.center_section_top_border)).check(matches(isDisplayed()))
    onView(withId(R.id.center_section_bottom_border)).check(matches(isDisplayed()))
    onView(withId(R.id.low_indicator)).check(matches(isDisplayed()))
    onView(withId(R.id.low_value)).check(matches(isDisplayed()))
    onView(withId(R.id.high_indicator)).check(matches(isDisplayed()))
    onView(withId(R.id.high_value)).check(matches(isDisplayed()))
    onView(withId(R.id.lower_background)).check(matches(isDisplayed()))
    onView(withId(R.id.volume_value)).check(matches(isDisplayed()))
    onView(withId(R.id.ask_value)).check(matches(isDisplayed()))
    onView(withId(R.id.bid_value)).check(matches(isDisplayed()))
    onView(withId(R.id.symbol_recyclerview)).check(matches(isDisplayed()))
    onView(withId(R.id.recyclerview_overlay)).check(matches(isDisplayed()))
  }

  @Test fun onSymbolsSwiped() {
    launchActivity()

    reset(mockViewModel) // Necessary due to initial scroll
    whenever(mockViewModel.getData(any())).thenReturn(Single.never())

    onView(withId(R.id.symbol_recyclerview)).perform(swipeRight())
    waitForUiThread()

    verify(mockViewModel).getData(any())
  }

  private fun waitForUiThread() {
    InstrumentationRegistry.getInstrumentation().waitForIdleSync()
  }
}