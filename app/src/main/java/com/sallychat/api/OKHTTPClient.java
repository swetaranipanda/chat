package com.sallychat.api;

import android.os.Environment;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * Created by Sweta .
 */
public class OKHTTPClient {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient okHttpClient;

    private OKHTTPClient() {
    }

    public static OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (OKHTTPClient.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient();
                    okHttpClient.setConnectTimeout(120, TimeUnit.SECONDS);
                    okHttpClient.setReadTimeout(120, TimeUnit.SECONDS);
                    // createCacheForOkHTTP();
                }
            }
        }
        return okHttpClient;
    }

    private static void createCacheForOkHTTP() {
        Cache cache = null;
        cache = new Cache(getDirectory(), 1024 * 1024 * 10);
        okHttpClient.setCache(cache);
    }

    public static File getDirectory() {
        final File root = new File(Environment.getExternalStorageDirectory() + File.separator + "UCC" + File.separator);
        root.mkdirs();
        final String fname = "gamoly.cache";
        final File sdImageMainDirectory = new File(root, fname);
        return sdImageMainDirectory;
    }

    public static Call get(String url, Callback callback) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = getOkHttpClient().newCall(request);
        call.enqueue(callback);
        return call;
    }

/*    public static Call getWithHeader(String url, Callback callback, Context context) throws IOException {
        String headerValue = "Bearer " + BaseApplication.getString(context, AppConstants.ACCESS_TOKEN);
//        String headerValue ="Bearer 6511de518269810bc0c8e72fd21db5e7f88f79b3f355b910a5010cbbbc626735";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Authorization", headerValue)
                .build();
        Call call = getOkHttpClient().newCall(request);
        call.enqueue(callback);
        return call;
    }
//*/

    //
/*
    public static Call post(String url, String json, Callback callback) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Call call = null;
        String headerValue = "Bearer " + BaseApplication.getString(BaseApplication.getAppContext(), AppConstants.ACCESS_TOKEN);
//        String headerValue ="Bearer 6511de518269810bc0c8e72fd21db5e7f88f79b3f355b910a5010cbbbc626735";

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader("Authorization", headerValue)
                    .build();
            call = getOkHttpClient().newCall(request);
            call.enqueue(callback);
        } catch (IllegalArgumentException arg) {

        }
        return call;
    }
*/


    public static Call postWithoutHeader(String url, String json, Callback callback) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Call call = null;

        try {
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            call = getOkHttpClient().newCall(request);
            call.enqueue(callback);
        } catch (IllegalArgumentException arg) {

        }
        return call;
    }

    public static Call put(String url, String json, Callback callback) throws IOException {
//        String headerValue = "Bearer " + BaseApplication.getString(BaseApplication.getAppContext(), AppConstants.JWT_TOKEN);

        RequestBody body = RequestBody.create(JSON, json);
        Call call = null;
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .put(body)
//                    .addHeader("Authorization", headerValue)
                    .build();
            call = getOkHttpClient().newCall(request);
            call.enqueue(callback);
        } catch (IllegalArgumentException arg) {

        }
        return call;
    }

    public static Call putWIthHeader(String url, String json, Callback callback) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
//        String headerValue = "Bearer " + BaseApplication.getString(BaseApplication.getAppContext(), AppConstants.JWT_TOKEN);
        Call call = null;
        try {
            Request request = new Request.Builder()
                    .url(url)
//                    .addHeader("Authorization", headerValue)
                    .put(body)
                    .build();
            call = getOkHttpClient().newCall(request);
            call.enqueue(callback);
        } catch (IllegalArgumentException arg) {

        }
        return call;
    }

    public static Call delete(String url, String json, Callback callback) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
//        String headerValue ="Bearer 6511de518269810bc0c8e72fd21db5e7f88f79b3f355b910a5010cbbbc626735";
//        String headerValue = "Bearer " + BaseApplication.getString(BaseApplication.getAppContext(), AppConstants.JWT_TOKEN);
        Call call = null;
        try {
            Request request = new Request.Builder()
                    .url(url)
//                    .addHeader("Authorization", headerValue)
                    .delete(body)
                    .build();
            call = getOkHttpClient().newCall(request);
            call.enqueue(callback);
        } catch (IllegalArgumentException arg) {

        }
        return call;
    }
}
