package com.rc.framework.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Description:
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2016-04-25 15:49
 */
public class ToastUtil {

    public static void ShowShort(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void ShowLong(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
