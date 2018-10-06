package com.nathansottek.cryptotrack.ui.main;


import androidx.lifecycle.ViewModel;
import com.nathansottek.cryptotrack.data.CurrencyData;
import com.nathansottek.cryptotrack.data.main.MainRepository;
import io.reactivex.Observable;

import javax.inject.Inject;

class MainViewModel extends ViewModel {

  private MainRepository mainRepository;

  @Inject
  public MainViewModel(MainRepository mainRepository) {
   this.mainRepository = mainRepository;
  }

  public Observable<CurrencyData> getBcc() {
    return mainRepository.getBccData();
  }

  public Observable<CurrencyData> getBtc() {
    return mainRepository.getBtcData();
  }

  public Observable<CurrencyData> getEth() {
    return mainRepository.getEthData();
  }

  public Observable<CurrencyData> getLtc() {
    return mainRepository.getLtcData();
  }

  public Observable<CurrencyData> getNeo() {
    return mainRepository.getNeoData();
  }


}
