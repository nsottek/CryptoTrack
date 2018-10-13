package com.nathansottek.cryptotrack.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.nathansottek.cryptotrack.R;
import com.nathansottek.cryptotrack.data.NetworkResult;
import com.nathansottek.cryptotrack.data.main.CurrencyData;
import com.nathansottek.cryptotrack.module.ApplicationComponentHolder;
import com.nathansottek.cryptotrack.ui.base.GenericViewModelFactory;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import javax.inject.Inject;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements SymbolAdapterCallback {

  private static final String VOLUME_PATTERN = "#.##";
  private static final String TIMESTAMP_DELIMITER = "\\.";
  private static final String UPDATED_AT_DATE_FORMAT = "hh:mm a";

  @Inject GenericViewModelFactory<MainViewModel> viewModelFactory;

  @BindView(R.id.mid_value) TextView midValueView;
  @BindView(R.id.updated_at) TextView updatedAtView;
  @BindView(R.id.low_value) TextView lowValueView;
  @BindView(R.id.high_value) TextView highValueView;
  @BindView(R.id.low_high_indicator) ImageView lowHighIndicatorView;
  @BindView(R.id.volume_value) TextView volumeValueView;
  @BindView(R.id.ask_value) TextView askValueView;
  @BindView(R.id.bid_value) TextView bidValueView;
  @BindView(R.id.symbol_recyclerview) RecyclerView symbolRecyclerview;

  private MainViewModel viewModel;
  private SymbolAdapter adapter;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private Disposable lastDataDisposable;

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
    addSubscription(viewModel.getSymbols()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::initRecyclerview));
  }

  private void retrieveData(int position) {
    if (lastDataDisposable != null) {
      lastDataDisposable.dispose();
    }
    lastDataDisposable = viewModel.getData(position)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::handleCurrencyData);
    addSubscription(lastDataDisposable);
  }

  private void initRecyclerview(List<String> symbols) {
    LinearLayoutManager layoutManager = setLayoutManager();
    setAdapter(symbols);
    LinearSnapHelper snapHelper = setSnapHelper();
    addScrollListener(layoutManager, snapHelper);
    performInitialScroll();
  }

  @NonNull
  private LinearLayoutManager setLayoutManager() {
    LinearLayoutManager layoutManager
        = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    symbolRecyclerview.setLayoutManager(layoutManager);
    return layoutManager;
  }

  private void setAdapter(List<String> symbols) {
    adapter = new SymbolAdapter(this, symbols);
    symbolRecyclerview.setAdapter(adapter);
  }

  @NonNull
  private LinearSnapHelper setSnapHelper() {
    LinearSnapHelper snapHelper = new LinearSnapHelper();
    snapHelper.attachToRecyclerView(symbolRecyclerview);
    return snapHelper;
  }

  private void performInitialScroll() {
    symbolRecyclerview.scrollToPosition(adapter.getStartingPosition() - 5);
    symbolRecyclerview.smoothScrollToPosition(adapter.getStartingPosition());
    adapter.setSelectedPosition(adapter.getStartingPosition());
  }

  private void addScrollListener(LinearLayoutManager layoutManager, LinearSnapHelper snapHelper) {
    symbolRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
          View snapView = snapHelper.findSnapView(layoutManager);
          if (snapView != null) {
            int snapPosition = layoutManager.getPosition(snapView);
            adapter.setSelectedPosition(snapPosition);
            int dataPosition = adapter.getDataPosition(snapPosition);
            retrieveData(dataPosition);
          }
        }
      }
    });
  }

  private void handleCurrencyData(CurrencyData result) {
    if (result.networkResult != NetworkResult.SUCCESS) {
      handleDataError(result);
    } else {
      updateViews(result);
    }
  }

  private void handleDataError(CurrencyData errorData) {
    switch (errorData.networkResult) {
      case TOO_MANY_REQUESTS:
        Snackbar.make(findViewById(android.R.id.content), R.string.too_many_requests_network_error, Snackbar.LENGTH_SHORT).show();
        break;
      case ERROR:
      default:
        Snackbar.make(findViewById(android.R.id.content), R.string.generic_network_error, Snackbar.LENGTH_SHORT).show();
    }
  }

  private void updateViews(CurrencyData data) {
    setIndicator(data);
    setPrices(data);
    setUpdatedAt(data);
    setVolume(data);
  }

  private void setIndicator(CurrencyData data) {
    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) lowHighIndicatorView.getLayoutParams();
    params.horizontalBias = calculateIndicatorBias(data);
    lowHighIndicatorView.setLayoutParams(params);
  }

  private float calculateIndicatorBias(CurrencyData data) {
    return (Float.parseFloat(data.mid) - Float.parseFloat(data.low))
        / (Float.parseFloat(data.high) - Float.parseFloat(data.low));
  }

  private void setPrices(CurrencyData data) {
    midValueView.setText(getString(R.string.price_format, data.mid));
    lowValueView.setText(getString(R.string.price_format, data.low));
    highValueView.setText(getString(R.string.price_format, data.high));
    askValueView.setText(getString(R.string.ask, data.ask));
    bidValueView.setText(getString(R.string.bid, data.bid));
  }

  private void setUpdatedAt(CurrencyData data) {
    Date date = new Date(Long.parseLong(data.timestamp.split(TIMESTAMP_DELIMITER)[0]) * 1000);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(UPDATED_AT_DATE_FORMAT, Locale.getDefault());
    simpleDateFormat.setTimeZone(TimeZone.getDefault());
    updatedAtView.setText(getString(R.string.updated_at, simpleDateFormat.format(date)));
  }

  private void setVolume(CurrencyData data) {
    DecimalFormat df = new DecimalFormat(VOLUME_PATTERN);
    df.setRoundingMode(RoundingMode.HALF_UP);
    volumeValueView.setText(getString(R.string.volume, df.format(Double.valueOf(data.volume))));
  }

  private void addSubscription(Disposable disposable) {
    compositeDisposable.add(disposable);
  }

  @Override
  public void scrollToPosition(int position) {
    symbolRecyclerview.smoothScrollToPosition(position);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    compositeDisposable.dispose();
  }
}
