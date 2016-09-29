package com.rc.framework.dialog;

import android.app.Dialog;
import android.content.Context;

/**
 * Description:
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2016-01-20 12:20
 */
public class DialogFactory {

    /**
     * 加载中的对话框
     * @param context
     * @param theme
     * @param mes
     * @return
     */
    public static Dialog createLoadingDialog(Context context, int theme,String mes)
    {
        LoadingDialog dialog = new LoadingDialog(context, theme, mes);
        return dialog;
    }
}
