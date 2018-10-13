package com.nathansottek.cryptotrack.network.main;

import com.nathansottek.cryptotrack.data.NetworkResult;
import com.nathansottek.cryptotrack.data.main.CurrencyData;
import com.nathansottek.cryptotrack.network.NetworkConstants;
import retrofit2.HttpException;

import javax.inject.Inject;

import static com.nathansottek.cryptotrack.network.NetworkConstants.HTTP_TOO_MANY_REQUESTS_ERROR;

public class CurrencyNetworkMapper {

  @Inject
  public CurrencyNetworkMapper() {
    // Empty for Dagger
  }

  public String toRequestString(CurrencyData.Type currencyType) {
    switch (currencyType) {
      case BTC:
        return NetworkConstants.BTC_REQUEST_SYMBOL;
      case ETH:
        return NetworkConstants.ETH_REQUEST_SYMBOL;
      case LTC:
        return NetworkConstants.LTC_REQUEST_SYMBOL;
      case NEO:
        return NetworkConstants.NEO_REQUEST_SYMBOL;
      case XRP:
        return NetworkConstants.XRP_REQUEST_SYMBOL;
      default:
        throw new IllegalArgumentException("Unknown currency type requested");
    }
  }

  public CurrencyData toCurrencyData(CurrencyData.Type type, CurrencyResponse response) {
    return new CurrencyData(type, response.mid, response.bid, response.ask, response.lastPrice, response.low,
        response.high, response.volume, response.timestamp);
  }

  public CurrencyData toCurrencyDataError(Throwable throwable) {
    if (isTooManyRequestsError(throwable)) {
      return new CurrencyData(NetworkResult.TOO_MANY_REQUESTS);
    }
    return new CurrencyData(NetworkResult.ERROR);
  }

  private boolean isTooManyRequestsError(Throwable throwable) {
    return throwable instanceof HttpException && ((HttpException) throwable).code() == HTTP_TOO_MANY_REQUESTS_ERROR;
  }
}
