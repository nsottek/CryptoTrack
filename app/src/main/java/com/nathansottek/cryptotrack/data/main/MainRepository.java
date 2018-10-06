package com.nathansottek.cryptotrack.data.main;

import com.nathansottek.cryptotrack.data.CurrencyData;
import io.reactivex.Observable;

import javax.inject.Inject;

public class MainRepository {

  private CurrencyData bccData = new CurrencyData(CurrencyData.Type.BCC,"6593.45", "6593.4", "6593.5", "6593.5", "6563.7", "6741.4366438", "10387.98909321", "1538844797.3928764");
  private CurrencyData btcData = new CurrencyData(CurrencyData.Type.BTC,"6593.45", "6593.4", "6593.5", "6593.5", "6563.7", "6741.4366438", "10387.98909321", "1538844797.3928764");
  private CurrencyData ethData = new CurrencyData(CurrencyData.Type.ETH,"6593.45", "6593.4", "6593.5", "6593.5", "6563.7", "6741.4366438", "10387.98909321", "1538844797.3928764");
  private CurrencyData ltcData = new CurrencyData(CurrencyData.Type.LTC,"6593.45", "6593.4", "6593.5", "6593.5", "6563.7", "6741.4366438", "10387.98909321", "1538844797.3928764");
  private CurrencyData neoData = new CurrencyData(CurrencyData.Type.NEO,"6593.45", "6593.4", "6593.5", "6593.5", "6563.7", "6741.4366438", "10387.98909321", "1538844797.3928764");

  @Inject
  public MainRepository() {
    // Empty for Dagger
  }

  public Observable<CurrencyData> getBccData() {
    return Observable.just(bccData);
  }

  public Observable<CurrencyData> getBtcData() {
    return Observable.just(btcData);
  }

  public Observable<CurrencyData> getEthData() {
    return Observable.just(ethData);
  }

  public Observable<CurrencyData> getLtcData() {
    return Observable.just(ltcData);
  }

  public Observable<CurrencyData> getNeoData() {
    return Observable.just(neoData);
  }
}
