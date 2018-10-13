package com.nathansottek.cryptotrack.ui.main

import com.nathansottek.cryptotrack.data.main.MainRepository
import com.nathansottek.cryptotrack.factory.DataFactory
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

  @Mock lateinit var mainRepository: MainRepository

  @InjectMocks lateinit var viewModel: MainViewModel

  @Test fun getBtc() {
    val expected = DataFactory.currencyData()
    whenever(mainRepository.btcData).thenReturn(Single.just(expected))

    val testObserver = viewModel.getData(0).test()

    testObserver.assertValue(expected)
  }

  @Test fun getEth() {
    val expected = DataFactory.currencyData()
    whenever(mainRepository.ethData).thenReturn(Single.just(expected))

    val testObserver = viewModel.getData(1).test()

    testObserver.assertValue(expected)
  }

  @Test fun getLtc() {
    val expected = DataFactory.currencyData()
    whenever(mainRepository.ltcData).thenReturn(Single.just(expected))

    val testObserver = viewModel.getData(2).test()

    testObserver.assertValue(expected)
  }

  @Test fun getNeo() {
    val expected = DataFactory.currencyData()
    whenever(mainRepository.neoData).thenReturn(Single.just(expected))

    val testObserver = viewModel.getData(3).test()

    testObserver.assertValue(expected)
  }

  @Test fun getXrp() {
    val expected = DataFactory.currencyData()
    whenever(mainRepository.xrpData).thenReturn(Single.just(expected))

    val testObserver = viewModel.getData(4).test()

    testObserver.assertValue(expected)
  }

  @Test fun getSymbols() {
    val expected = "BTCUSD"
    whenever(mainRepository.symbols).thenReturn(Single.just(listOf(expected)))

    val testObserver = viewModel.symbols.test()

    testObserver.assertValue {
      assertEquals(expected, it[0])
      true
    }
  }

}