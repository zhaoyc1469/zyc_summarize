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

public class DataClient {

    private static volatile DataClient mDataClient;
    //接口
    private String baseUrl = "https://www.baidu.com/";

    //网络库
    private OkHttpClient okHttpClient;
    private Converter.Factory gsonConverterFactory;
    private CallAdapter.Factory rxJavaCallAdapterFactory;
    private Retrofit retrofit;
    private Map<String, Retrofit> retrofitMap;

    private DataClient() {
        okHttpClient = new OkHttpClient.Builder().build();
        gsonConverterFactory = GsonConverterFactory.create();
        rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();


        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }

    public static DataClient getInstance() {
        if (mDataClient == null) {
            synchronized (AppManager.class) {
                if (mDataClient == null) {
                    mDataClient = new DataClient();
                }
            }
        }
        return mDataClient;
    }

    public <T> T createNet(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofit.create(service);
    }

    public <T> T createNet(final Class<T> service, String thisUrl) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        if (retrofitMap == null) {
            retrofitMap = new HashMap<>();
            return createNet(service, thisUrl, true);
        }
        if (retrofitMap.containsKey(thisUrl)) {
            Retrofit retrofit = retrofitMap.get(thisUrl);
            if (retrofit != null)
                return retrofit.create(service);
            return createNet(service, thisUrl, true);
        }
        return createNet(service, thisUrl, true);

    }

    public <T> T createNet(final Class<T> service, String thisUrl, boolean save) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        if (save) {
            if (retrofitMap == null) {
                retrofitMap = new HashMap<>();
            }
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(thisUrl)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            retrofitMap.put(thisUrl, retrofit);
            return retrofit.create(service);
        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(thisUrl)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            return retrofit.create(service);
        }
    }

}
