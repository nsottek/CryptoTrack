package com.nathansottek.cryptotrack.network.main

import com.nathansottek.cryptotrack.factory.PrimitiveFactory
import kotlin.test.Test
import kotlin.test.assertEquals

class CurrencyResponseTest {

  @Test fun constructor() {
    val mid = PrimitiveFactory.randomString()
    val bid = PrimitiveFactory.randomString()
    val ask = PrimitiveFactory.randomString()
    val lastPrice = PrimitiveFactory.randomString()
    val low = PrimitiveFactory.randomString()
    val high = PrimitiveFactory.randomString()
    val volume = PrimitiveFactory.randomString()
    val timeStamp = PrimitiveFactory.randomString()

    val result = CurrencyResponse(mid, bid, ask, lastPrice, low, high, volume, timeStamp)

    assertEquals(mid, result.mid)
    assertEquals(bid, result.bid)
    assertEquals(ask, result.ask)
    assertEquals(lastPrice, result.lastPrice)
    assertEquals(low, result.low)
    assertEquals(high, result.high)
    assertEquals(volume, result.volume)
    assertEquals(timeStamp, result.timestamp)
  }
}