package com.nathansottek.cryptotrack.data.main

import com.nathansottek.cryptotrack.factory.DataFactory
import com.nathansottek.cryptotrack.network.main.CurrencyClient
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
class MainRepositoryTest {

  @Mock lateinit var mockCurrencyClient: CurrencyClient

  @InjectMocks lateinit var repository: MainRepository

  @BeforeTest fun setUp() {
    whenever(mockCurrencyClient.getCurrencyData(any())).thenAnswer { i -> Single.just(DataFactory.currencyData(i.getArgument(0))) }
  }

  @Test fun getBccData() {
    val testObserver = repository.bccData.test()

    testObserver.assertValue {
      assertEquals(CurrencyData.Type.BCC, it.currencyType)
      true
    }
  }

  @Test fun getBtcData() {
    val testObserver = repository.btcData.test()

    testObserver.assertValue {
      assertEquals(CurrencyData.Type.BTC, it.currencyType)
      true
    }
  }

  @Test fun getEthData() {
    val testObserver = repository.ethData.test()

    testObserver.assertValue {
      assertEquals(CurrencyData.Type.ETH, it.currencyType)
      true
    }
  }

  @Test fun getLtcData() {
    val testObserver = repository.ltcData.test()

    testObserver.assertValue {
      assertEquals(CurrencyData.Type.LTC, it.currencyType)
      true
    }
  }

  @Test fun getNeoData() {
    val testObserver = repository.neoData.test()

    testObserver.assertValue {
      assertEquals(CurrencyData.Type.NEO, it.currencyType)
      true
    }
  }
}