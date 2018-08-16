/*
 * Copyright (c) 2018 Follett. All rights reserved.
 */

package com.follett.fsc.mobile.circdesk.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    
    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient;
    
    public static Retrofit getClient(String baseUrl) {
    
        if (okHttpClient == null) { initOkHttp(); }
        
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        
        return retrofit;
    }
    
    public static Retrofit getGeoClient(String baseUrl) {
    
        if (okHttpClient == null) { initOkHttp(); }
        
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
        
        /*TODO while Implementing Header concepts do here*/
        /*httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Authorization", "Bearer " + Config.API_KEY)
                        .addHeader("Accept", "application/json");

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });*/
        
        okHttpClient = httpClient.build();
    }
}
