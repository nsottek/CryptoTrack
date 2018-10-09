package com.nathansottek.cryptotrack.network.main;

import com.nathansottek.cryptotrack.data.NetworkResult;
import com.nathansottek.cryptotrack.data.main.CurrencyData;
import com.nathansottek.cryptotrack.network.NetworkApi;
import io.reactivex.Single;

import javax.inject.Inject;

public class CurrencyClient {

  private NetworkApi networkApi;
  private CurrencyNetworkMapper networkMapper;

  @Inject
  public CurrencyClient(NetworkApi networkApi, CurrencyNetworkMapper networkMapper) {
    this.networkApi = networkApi;
    this.networkMapper = networkMapper;
  }

  public Single<CurrencyData> getCurrencyData(CurrencyData.Type currencyType) {
    return networkApi.getCurrencyUpdate(networkMapper.toRequestString(currencyType))
        .map(currencyResponse -> networkMapper.toCurrencyData(currencyType, currencyResponse))
        .onErrorReturnItem(new CurrencyData(NetworkResult.ERROR));
  }
}
