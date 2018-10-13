package com.nathansottek.cryptotrack.data.main;

import com.nathansottek.cryptotrack.network.main.CurrencyClient;
import io.reactivex.Single;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class MainRepository {

  private CurrencyClient currencyClient;

  @Inject
  public MainRepository(CurrencyClient currencyClient) {
    this.currencyClient = currencyClient;
  }

  public Single<CurrencyData> getBtcData() {
    return currencyClient.getCurrencyData(CurrencyData.Type.BTC);
  }

  public Single<CurrencyData> getEthData() {
    return currencyClient.getCurrencyData(CurrencyData.Type.ETH);
  }

  public Single<CurrencyData> getLtcData() {
    return currencyClient.getCurrencyData(CurrencyData.Type.LTC);
  }

  public Single<CurrencyData> getNeoData() {
    return currencyClient.getCurrencyData(CurrencyData.Type.NEO);
  }

  public Single<CurrencyData> getXrpData() {
    return currencyClient.getCurrencyData(CurrencyData.Type.XRP);
  }

  public Single<List<String>> getSymbols() {
    ArrayList<String> symbols = new ArrayList<>();
    symbols.add(CurrencyData.Type.BTC.symbol);
    symbols.add(CurrencyData.Type.ETH.symbol);
    symbols.add(CurrencyData.Type.LTC.symbol);
    symbols.add(CurrencyData.Type.NEO.symbol);
    symbols.add(CurrencyData.Type.XRP.symbol);
    return Single.just(symbols);
  }
}
