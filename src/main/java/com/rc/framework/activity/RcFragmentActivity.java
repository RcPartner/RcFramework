package com.rc.framework.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.AnimRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Description:
 * 功能点如下：
 * 1）启动一个fragment并加入回退栈
 * 2）回退
 * 3）回退到指定的fragment
 * 4）设置fragment转场动画
 * Author: Caizemingg
 * Email:caizemingg@163.com
 * Date: 2016-01-20 16:28
 */
public class RcFragmentActivity extends FragmentActivity {

    public static final int NO_USE_TRANSACTION_ANIM = 0;

    private int enterAnim = NO_USE_TRANSACTION_ANIM;
    private int exitAnim = NO_USE_TRANSACTION_ANIM;
    private int popEnterAnim = NO_USE_TRANSACTION_ANIM;
    private int popExitAnim = NO_USE_TRANSACTION_ANIM;

    protected FragmentManager fm;
    protected FragmentTransaction ft;

    private ArrayList<Fragment> mSingletonFragment = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        fm = getSupportFragmentManager();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
    }

    private Fragment isHasFragment(Class<?> fragment) {
        for (Fragment f : mSingletonFragment) {
            if (f.getClass().equals(fragment)) {
                return f;
            }
        }
        return null;
    }

    /**
     * 启动指定的fragment，fragment在回退栈的tag规则为：
     * fragment.getSimpleName() + 序号，序号从1开始
     *
     * @param fragment
     */
    public Fragment startFragment(Class<?> fragment) {
        Fragment fTemp = null;
        try {
            fTemp = (Fragment) fragment.newInstance();
            ft = fm.beginTransaction();
            ft.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim);
            String tag = fragment.getSimpleName();
            int fragmentTagNum;
            FragmentManager.BackStackEntry entry = getLastFragmentInBackStack(fragment);
            String name;
            if (entry == null) {
                fragmentTagNum = 1;
            } else {
                name = entry.getName();
                String num = name.substring(tag.length(), name.length());
                //转换不成功直接抛异常，使用改方法必须保证 BackStackEntry.getName()的规则为
                // fragment.getSimpleName() + 序号，序号从1开始
                fragmentTagNum = Integer.parseInt(num);
                fragmentTagNum++;
            }
            tag += fragmentTagNum;
            ft.replace(android.R.id.content, fTemp, tag);
            ft.addToBackStack(tag);
            ft.commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return fTemp;
    }

    public void startSingleTaskFragment(Class<?> fragment) {
        Fragment f = isHasFragment(fragment);
        if (f != null) {
            //弹出所有该fragment
            FragmentManager.BackStackEntry entry = getFirstFragmentInBackStack(fragment);
            if (entry != null) {
                fm.popBackStack(entry.getName(), 1);
            }
        }
        f = startFragment(fragment);
        if (f != null) {
            mSingletonFragment.add(f);
        }
    }


    /**
     * 回退到指定的fragment，如果回退栈中包含多个该frament的记录，回退到最靠近栈顶的那个
     *
     * @param fragment 要回退到frament
     */
    public void popFragment(Class<?> fragment) {
        FragmentManager.BackStackEntry entry = getLastFragmentInBackStack(fragment);
        if (entry != null) {
            fm.popBackStack(entry.getName(), 0);
        }
    }

    public boolean isFragmentInBackStack(Class<?> fragment) {
        if (getFirstFragmentInBackStack(fragment) != null) {
            return true;
        }
        return false;
    }

    public FragmentManager.BackStackEntry getFirstFragmentInBackStack(Class<?> fragment) {
        for (int i = 0; i < fm.getBackStackEntryCount(); i++) {
            String name = fm.getBackStackEntryAt(i).getName();
            if (TextUtils.isEmpty(name)) {
                continue;
            }
            if (name.contains(fragment.getSimpleName())) {
                return fm.getBackStackEntryAt(i);
            }
        }
        return null;
    }

    public FragmentManager.BackStackEntry getLastFragmentInBackStack(Class<?> fragment) {
        int backStackEntryCount = fm.getBackStackEntryCount();
        for (int i = backStackEntryCount - 1; i >= 0; i--) {
            String name = fm.getBackStackEntryAt(i).getName();
            if (TextUtils.isEmpty(name)) {
                continue;
            }
            if (name.contains(fragment.getSimpleName())) {
                return fm.getBackStackEntryAt(i);
            }
        }
        return null;
    }

    /**
     * 回退
     */
    public void popFragment() {
        fm.popBackStack();
    }

    public void setTransactionAnimations(@AnimRes int enter, @AnimRes int exit, @AnimRes int popEnter, @AnimRes int popExit) {
        this.enterAnim = enter;
        this.exitAnim = exit;
        this.popEnterAnim = popEnter;
        this.popExitAnim = popExit;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
