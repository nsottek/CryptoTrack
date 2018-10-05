package com.nathansottek.cryptotrack.ui.main;

import android.os.Bundle;
import android.widget.TextView;

import com.nathansottek.cryptotrack.R;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        addSubscription(viewModel.getBcc()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateBcc));
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

    private void updateBcc(String result) {
        bccView.setText(result);
    }

    private void updateBtc(String result) {
        btcView.setText(result);
    }

    private void updateEth(String result) {
        ethView.setText(result);
    }

    private void updateLtc(String result) {
        ltcView.setText(result);
    }

    private void updateNeo(String result) {
        neoView.setText(result);
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
