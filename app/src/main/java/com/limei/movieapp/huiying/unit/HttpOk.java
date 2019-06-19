package com.limei.movieapp.huiying.unit;

import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/8/27.
 */

public class HttpOk {
    public static void GetData(String path, RequestBody requestBody,Callback callback) {
        Log.d("kananan", "onFai1111111111111111lure: ");

        Request request = new Request.Builder()
                .url(path)
                .post(requestBody)
                .build();


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5000, TimeUnit.SECONDS);
        builder.sslSocketFactory(Unit.createSSLSocketFactory());
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        builder.build().newCall(request).enqueue(callback);
    }
}
