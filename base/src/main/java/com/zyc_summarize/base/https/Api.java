package com.zyc_summarize.base.https;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    String baseUrl = "";



    @GET("action/apiv2/banner?catalog=1")
    Observable<Void> demoGet();
}
