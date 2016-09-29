package com.rc.framework.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import com.rc.framework.util.ExceptionUtil;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class RcAdapter<T> extends BaseAdapter {

    protected ArrayList<T> mList = new ArrayList<>();

    protected Context context;

//    protected DisplayImageOptions options;

    public RcAdapter(Context context) {
        ExceptionUtil.nullException(context);
        this.context = context;
//        this.options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true)
//                .build();
    }

    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mList != null) {
            return mList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(T data) {
        mList.add(data);
    }

    public void addArray(T[] list) {
        ExceptionUtil.nullException(list);
        mList.addAll(Arrays.asList(list));
    }

    public void setArray(T[] list) {
        mList = (ArrayList<T>) Arrays.asList(list);
    }

    public void clear() {
        mList.clear();
    }
}
