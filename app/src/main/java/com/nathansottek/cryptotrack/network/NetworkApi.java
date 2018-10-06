package com.nathansottek.cryptotrack.network;

import com.nathansottek.cryptotrack.network.main.CurrencyResponse;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Interface that defines all network requests for the application
 * Requests should be organized by feature
 */
public interface NetworkApi {

  @GET("pubticker/{currency_symbol}")
  Single<CurrencyResponse> getCurrencyUpdate(@Path("currency_symbol") String currencySymbol);

}
