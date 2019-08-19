package com.example.cleanarchtest.net;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ApiConnection {
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_VALUE_JSON = "application/json; charset=utf-8";
    private URL url;
    private String response;
    private  ApiConnection(String url) throws MalformedURLException {
        this.url=new URL(url);
    }
    private void connectToApi() throws IOException {
        OkHttpClient okHttpClient = this.connectTimeOut();
        final Request request = new Request.Builder()
                .url(this.url)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_VALUE_JSON)
                .get()
                .build();
        this.response = okHttpClient.newCall(request).execute().body().string();
    }
    private  OkHttpClient connectTimeOut(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15000, TimeUnit.SECONDS)
                .readTimeout(10000, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }
    static ApiConnection createGET(String url) throws MalformedURLException {
        return new ApiConnection(url);
    }
    @Nullable
    String requestSyncCall() throws IOException {
        connectToApi();
        return response;
    }


}
