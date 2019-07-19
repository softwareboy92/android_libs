package com.lv.libmvp.weight;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lv.libmvp.R;


/**
 * 作者：created by albert on 2018/9/6 13:58
 * 邮箱：lvzhongdi@icloud.com
 * 自定义个Dialog
 *
 * @param
 **/
public class DialogLoadding {

    private Context context;
    private LayoutInflater inflater;
    private String Message = "Loading...";
    private TextView tipText;
    private Dialog loadingdialog;

    public DialogLoadding(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        init();
    }


    private void init() {
        View view = inflater.inflate(R.layout.dialog_loading, null);
        LinearLayout layout = view
                .findViewById(R.id.dialog_loading_view);// 加载布局
        tipText = view.findViewById(R.id.tipTextView);

        tipText.setText(Message);

        loadingdialog = new Dialog(context, R.style.MyDialogStyle);
        loadingdialog.setCancelable(false);//是否可以按返回按钮
        loadingdialog.setCanceledOnTouchOutside(false);//屏幕之外区域是否可以点击消除
        loadingdialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        /**
         * 将显示的Dilaog封装在方法里面
         */
        Window window = loadingdialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.PopWindowAnimStyle);

    }

    /**
     * 设置显示文字
     *
     * @param message
     * @return
     */
    public DialogLoadding setMessage(String message) {
        tipText.setText(message);
        return this;
    }


    public void closeDialog() {
        if (loadingdialog != null && loadingdialog.isShowing()) {
            loadingdialog.dismiss();
        }
    }

    public void showDialog() {
        if (context != null) {
            loadingdialog.show();
        }
    }
}
