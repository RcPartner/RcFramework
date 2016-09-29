package com.rc.framework.http;

import android.content.Context;

/**
 * Description:
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2016-03-21 10:41
 */
public interface RcHttpClientInterface {

    void get(Context context, String url, RcRequestParam param, RcHttpResponse response);

    void post(Context context, String url, RcRequestParam param, RcHttpResponse response);

}
