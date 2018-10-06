package com.nathansottek.cryptotrack.data;

public class CurrencyData {

  public enum Type {
    BCC,
    BTC,
    ETH,
    LTC,
    NEO
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
  }
}
