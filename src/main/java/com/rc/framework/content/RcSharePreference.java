package com.rc.framework.content;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Description:
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2016-01-20 11:42
 */
public class RcSharePreference {

    protected String spName;

    protected Context context;

    protected SharedPreferences sp;

    protected SharedPreferences.Editor editor;

    public RcSharePreference(Context context, String spName) {
        this.spName = spName;
        this.context = context;
        sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public final boolean commit() {
        return editor.commit();
    }
}
