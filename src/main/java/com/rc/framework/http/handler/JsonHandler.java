package com.rc.framework.http.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Description:
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2015-12-10 17:17
 */
public abstract class JsonHandler<T> extends TextHttpResponseHandler{

    private static final String TAG = "JsonHandler";

    private static Gson gson = new GsonBuilder().create();

    public void onFailure(int statusCode, Header[] headers, T response, Throwable throwable) {
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        try {
            T response = getGsonInstance().fromJson(responseString, TypeToken.get(getSuperclassTypeParameter(this.getClass())).getType());
            onFailure(statusCode, headers, response, throwable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString) {
        try {
            T response = getGsonInstance().fromJson(responseString, TypeToken.get(getSuperclassTypeParameter(this.getClass())).getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Gson getGsonInstance() {
        return gson;
    }

    private Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        } else {
            ParameterizedType parameterized = (ParameterizedType) superclass;
            return parameterized.getActualTypeArguments()[0];
        }
    }

    @Override
    public void onCancel() {
        super.onCancel();
    }

}
