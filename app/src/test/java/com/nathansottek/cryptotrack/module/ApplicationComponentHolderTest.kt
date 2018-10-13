package com.nathansottek.cryptotrack.module

import com.nhaarman.mockitokotlin2.mock
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationComponentHolderTest {

  @Test fun getInstance() {
    assertEquals(ApplicationComponentHolder.getInstance(), ApplicationComponentHolder.getInstance())
  }

  @Test fun getAndSetComponent() {
    val holder = ApplicationComponentHolder.getInstance()
    val expected = mock<ApplicationComponent>()

    holder.setApplicationComponent(expected)

    assertEquals(expected, holder.component)
  }
}