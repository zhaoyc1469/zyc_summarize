package com.app.frame.https;

import com.app.frame.manager.AppManager;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    private static volatile RetrofitClient retrofitClient;

    private Retrofit retrofit;

    private RetrofitClient() {
        //网络库
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
        CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(HttpConstant.getBaseUrl())
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }

    public static RetrofitClient getInstance() {
        if (retrofitClient == null) {
            synchronized (RetrofitClient.class) {
                if (retrofitClient == null) {
                    retrofitClient = new RetrofitClient();
                }
            }
        }
        return retrofitClient;
    }

    public <T> T createNet(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofit.create(service);
    }
}
