package com.rc.framework.util;

import android.app.Activity;
import android.view.WindowManager;

/**
 * Description:
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2016-06-14 20:50
 */
public class SoftInputUtil {

    /**
     * 当前主窗口总是调整屏幕的大小以便留出软键盘的空间
     *
     * @param activity
     */
    public static void enableSoftInputAdjustResize(Activity activity) {
        activity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    /**
     * 当前窗口的内容将自动移动以便当前焦点从不被键盘覆盖和用户能总是看到输入内容的部分
     *
     * @param activity
     */
    public static void enableSoftInputAdjustPan(Activity activity) {
        activity.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }
}
