package com.nathansottek.cryptotrack.data.main;

import com.nathansottek.cryptotrack.data.NetworkResult;

public class CurrencyData {

  public enum Type {
    BTC("BTCUSD"),
    ETH("ETHUSD"),
    LTC("LTCUSD"),
    NEO("NEOUSD"),
    XRP("XRPUSD");

    public final String symbol;

    Type(String symbol) {
      this.symbol = symbol;
    }
  }

  public final Type currencyType;
  public final String mid;
  public final String bid;
  public final String ask;
  public final String lastPrice;
  public final String low;
  public final String high;
  public final String volume;
  public final String timestamp;
  public final NetworkResult networkResult;

  public CurrencyData(Type currencyType, String mid, String bid, String ask, String lastPrice,
      String low, String high, String volume, String timestamp) {
    this.currencyType = currencyType;
    this.mid = mid;
    this.bid = bid;
    this.ask = ask;
    this.lastPrice = lastPrice;
    this.low = low;
    this.high = high;
    this.volume = volume;
    this.timestamp = timestamp;
    this.networkResult = NetworkResult.SUCCESS;
  }

  public CurrencyData(NetworkResult networkResult) {
    if (networkResult == NetworkResult.SUCCESS) {
      throw new UnsupportedOperationException("Any success indication should also have data populated");
    }

    this.networkResult = networkResult;
    this.currencyType = null;
    mid = bid = ask = lastPrice = low = high = volume = timestamp = "";
  }
}
