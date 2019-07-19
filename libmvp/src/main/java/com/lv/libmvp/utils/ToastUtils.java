package com.lv.libmvp.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 作者：create by albert on 2018/1/11 15:18 PM
 * 邮箱：lvhzongdi@iclou.com
 */
public class ToastUtils {
    private Toast mToast;
    private static ToastUtils mToastUtils;

    private ToastUtils(Context context) {
        mToast = Toast.makeText(context, null, Toast.LENGTH_SHORT);
    }

    public static synchronized ToastUtils getInstanc(Context context) {
        if (null == mToastUtils) {
            mToastUtils = new ToastUtils(context);
        }
        return mToastUtils;
    }

    /**
     * 显示toast
     *
     * @param toastMsg
     */
    public void showToast(int toastMsg) {
        mToast.setText(toastMsg);
        mToast.show();
    }

    /**
     * 显示toast
     *
     * @param toastMsg
     */
    public void showToast(String toastMsg) {
        mToast.setText(toastMsg);
        mToast.show();
    }

    /**
     * 取消toast，在activity的destory方法中调用
     */
    public void destory() {
        if (null != mToast) {
            mToast.cancel();
            mToast = null;
        }
        mToastUtils = null;
    }
}

