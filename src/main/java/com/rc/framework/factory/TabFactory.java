package com.rc.framework.factory;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.StateListDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Description:
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2016-04-29 17:51
 */
public class TabFactory {

    public static View createTab(Context context, String text, float textSize, int[] icon, int[] textColor) {
        TextView tab = new TextView(context);
        Resources resources = context.getResources();
        if (icon.length == 1) {
            tab.setCompoundDrawablesWithIntrinsicBounds(null, resources.getDrawable(icon[0]), null, null);
        } else if (icon.length == 2) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{android.R.attr.state_selected}, resources.getDrawable(icon[1]));
            stateListDrawable.addState(new int[]{}, resources.getDrawable(icon[0]));
            tab.setCompoundDrawablesWithIntrinsicBounds(null, stateListDrawable, null, null);
        }

        if (textColor.length == 1) {
            tab.setTextColor(resources.getColor(textColor[0]));
        } else if (textColor.length == 2) {
            ColorStateList colorStateList = new ColorStateList(new int[][]{new int[]{android.R.attr.state_selected}, new int[]{}}
                    , new int[]{resources.getColor(textColor[1]), resources.getColor(textColor[0])});
            tab.setTextColor(colorStateList);
        }
        tab.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        tab.setGravity(Gravity.CENTER);
        tab.setText(text);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
        layoutParams.gravity = Gravity.CENTER;
        tab.setLayoutParams(layoutParams);
        return tab;
    }

    public static View[] createTabs(Context context, String[] texts, float textSize, int[][] icons, int[] textColor) {
        View[] views = new View[texts.length];
        for (int i = 0; i < texts.length; i++) {
            views[i] = createTab(context, texts[i], textSize, icons[i], textColor);
        }
        return views;
    }


}
