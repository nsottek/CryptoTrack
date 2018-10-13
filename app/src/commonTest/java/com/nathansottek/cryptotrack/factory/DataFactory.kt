package com.nathansottek.cryptotrack.factory

import com.nathansottek.cryptotrack.data.main.CurrencyData

object DataFactory {

  fun currencyData(currencyType: CurrencyData.Type = randomCurrencyDataType()): CurrencyData {
    val high = PrimitiveFactory.randomNumberString().toLong()
    val mid = high/2
    val low = 0
    return CurrencyData(currencyType, mid.toString(), PrimitiveFactory.randomString(), PrimitiveFactory.randomString(), PrimitiveFactory.randomString(), low.toString(), high.toString(), PrimitiveFactory.randomNumberString(), "${PrimitiveFactory.randomNumberString(10)}.${PrimitiveFactory.randomNumberString(6)}")
  }

  fun randomCurrencyDataType() = CurrencyData.Type.values()[PrimitiveFactory.randomInt(0, CurrencyData.Type.values().size - 1)]
}