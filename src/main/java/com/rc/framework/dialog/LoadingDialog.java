package com.rc.framework.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;

import com.rc.framework.R;

/**
 * Description:
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2016-01-20 11:51
 */
public class LoadingDialog extends ProgressDialog {

    public LoadingDialog(Context context) {
        this(context, 0, null);
    }

    public LoadingDialog(Context context, String mes) {
        this(context, 0, mes);
    }

    public LoadingDialog(Context context, int theme, String mes) {
        super(context, theme);
        setProgressStyle(ProgressDialog.STYLE_SPINNER);
        setCanceledOnTouchOutside(false);
        if (!TextUtils.isEmpty(mes)) {
            setMessage(mes);
        } else {
            setMessage(context.getString(R.string.loading_text));
        }
    }
}
