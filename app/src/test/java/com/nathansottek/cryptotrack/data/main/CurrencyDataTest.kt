package com.nathansottek.cryptotrack.data.main

import com.nathansottek.cryptotrack.data.NetworkResult
import com.nathansottek.cryptotrack.factory.DataFactory
import com.nathansottek.cryptotrack.factory.PrimitiveFactory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CurrencyDataTest {

  @Test fun constructor_validData() {
    val type = DataFactory.randomCurrencyDataType()
    val mid = PrimitiveFactory.randomString()
    val bid = PrimitiveFactory.randomString()
    val ask = PrimitiveFactory.randomString()
    val lastPrice = PrimitiveFactory.randomString()
    val low = PrimitiveFactory.randomString()
    val high = PrimitiveFactory.randomString()
    val volume = PrimitiveFactory.randomString()
    val timeStamp = PrimitiveFactory.randomString()

    val result = CurrencyData(type, mid, bid, ask, lastPrice, low, high, volume, timeStamp)

    assertEquals(type, result.currencyType)
    assertEquals(mid, result.mid)
    assertEquals(bid, result.bid)
    assertEquals(ask, result.ask)
    assertEquals(lastPrice, result.lastPrice)
    assertEquals(low, result.low)
    assertEquals(high, result.high)
    assertEquals(volume, result.volume)
    assertEquals(timeStamp, result.timestamp)
  }

  @Test fun constructor_tooManyRequestsError() {
    val networkResult = NetworkResult.TOO_MANY_REQUESTS

    val result = CurrencyData(networkResult)

    assertError(networkResult, result)
  }

  @Test fun constructor_genericError() {
    val networkResult = NetworkResult.ERROR

    val result = CurrencyData(networkResult)

    assertError(networkResult, result)
  }

  private fun assertError(error: NetworkResult, result: CurrencyData) {
    assertEquals(error, result.networkResult)
    assertTrue(result.mid.isEmpty())
    assertTrue(result.bid.isEmpty())
    assertTrue(result.ask.isEmpty())
    assertTrue(result.lastPrice.isEmpty())
    assertTrue(result.low.isEmpty())
    assertTrue(result.high.isEmpty())
    assertTrue(result.volume.isEmpty())
    assertTrue(result.timestamp.isEmpty())
  }
}