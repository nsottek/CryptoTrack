package com.nathansottek.cryptotrack.network.main

import com.nathansottek.cryptotrack.data.NetworkResult
import com.nathansottek.cryptotrack.factory.DataFactory
import com.nathansottek.cryptotrack.factory.PrimitiveFactory
import com.nathansottek.cryptotrack.network.NetworkApi
import com.nathansottek.cryptotrack.network.ResponseFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class CurrencyClientTest {

  @Mock lateinit var networkApi: NetworkApi
  @Mock lateinit var networkMapper: CurrencyNetworkMapper

  @InjectMocks lateinit var currencyClient: CurrencyClient

  @BeforeTest fun setUp() {
    whenever(networkMapper.toRequestString(any())).thenReturn(PrimitiveFactory.randomString())
    whenever(networkApi.getCurrencyUpdate(any())).thenReturn(Single.just(ResponseFactory.currencyResponse()))
  }

  @Test fun getCurrencyData_returnsFromClient() {
    val expected = DataFactory.currencyData()
    whenever(networkMapper.toCurrencyData(any(), any())).thenReturn(expected)

    val testObserver = currencyClient.getCurrencyData(DataFactory.randomCurrencyDataType()).test()

    testObserver.assertValueCount(1)
        .assertValue(expected)
  }

  @Test fun getCurrencyData_returnsError() {
    whenever(networkApi.getCurrencyUpdate(any())).thenReturn(Single.error(UnsupportedOperationException()))

    val testObserver = currencyClient.getCurrencyData(DataFactory.randomCurrencyDataType()).test()

    testObserver.assertValueCount(1)
        .assertValue {
          assertEquals(NetworkResult.ERROR, it.networkResult)
          true
        }
  }

}