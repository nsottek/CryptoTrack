package com.nathansottek.cryptotrack.factory

import com.nathansottek.cryptotrack.data.CurrencyData

object DataFactory {

  fun currencyData(currencyType: CurrencyData.Type = randomCurrencyDataType()) = CurrencyData(currencyType, PrimitiveFactory.randomString(), PrimitiveFactory.randomString(), PrimitiveFactory.randomString(), PrimitiveFactory.randomString(), PrimitiveFactory.randomString(), PrimitiveFactory.randomString(), PrimitiveFactory.randomString(), PrimitiveFactory.randomString())

  fun randomCurrencyDataType() = CurrencyData.Type.values()[PrimitiveFactory.randomInt(0, CurrencyData.Type.values().size - 1)]
}