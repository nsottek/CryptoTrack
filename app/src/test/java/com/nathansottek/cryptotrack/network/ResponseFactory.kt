package com.nathansottek.cryptotrack.network

import com.nathansottek.cryptotrack.factory.PrimitiveFactory
import com.nathansottek.cryptotrack.network.main.CurrencyResponse

object ResponseFactory {

  fun currencyResponse() = CurrencyResponse(PrimitiveFactory.randomString(), PrimitiveFactory.randomString(),
      PrimitiveFactory.randomString(), PrimitiveFactory.randomString(), PrimitiveFactory.randomString(),
      PrimitiveFactory.randomString(), PrimitiveFactory.randomString(), PrimitiveFactory.randomString())
}