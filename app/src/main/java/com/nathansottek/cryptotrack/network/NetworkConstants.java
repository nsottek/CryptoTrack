package com.nathansottek.cryptotrack.network;

public class NetworkConstants {

  private NetworkConstants() {
    // Empty private constructor for constants class
  }

  public static String BASE_URL = "https://api.bitfinex.com/v1/";
  public static int REQUEST_TIMEOUT = 20; // in seconds

  // Request symbols
  public static String BTC_REQUEST_SYMBOL = "btcusd";
  public static String ETH_REQUEST_SYMBOL = "ethusd";
  public static String LTC_REQUEST_SYMBOL = "ltcusd";
  public static String NEO_REQUEST_SYMBOL = "neousd";
  public static String XRP_REQUEST_SYMBOL = "xrpusd";

  public static int HTTP_TOO_MANY_REQUESTS_ERROR = 429;
}
