package com.nathansottek.cryptotrack.network.main;

import com.nathansottek.cryptotrack.data.CurrencyData;
import com.nathansottek.cryptotrack.network.NetworkApi;
import io.reactivex.Single;

import javax.inject.Inject;

public class CurrencyClient {

  private NetworkApi networkApi;

  @Inject
  public CurrencyClient(NetworkApi networkApi) {
    this.networkApi = networkApi;
  }

  public Single<CurrencyData> getCurrencyData(CurrencyData.Type currencyType) {
    return networkApi.getCurrencyUpdate(toRequestString(currencyType))
        .map(currencyResponse -> toCurrencyData(currencyType, currencyResponse));
  }

  private String toRequestString(CurrencyData.Type currencyType) {
    switch (currencyType) {
      case BCC:
        return "bccusd";
      case BTC:
        return "btcusd";
      case ETH:
        return "ethusd";
      case LTC:
        return "ltcusd";
      case NEO:
        return "neousd";
      default:
        throw new IllegalArgumentException("Unknown currency type requested");
    }
  }

  private CurrencyData toCurrencyData(CurrencyData.Type type, CurrencyResponse response) {
    return new CurrencyData(type, response.mid, response.bid, response.ask, response.lastPrice, response.low,
        response.high, response.volume, response.timestamp);
  }
}
