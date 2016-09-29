
package com.rc.framework.util;

import android.widget.TextView;

/**
 * Description: 文本工具类
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2016-01-19
 * 14:48
 */
public class TextUtil {

    /**
     * 计算文本的宽度
     *
     * @param textView 文本控件
     * @param text     文本
     * @return 文本的宽度（单位：px）
     */
    public static float textWidth(TextView textView, String text) {
        if (textView == null || text == null) {
            return -1;
        }
        return textView.getPaint().measureText(text, 0, text.length());
        // Rect bounds = new Rect();
        // textView.getPaint().getTextBounds(text, 0, text.length(), bounds);
        // return bounds.width();
    }
}
