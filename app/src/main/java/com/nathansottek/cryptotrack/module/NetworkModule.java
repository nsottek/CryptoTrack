package com.nathansottek.cryptotrack.module;

import com.nathansottek.cryptotrack.network.NetworkApi;
import com.squareup.moshi.Moshi;
import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import javax.inject.Singleton;
import java.util.concurrent.TimeUnit;

import static com.nathansottek.cryptotrack.network.NetworkConstants.BASE_URL;
import static com.nathansottek.cryptotrack.network.NetworkConstants.REQUEST_TIMEOUT;

@Module
public class NetworkModule {

  public NetworkModule() {
    // Empty constructor
  }

  @Provides
  @Singleton
  Moshi providesMoshi() {
    return new Moshi.Builder().build();
  }

  @Provides
  @Singleton
  NetworkApi providesNetworkApi(Retrofit retrofit) {
    return retrofit.create(NetworkApi.class);
  }

  @Provides
  @Singleton
  OkHttpClient providesOkHttpClient() {
    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
        .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    clientBuilder.addInterceptor(loggingInterceptor);

    return clientBuilder.build();
  }

  @Provides
  @Singleton
  Retrofit providesRetrofit(OkHttpClient okHttpClient, Moshi moshi) {
    return new Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build();
  }
}
