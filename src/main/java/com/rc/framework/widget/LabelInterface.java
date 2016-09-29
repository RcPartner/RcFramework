package com.rc.framework.widget;

/**
 * Description:带标签的文本（如姓名：xxxx）
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2016-01-19 10:18
 */
public interface LabelInterface {

    /**
     * 设置标题的文本
     *
     * @param text 标题的文本
     * @return {@link LabelInterface}
     */
    LabelInterface setTitleText(CharSequence text);

    /**
     * 获取标题的文本
     *
     * @return 标题的文本
     */
    CharSequence getTitleText();

    /**
     * 设置标题的文本大小 （单位：px）
     *
     * @param size 标题文本大小
     * @return {@link LabelInterface}
     */
    LabelInterface setTitleTextSize(int size);

    /**
     * 获取标题的文本大小 （单位：px）
     *
     * @return 标题文本大小
     */
    int getTitleTextSize();

    /**
     * 设置标题的文本颜色
     *
     * @param color 标题的文本颜色
     * @return {@link LabelInterface}
     */
    LabelInterface setTitleTextColor(int color);

    /**
     * 获取标题的文本颜色
     *
     * @return 标题的文本颜色
     */
    int getTitleTextColor();

    /**
     * 设置内容的文本
     *
     * @param text 内容的文本
     * @return {@link LabelInterface}
     */
    LabelInterface setContentText(CharSequence text);

    /**
     * 获取内容的文本
     *
     * @return 内容的文本
     */
    CharSequence getContentText();

    /**
     * 设置内容的文本大小 （单位：px）
     *
     * @param size 内容的文本大小
     * @return {@link LabelInterface}
     */
    LabelInterface setContentTextSize(int size);

    /**
     * 获取内容的文本大小 （单位：px）
     *
     * @return 内容的文本大小
     */
    int getContentTextSize();

    /**
     * 设置内容的文本颜色
     *
     * @param color 内容的文本颜色\
     * @return {@link LabelInterface}
     */
    LabelInterface setContentTextColor(int color);

    /**
     * 获取内容的文本颜色
     *
     * @return 内容的文本颜色
     */
    int getContentTextColor();

    /**
     * 设置标题与内容的间隙 （单位：px）
     *
     * @param padding 标题与内容的间隙
     * @return {@link LabelInterface}
     */
    LabelInterface setTitlePadding(float padding);

    /**
     * 获取标题与内容的间隙 （单位：px）
     *
     * @return 标题与内容的间隙
     */
    float getTitlePadding();

    /**
     * 是否使用冒号
     *
     * @param useColon true:使用
     * @return {@link LabelInterface}
     */
    LabelInterface setUseColon(boolean useColon);

    /**
     * 是否使用冒号
     *
     * @return true:使用
     */
    boolean isUseColon();

    void updateText();
}
