package com.nathansottek.cryptotrack.network.main;

import com.nathansottek.cryptotrack.data.main.CurrencyData;
import com.nathansottek.cryptotrack.network.NetworkConstants;

import javax.inject.Inject;

public class CurrencyNetworkMapper {

  @Inject
  public CurrencyNetworkMapper() {
    // Empty for Dagger
  }

  public String toRequestString(CurrencyData.Type currencyType) {
    switch (currencyType) {
      case BCC:
        return NetworkConstants.BCC_REQUEST_SYMBOL;
      case BTC:
        return NetworkConstants.BTC_REQUEST_SYMBOL;
      case ETH:
        return NetworkConstants.ETH_REQUEST_SYMBOL;
      case LTC:
        return NetworkConstants.LTC_REQUEST_SYMBOL;
      case NEO:
        return NetworkConstants.NEO_REQUEST_SYMBOL;
      default:
        throw new IllegalArgumentException("Unknown currency type requested");
    }
  }

  public CurrencyData toCurrencyData(CurrencyData.Type type, CurrencyResponse response) {
    return new CurrencyData(type, response.mid, response.bid, response.ask, response.lastPrice, response.low,
        response.high, response.volume, response.timestamp);
  }
}
