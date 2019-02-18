package com.zyc_summarize.base.https;

import com.zyc_summarize.base.manager.AppManager;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpUtil {

    private static volatile HttpUtil mHttpUtil;
    //接口
    private Api api;

    //网络库
    private OkHttpClient okHttpClient;
    private Converter.Factory gsonConverterFactory;
    private CallAdapter.Factory rxJavaCallAdapterFactory;

    private HttpUtil(){
        okHttpClient = new OkHttpClient.Builder().build();
        gsonConverterFactory = GsonConverterFactory.create();
        rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();
    }

    public static HttpUtil getInstance() {
        if (mHttpUtil == null) {
            synchronized (AppManager.class) {
                if (mHttpUtil == null) {
                    mHttpUtil = new HttpUtil();
                }
            }
        }
        return mHttpUtil;
    }

    public Api getApi() {
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Api.baseUrl)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            api = retrofit.create(Api.class);
        }
        return api;
    }

}
