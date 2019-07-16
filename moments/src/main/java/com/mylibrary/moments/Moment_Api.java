package com.mylibrary.moments;

import com.mylibrary.moments.bean.MomentItemBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Moment_Api {

    @GET("action/apiv2/banner?catalog=1")
    Observable<MomentItemBean> login();

    @POST("action/apiv/")
    @FormUrlEncoded
    Observable<MomentItemBean> checkVersion(@Field("catalog") String version);
}
