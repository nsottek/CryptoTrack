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
    addSubscription(viewModel.getSymbols()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::initRecyclerview));
  }

  private void initRecyclerview(List<String> symbols) {
    LinearLayoutManager layoutManager
        = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    symbolRecyclerview.setLayoutManager(layoutManager);

    SymbolAdapter adapter = new SymbolAdapter(this, symbols);
    symbolRecyclerview.setAdapter(adapter);

    LinearSnapHelper snapHelper = new LinearSnapHelper();
    snapHelper.attachToRecyclerView(symbolRecyclerview);

    symbolRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
          View snapView = snapHelper.findSnapView(layoutManager);
          int snapPosition = layoutManager.getPosition(snapView);
          adapter.onScrollFinished(snapPosition);
        }
      }
    });

    symbolRecyclerview.scrollToPosition(adapter.getStartingPosition() - 5);
    symbolRecyclerview.smoothScrollToPosition(adapter.getStartingPosition());
  }

  private void handleCurrencyData(CurrencyData result) {
    if (result.networkResult != NetworkResult.SUCCESS) {
      Snackbar.make(findViewById(android.R.id.content), R.string.generic_network_error, Snackbar.LENGTH_SHORT);
    } else {
      updateViews(result);
    }
  }

  private void updateViews(CurrencyData data) {
    midValueView.setText(String.format("$%s", data.mid));
    Date date = new Date(Long.parseLong(data.timestamp.split("\\.")[0]) * 1000);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
    simpleDateFormat.setTimeZone(TimeZone.getDefault());
    updatedAtView.setText(String.format("Updated at %s", simpleDateFormat.format(date)));
    lowValueView.setText(String.format("$%s", data.low));
    highValueView.setText(String.format("$%s", data.high));
    DecimalFormat df = new DecimalFormat("#.##");
    df.setRoundingMode(RoundingMode.HALF_UP);
    volumeValueView.setText(String.format("Volume: %s", df.format(Double.valueOf(data.volume))));
    askValueView.setText(String.format("Ask: $%s", data.ask));
    bidValueView.setText(String.format("Bid: $%s", data.bid));

    ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) lowHighIndicatorView.getLayoutParams();
    params.horizontalBias = (Float.parseFloat(data.mid) - Float.parseFloat(data.low)) / (Float.parseFloat(data.high) - Float.parseFloat(data.low));
    lowHighIndicatorView.setLayoutParams(params);
  }

  private void addSubscription(Disposable disposable) {
    compositeDisposable.add(disposable);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    compositeDisposable.dispose();
  }

  @Override
  public void onSymbolSelected(int position) {
    addSubscription(viewModel.getData(position)
    .observeOn(AndroidSchedulers.mainThread())
    .subscribe(this::handleCurrencyData));
  }
}
