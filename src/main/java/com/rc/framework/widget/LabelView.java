package com.rc.framework.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.TextView;

import com.rc.framework.R;
import com.rc.framework.util.TextUtil;

/**
 * Description: 标签控件（带标题和内容）
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2016-01-18 16:30
 */
public class LabelView extends TextView implements LabelInterface {

    private static final int UNSET = -1;

    private static final String COLON = ":";

    private CharSequence titleText;
    private int titleTextSize = UNSET;
    private int titleTextColor;

    private CharSequence contentText;
    private int contentTextSize = UNSET;
    private int contentTextColor;

    private float titlePadding = UNSET;

    private boolean useColon = false;

    public LabelView(Context context) {
        super(context);
    }

    public LabelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs);
        updateText();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LabelView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttr(context, attrs);
        updateText();
    }

    private void initAttr(Context context, AttributeSet attrs) {
//        if (isInEditMode()) {
//            return;
//        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LabelView);
        this.titleText = typedArray.getText(R.styleable.LabelView_titleText);
        this.titleTextSize = typedArray.getDimensionPixelSize(R.styleable.LabelView_titleTextSize, UNSET);
        this.titleTextColor = typedArray.getColor(R.styleable.LabelView_titleTextColor, UNSET);
        this.titlePadding = typedArray.getDimensionPixelSize(R.styleable.LabelView_titlePadding, UNSET);
        this.contentText = typedArray.getText(R.styleable.LabelView_contentText);
        this.contentTextSize = typedArray.getDimensionPixelSize(R.styleable.LabelView_contentTextSize, UNSET);
        this.contentTextColor = typedArray.getColor(R.styleable.LabelView_contentTextColor, UNSET);
        this.useColon = typedArray.getBoolean(R.styleable.LabelView_userColon, false);
    }

    @Override
    public LabelInterface setTitleText(CharSequence text) {
        this.titleText = text;
        return this;
    }

    @Override
    public CharSequence getTitleText() {
        return this.titleText;
    }

    @Override
    public LabelInterface setTitleTextSize(int size) {
        this.titleTextSize = size;
        return this;
    }

    @Override
    public int getTitleTextSize() {
        return this.titleTextSize;
    }

    @Override
    public LabelInterface setTitleTextColor(int color) {
        this.titleTextColor = color;
        return this;
    }

    @Override
    public int getTitleTextColor() {
        return this.titleTextColor;
    }

    @Override
    public LabelInterface setContentText(CharSequence text) {
        this.contentText = text;
        return this;
    }

    @Override
    public CharSequence getContentText() {
        return this.contentText;
    }

    @Override
    public LabelInterface setContentTextSize(int size) {
        this.contentTextSize = size;
        return this;
    }

    @Override
    public int getContentTextSize() {
        return this.contentTextSize;
    }

    @Override
    public LabelInterface setContentTextColor(int color) {
        this.contentTextColor = color;
        return this;
    }

    @Override
    public int getContentTextColor() {
        return this.contentTextColor;
    }

    @Override
    public LabelInterface setTitlePadding(float padding) {
        this.titlePadding = padding;
        return this;
    }

    @Override
    public float getTitlePadding() {
        return this.titlePadding;
    }

    @Override
    public boolean isUseColon() {
        return useColon;
    }

    @Override
    public LabelInterface setUseColon(boolean useColon) {
        this.useColon = useColon;
        return this;
    }

    @Override
    public void updateText() {
        String str = "";
        SpannableString spanStr;
        int startIndex = 0;
        int endIndex = 0;
        //空格的宽度
        float spaceWidth;
        int spaceCount = 0;

        if (!TextUtils.isEmpty(titleText)) {
            str += titleText;
            endIndex += titleText.length();
        }

        if (useColon) {
            str += COLON;
            endIndex++;
        }

        if (titlePadding != UNSET) {
            spaceWidth = TextUtil.textWidth(this, " ");
            spaceCount = Math.round(titlePadding / spaceWidth);
            for (int i = 0; i < spaceCount; i++) {
                str += " ";
            }
            endIndex += spaceCount;
        }

        if (!TextUtils.isEmpty(contentText)) {
            str += contentText;
        }

        if (TextUtils.isEmpty(str)) {
            return;
        }

        spanStr = new SpannableString(str);
        if (!TextUtils.isEmpty(titleText)) {
            if (titleTextSize != UNSET) {
                spanStr.setSpan(new AbsoluteSizeSpan(titleTextSize), startIndex, endIndex, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            }
            if (titleTextColor != UNSET) {
                spanStr.setSpan(new ForegroundColorSpan(titleTextColor), startIndex, endIndex, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }
        startIndex = endIndex;
        if (!TextUtils.isEmpty(contentText)) {
            endIndex += contentText.length();
            if (contentTextSize != UNSET) {
                spanStr.setSpan(new AbsoluteSizeSpan(contentTextSize), startIndex, endIndex, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            }
            if (contentTextColor != UNSET) {
                spanStr.setSpan(new ForegroundColorSpan(contentTextColor), startIndex, endIndex, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }
        setText(spanStr);
    }
}
