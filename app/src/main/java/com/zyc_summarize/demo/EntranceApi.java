package com.zyc_summarize.demo;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface EntranceApi {

    @GET("action/apiv2/banner?catalog=1")
    Observable<Void> demoGet();

    @GET("action/apiv2/banner?catalog=1")
    Observable<Void> login();
}
