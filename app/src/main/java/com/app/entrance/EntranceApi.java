package com.app.entrance;

import com.app.entrance.entity.TextBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EntranceApi {

    @GET("action/apiv2/banner?catalog=1")
    Observable<TextBean> login();

    @POST("action/apiv/")
    @FormUrlEncoded
    Observable<TextBean> checkVersion(@Field("catalog") String version);
}
