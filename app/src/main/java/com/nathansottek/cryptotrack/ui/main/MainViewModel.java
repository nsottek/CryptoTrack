package com.nathansottek.cryptotrack.ui.main;


import androidx.lifecycle.ViewModel;
import com.nathansottek.cryptotrack.data.CurrencyData;
import com.nathansottek.cryptotrack.data.main.MainRepository;
import io.reactivex.Single;

import javax.inject.Inject;

class MainViewModel extends ViewModel {

  private MainRepository mainRepository;

  @Inject
  public MainViewModel(MainRepository mainRepository) {
   this.mainRepository = mainRepository;
  }

  public Single<CurrencyData> getBcc() {
    return mainRepository.getBccData();
  }

  public Single<CurrencyData> getBtc() {
    return mainRepository.getBtcData();
  }

  public Single<CurrencyData> getEth() {
    return mainRepository.getEthData();
  }

  public Single<CurrencyData> getLtc() {
    return mainRepository.getLtcData();
  }

  public Single<CurrencyData> getNeo() {
    return mainRepository.getNeoData();
  }

  @Override
  protected void onCleared() {
    super.onCleared();
  }
}
