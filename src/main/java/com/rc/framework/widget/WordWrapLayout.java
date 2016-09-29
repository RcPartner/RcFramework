package com.rc.framework.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.rc.framework.R;

/**
 * Description: 自动换行的横向线性布局
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2016-01-08 16:37
 */
public class WordWrapLayout extends ViewGroup {
    /**
     * 竖分割线
     */
    private int hDividerWidth = 0;

    /**
     * 横分割线
     */
    private int vDividerWidth = 0;

    private BaseAdapter mAdapter;

    private OnItemClickListener mListener;

    private DataChangeObserver mObserver;

    public WordWrapLayout(Context context) {
        super(context);
    }

    public WordWrapLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WordWrapLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WordWrapLayout);
        hDividerWidth = a.getDimensionPixelSize(
                R.styleable.WordWrapLayout_hDividerWidth, 0);
        vDividerWidth = a.getDimensionPixelSize(
                R.styleable.WordWrapLayout_vDividerWidth, 0);
        a.recycle();
    }

    @TargetApi(21)
    public WordWrapLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = r - l;
        int childL;
        int childT;
        int childR;
        int childB;
        int lUsed = 0;
        int tUsed = 0;

        int childCount = getChildCount();
        int lineHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            //单个子视图就超过父视图的宽度,换行
            if (child.getMeasuredWidth() > width) {
                childL = 0;
                childR = width;
                childT = tUsed;
                lUsed = 0;
                tUsed += lineHeight + vDividerWidth;
                lineHeight = 0;
            } else {
                //换行
                if (lUsed + child.getMeasuredWidth() > width) {
                    childL = 0;
                    childR = child.getMeasuredWidth();
                    childT = tUsed + lineHeight + vDividerWidth;
                    lUsed = child.getMeasuredWidth() + hDividerWidth;
                    tUsed += lineHeight + vDividerWidth;
                    lineHeight = child.getMeasuredHeight();
                } else {
                    childL = lUsed;
                    childR = lUsed + child.getMeasuredWidth();
                    childT = tUsed;
                    lUsed += child.getMeasuredWidth() + hDividerWidth;
                    lineHeight = Math.max(child.getMeasuredHeight(), lineHeight);
                }
            }
            childB = childT + child.getMeasuredHeight();
            child.layout(childL, childT, childR, childB);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int totalWidth = 0;
        int height = 0;
        int totalHeight = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams lp = child.getLayoutParams();
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, 0, lp.width);
            totalWidth += child.getMeasuredWidth() + hDividerWidth;
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, 0, lp.height);
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            if (widthMode == MeasureSpec.AT_MOST) {
                width = Math.min(widthSize, totalWidth - hDividerWidth);
            }
            if (widthMode == MeasureSpec.UNSPECIFIED) {
                width = totalWidth - hDividerWidth;
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            int lUsed = 0;
            int tUsed = 0;
            int lineHeight = 0;
            for (int i = 0; i < childCount; i++) {
                View child = getChildAt(i);
                //单个子视图就超过父视图的宽度,换行
                if (child.getMeasuredWidth() > width) {
                    lUsed = 0;
                    tUsed += lineHeight + vDividerWidth;
                    lineHeight = 0;
                } else {
                    //换行
                    if (lUsed + child.getMeasuredWidth() > width) {
                        lUsed = child.getMeasuredWidth() + hDividerWidth;
                        tUsed += lineHeight + vDividerWidth;
                        lineHeight = child.getMeasuredHeight();
                    } else {
                        lUsed += child.getMeasuredWidth() + hDividerWidth;
                        lineHeight = Math.max(child.getMeasuredHeight(), lineHeight);
                    }
                }
            }
            totalHeight = tUsed + lineHeight + vDividerWidth;
            if (heightMode == MeasureSpec.AT_MOST) {
                height = Math.min(heightSize, totalHeight - vDividerWidth);
            }
            if (heightMode == MeasureSpec.UNSPECIFIED) {
                height = totalHeight - vDividerWidth;
            }
        }
        setMeasuredDimension(width, height);
    }

    private void drawLayout() {
        if (mAdapter == null || mAdapter.getCount() == 0) {
            return;
        }

        this.removeAllViews();

        for (int i = 0; i < mAdapter.getCount(); i++) {
            final View view = mAdapter.getView(i, null, null);
            final int position = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(WordWrapLayout.this, view, position, (long) position);
                    }
                }
            });
            this.addView(view);
        }
    }

    public int getHDividerWidth() {
        return hDividerWidth;
    }

    public void setHDividerWidth(int hDividerWidth) {
        this.hDividerWidth = hDividerWidth;
    }

    public int getVDividerWidth() {
        return vDividerWidth;
    }

    public void setVDividerWidth(int vDividerWidth) {
        this.vDividerWidth = vDividerWidth;
    }

    public BaseAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(BaseAdapter adapter) {
        if (adapter != null) {
            mAdapter = adapter;
            if (mObserver == null) {
                mObserver = new DataChangeObserver();
                mAdapter.registerDataSetObserver(mObserver);
            }
            drawLayout();
        }
    }

    public OnItemClickListener getOnItemClickListener() {
        return mListener;
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    public interface OnItemClickListener {

        void onItemClick(View parent, View view, int position, long id);
    }

    class DataChangeObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            WordWrapLayout.this.drawLayout();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    }
}
