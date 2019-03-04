package com.app.entrance;

import com.app.entrance.entity.TextBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface EntranceApi {

    @GET("action/apiv2/banner?catalog=1")
    Observable<TextBean> login();

    @GET("action/apiv2/banner?catalog=1")
    Observable<TextBean> demoGet();
}
