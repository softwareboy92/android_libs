package com.lv.libdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Dialog 2016年7月30日
 */
public class MyDialog {

    private final Display display;
    private Dialog mDialog;
    private Context mContext;
    private TextView button;
    private TextView content;
    private TextView title;
    private Window dialogWindow;

    public MyDialog(Context context) {
        this.mContext = context;
        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        mDialog = new Dialog(context, R.style.Custom_Dialog_Style);
        dialogWindow = mDialog.getWindow();

    }


    public MyDialog builder() {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.layout_dialog, null, false);
        LinearLayout mLinearDialog = view.findViewById(R.id.linear_dialog);
        button = view.findViewById(R.id.button);
        content = view.findViewById(R.id.content);
        title = view.findViewById(R.id.title);
        mDialog.setContentView(view);
        mLinearDialog.setLayoutParams(new FrameLayout.LayoutParams(((int) (display.getWidth() * 0.80)), LinearLayout.LayoutParams.WRAP_CONTENT));
        dialogWindow.setGravity(Gravity.CENTER);
        return this;
    }

    public MyDialog setContextText(String contextText){
        content.setText(contextText);
        return this;
    }

    public MyDialog setTitleText(String titleText){
        title.setText(titleText);
        return this;
    }


    public MyDialog setButtonText(String buttonText,final View.OnClickListener listener){
        button.setText(buttonText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                mDialog.dismiss();
            }
        });
        return this;
    }

    public void show(){
        mDialog.show();
    }

}