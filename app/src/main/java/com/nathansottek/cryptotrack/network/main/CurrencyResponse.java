package com.nathansottek.cryptotrack.network.main;

import com.squareup.moshi.Json;

public class CurrencyResponse {

  @Json(name = "mid") final String mid;
  @Json(name = "bid") final String bid;
  @Json(name = "ask") final String ask;
  @Json(name = "last_price") final String lastPrice;
  @Json(name = "low") final String low;
  @Json(name = "high") final String high;
  @Json(name = "volume") final String volume;
  @Json(name = "timestamp") final String timestamp;

  public CurrencyResponse(String mid, String bid, String ask, String lastPrice, String low,
      String high, String volume, String timestamp) {
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
