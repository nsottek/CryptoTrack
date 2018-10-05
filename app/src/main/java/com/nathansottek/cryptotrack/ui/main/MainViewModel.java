package com.nathansottek.cryptotrack.ui.main;


import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

class MainViewModel extends ViewModel {

    private PublishSubject<String> bccSubject = PublishSubject.create();
    private PublishSubject<String> btcSubject = PublishSubject.create();
    private PublishSubject<String> ethSubject = PublishSubject.create();
    private PublishSubject<String> ltcSubject = PublishSubject.create();
    private PublishSubject<String> neoSubject = PublishSubject.create();

    public MainViewModel() {
        // Empty for future Dagger injections
    }

    public Observable<String> getBcc() {
        return bccSubject.startWith("bcc");
    }

    public Observable<String> getBtc() {
        return btcSubject.startWith("btc");
    }

    public Observable<String> getEth() {
        return ethSubject.startWith("eth");
    }

    public Observable<String> getLtc() {
        return ltcSubject.startWith("ltc");
    }

    public Observable<String> getNeo() {
        return neoSubject.startWith("neo");
    }


}
