package com.nathansottek.cryptotrack.network.main

import com.nathansottek.cryptotrack.data.NetworkResult
import com.nathansottek.cryptotrack.data.main.CurrencyData
import com.nathansottek.cryptotrack.factory.DataFactory
import com.nathansottek.cryptotrack.network.NetworkConstants
import com.nathansottek.cryptotrack.network.NetworkConstants.HTTP_TOO_MANY_REQUESTS_ERROR
import com.nathansottek.cryptotrack.network.ResponseFactory
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import retrofit2.HttpException
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CurrencyNetworkMapperTest {

  private val networkMapper: CurrencyNetworkMapper = CurrencyNetworkMapper()

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

  @Test fun toRequestString_XRP() {
    assertCorrectRequestMapping(CurrencyData.Type.XRP, NetworkConstants.XRP_REQUEST_SYMBOL)
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

  @Test fun toCurrencyDataError_tooManyRequests() {
    val expectedType = NetworkResult.TOO_MANY_REQUESTS
    val response = mock<retrofit2.Response<*>>()
    whenever(response.code()).thenReturn(HTTP_TOO_MANY_REQUESTS_ERROR)
    val throwable = HttpException(response)

    val result = networkMapper.toCurrencyDataError(throwable)

    assertEquals(result.networkResult, expectedType)
    assertTrue(result.mid.isEmpty())
    assertTrue(result.bid.isEmpty())
    assertTrue(result.ask.isEmpty())
    assertTrue(result.lastPrice.isEmpty())
    assertTrue(result.low.isEmpty())
    assertTrue(result.high.isEmpty())
    assertTrue(result.volume.isEmpty())
    assertTrue(result.timestamp.isEmpty())
  }

  @Test fun toCurrencyDataError_generic() {
    val expectedType = NetworkResult.ERROR
    val response = mock<retrofit2.Response<*>>()
    whenever(response.code()).thenReturn(400)
    val throwable = HttpException(response)

    val result = networkMapper.toCurrencyDataError(throwable)

    assertEquals(expectedType, result.networkResult)
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