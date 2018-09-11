/*
 *
 *  * Copyright (c) 2018 Follett. All rights reserved.
 *
 */

package com.follett.fsc.mobile.circdesk.data.remote.api;

import android.text.TextUtils;
import android.util.Log;

import com.follett.fsc.mobile.circdesk.utils.FollettLog;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FollettAPIManager {

    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient;

    public static Retrofit getClient(String baseUrl) {

        if (okHttpClient == null) {
            initOkHttp();
        }

        if (retrofit == null && !TextUtils.isEmpty(baseUrl)) {
             retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    /*.addConverterFactory(new JsonAndXmlConverters.QualifiedTypeConverterFactory(
                            GsonConverterFactory.create(),
                            SimpleXmlConverterFactory.create()))*/
                    .build();
        }
        Log.d("TAG", "Response = " + retrofit);

        return retrofit;
    }

    public static Retrofit getGeoClient(String baseUrl) {

        if (okHttpClient == null) {
            initOkHttp();
        }

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    private static void initOkHttp() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().connectTimeout(70, TimeUnit.SECONDS)
                .readTimeout(70, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(logging);

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .build();
                FollettLog.d("Response>>>>>>>>>>>>>>>>>>>>>>>", chain.proceed(request).body().string());
                return chain.proceed(request);
            }
        });
        okHttpClient = httpClient.build();

    }


}
