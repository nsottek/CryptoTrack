package com.nathansottek.cryptotrack.ui.main;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.nathansottek.cryptotrack.R;
import com.nathansottek.cryptotrack.data.CurrencyData;
import com.nathansottek.cryptotrack.module.ApplicationComponentHolder;
import com.nathansottek.cryptotrack.ui.base.GenericViewModelFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

  private static final String currencyFormat = "%s: $%s";

  @Inject GenericViewModelFactory<MainViewModel> viewModelFactory;

  @BindView(R.id.bcc) TextView bccView;
  @BindView(R.id.btc) TextView btcView;
  @BindView(R.id.eth) TextView ethView;
  @BindView(R.id.ltc) TextView ltcView;
  @BindView(R.id.neo) TextView neoView;

  MainViewModel viewModel;
  CompositeDisposable compositeDisposable = new CompositeDisposable();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ApplicationComponentHolder.getInstance().getComponent().inject(this);
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
  }

  @Override
  protected void onStart() {
    super.onStart();
    // TODO: BCCUSD currently unavailable from API
//    addSubscription(viewModel.getBcc()
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(this::updateBcc));
    addSubscription(viewModel.getBtc()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::updateBtc));
    addSubscription(viewModel.getEth()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::updateEth));
    addSubscription(viewModel.getLtc()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::updateLtc));
    addSubscription(viewModel.getNeo()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::updateNeo));
  }

  private void updateBcc(CurrencyData result) {
    updateView(bccView, result);
  }

  private void updateBtc(CurrencyData result) {
    updateView(btcView, result);
  }

  private void updateEth(CurrencyData result) {
    updateView(ethView, result);
  }

  private void updateLtc(CurrencyData result) {
    updateView(ltcView, result);
  }

  private void updateNeo(CurrencyData result) {
    updateView(neoView, result);
  }

  private void updateView(TextView view, CurrencyData data) {
    view.setText(String.format(currencyFormat, data.currencyType, data.ask));
  }

  private void addSubscription(Disposable disposable) {
    compositeDisposable.add(disposable);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    compositeDisposable.dispose();
  }
}
