package com.rc.framework.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.rc.framework.R;

/**
 * Description:
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2016-01-20 16:18
 */
public class LoadingDF extends RcDialogFragment{

    private ImageView ivLoading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_loading, container, false);
        ivLoading = (ImageView) view.findViewById(R.id.ivLoading);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.common_loading);
        ivLoading.startAnimation(animation);
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (ivLoading != null) {
            ivLoading.clearAnimation();
        }
    }
}
