package com.rc.framework.widget;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;

/**
 * Description:
 * {@link TimePickerDialog}选择完时间后点击完成,
 * 会回调两次{@linkplain android.app.TimePickerDialog.OnTimeSetListener#onTimeSet(TimePicker, int, int)};
 * 点击空白处会回调一次；
 * {@link RcTimePickerDialog}通过复写{@link TimePickerDialog#onStop()}保证都只回调一次
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2016-04-25 16:04
 */
public class RcTimePickerDialog extends TimePickerDialog{
    public RcTimePickerDialog(Context context, OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView) {
        super(context, callBack, hourOfDay, minute, is24HourView);
    }

    public RcTimePickerDialog(Context context, int theme, OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView) {
        super(context, theme, callBack, hourOfDay, minute, is24HourView);
    }

    @Override
    protected void onStop() {
        //super.onStop();
    }
}
