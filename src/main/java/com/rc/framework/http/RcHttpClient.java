package com.rc.framework.http;

import android.content.Context;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Description:
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2016-03-21 16:06
 */
public class RcHttpClient implements RcHttpClientInterface{

    //创建okHttpClient对象
    private static OkHttpClient mOkHttpClient = new OkHttpClient();


    public static RcHttpClient INSTANCE = new RcHttpClient();

    private RcHttpClient(){
    }

    @Override
    public void get(Context context, String url, RcRequestParam param, final RcHttpResponse response) {
        Request request = new Request.Builder().url(url).build();
        Call call = mOkHttpClient.newCall(request);
        call.request();
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response1) throws IOException {
                response.onSuccess(response1.code(), response1.body().bytes());
            }
        });

    }

    @Override
    public void post(Context context, String url, RcRequestParam param, RcHttpResponse response) {
    }


//    public  RcHttpClientInterface get(Context context, String url, RcHttpResponse response) {
//        return get(context, url, null, response);
//    }
//
//    public  RcHttpClientInterface get(Context context, String url, RcRequestParam param, final RcHttpResponse response) {
//
//    }
//
//    public  RcHttpClientInterface post(Context context, String url, RcHttpResponse response) {
//        return post(context, url, null, response);
//    }
//
//    public  RcHttpClientInterface post(Context context, String url, RcRequestParam param, RcHttpResponse response) {
//        return null;
//    }
}
