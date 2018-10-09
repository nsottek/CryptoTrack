package com.nathansottek.cryptotrack.network.main

import com.nathansottek.cryptotrack.data.main.CurrencyData
import com.nathansottek.cryptotrack.factory.DataFactory
import com.nathansottek.cryptotrack.network.NetworkConstants
import com.nathansottek.cryptotrack.network.ResponseFactory
import kotlin.test.Test
import kotlin.test.assertEquals

class CurrencyNetworkMapperTest {

  private val networkMapper: CurrencyNetworkMapper = CurrencyNetworkMapper()

  @Test fun toRequestString_BCC() {
    assertCorrectRequestMapping(CurrencyData.Type.BCC, NetworkConstants.BCC_REQUEST_SYMBOL)
  }

  @Test fun toRequestString_BTC() {
    assertCorrectRequestMapping(CurrencyData.Type.BTC, NetworkConstants.BTC_REQUEST_SYMBOL)
  }

  @Test fun toRequestString_ETH() {
    assertCorrectRequestMapping(CurrencyData.Type.ETH, NetworkConstants.ETH_REQUEST_SYMBOL)
  }

  @Test fun toRequestString_LTC() {
    assertCorrectRequestMapping(CurrencyData.Type.LTC, NetworkConstants.LTC_REQUEST_SYMBOL)
  }

  @Test fun toRequestString_NEO() {
    assertCorrectRequestMapping(CurrencyData.Type.NEO, NetworkConstants.NEO_REQUEST_SYMBOL)
  }

  private fun assertCorrectRequestMapping(type: CurrencyData.Type, expectedSymbol: String) {
    val result = networkMapper.toRequestString(type)
    assertEquals(expectedSymbol, result)
  }

  @Test fun toCurrencyData() {
    val expectedType = DataFactory.randomCurrencyDataType()
    val response = ResponseFactory.currencyResponse()

    val result = networkMapper.toCurrencyData(expectedType, response)

    assertEquals(result.currencyType, expectedType)
    assertEquals(result.mid, response.mid)
    assertEquals(result.bid, response.bid)
    assertEquals(result.ask, response.ask)
    assertEquals(result.lastPrice, response.lastPrice)
    assertEquals(result.low, response.low)
    assertEquals(result.high, response.high)
    assertEquals(result.volume, response.volume)
    assertEquals(result.timestamp, response.timestamp)
  }
}