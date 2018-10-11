package com.nathansottek.cryptotrack.ui.main;


import android.arch.lifecycle.ViewModel;
import com.nathansottek.cryptotrack.data.main.CurrencyData;
import com.nathansottek.cryptotrack.data.main.MainRepository;
import io.reactivex.Single;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

  private MainRepository mainRepository;

  @Inject
  public MainViewModel(MainRepository mainRepository) {
    this.mainRepository = mainRepository;
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

  public Single<CurrencyData> getData(int position) {
    switch (position) {
      case 0:
        return mainRepository.getBtcData();
      case 1:
        return mainRepository.getEthData();
      case 2:
        return mainRepository.getLtcData();
      case 3:
        return mainRepository.getNeoData();
      case 4:
        return mainRepository.getXrpData();
      default:
        throw new IllegalArgumentException("Illegal position requested");
    }
  }
}