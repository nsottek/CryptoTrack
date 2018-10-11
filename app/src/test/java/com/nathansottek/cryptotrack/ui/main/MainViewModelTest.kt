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

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

  @Mock lateinit var mainRepository: MainRepository

  @InjectMocks lateinit var viewModel: MainViewModel

  @Test fun getBcc() {
    val expected = DataFactory.currencyData()
    whenever(mainRepository.xrpData).thenReturn(Single.just(expected))

    val testObserver = viewModel.bcc.test()

    testObserver.assertValue(expected)
  }

  @Test fun getBtc() {
    val expected = DataFactory.currencyData()
    whenever(mainRepository.btcData).thenReturn(Single.just(expected))

    val testObserver = viewModel.btc.test()

    testObserver.assertValue(expected)
  }

  @Test fun getEth() {
    val expected = DataFactory.currencyData()
    whenever(mainRepository.ethData).thenReturn(Single.just(expected))

    val testObserver = viewModel.eth.test()

    testObserver.assertValue(expected)
  }

  @Test fun getLtc() {
    val expected = DataFactory.currencyData()
    whenever(mainRepository.ltcData).thenReturn(Single.just(expected))

    val testObserver = viewModel.ltc.test()

    testObserver.assertValue(expected)
  }

  @Test fun getNeo() {
    val expected = DataFactory.currencyData()
    whenever(mainRepository.neoData).thenReturn(Single.just(expected))

    val testObserver = viewModel.neo.test()

    testObserver.assertValue(expected)
  }

}