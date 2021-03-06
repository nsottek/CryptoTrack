package com.nathansottek.cryptotrack.ui.main;


import android.arch.lifecycle.ViewModel;
import com.nathansottek.cryptotrack.data.main.CurrencyData;
import com.nathansottek.cryptotrack.data.main.MainRepository;
import io.reactivex.Single;

import javax.inject.Inject;
import java.util.List;

public class MainViewModel extends ViewModel {

  private MainRepository mainRepository;

  @Inject
  MainViewModel(MainRepository mainRepository) {
    this.mainRepository = mainRepository;
  }

  public Single<List<String>> getSymbols() {
    return mainRepository.getSymbols();
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