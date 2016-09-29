package com.rc.framework.listener;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Description: 监听双击事件
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2016-01-25 11:47
 */
public abstract class onDoubleClickListener implements View.OnClickListener {

    private static final int CLICK = 1;

    /**
     * 是否已经点击过一次
     */
    private boolean isFirstClicked = false;

    private static final int DOUBLE_CLICK_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();

    private DoubleClickHandler mHandler;

    public abstract void doubleClick(View view);

    @Override
    public void onClick(View v) {
        if (!isFirstClicked) {
            isFirstClicked = true;
            mHandler.sendEmptyMessageDelayed(CLICK, DOUBLE_CLICK_TIMEOUT);
        }
        else {
            boolean hadTapMessage = mHandler.hasMessages(CLICK);
            if (hadTapMessage) {
                mHandler.removeMessages(CLICK);
            }
            doubleClick(v);
            isFirstClicked = false;
        }
    }

    private class DoubleClickHandler extends Handler {
        DoubleClickHandler() {
            super();
        }

        DoubleClickHandler(Handler handler) {
            super(handler.getLooper());
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CLICK:
                    isFirstClicked = false;
                    break;

            }
        }
    }
}
