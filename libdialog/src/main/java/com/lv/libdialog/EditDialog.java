package com.lv.libdialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class EditDialog {
    private Context context;
    private Dialog dialog;
    private LinearLayout mLinear_layout;
    private TextView mTvTitle;
    private EditText mEdtContent;
    private Button btn_neg;
    private Button btn_pos;
    private ImageView img_line;
    private Display display;
    private TextView mTvOK;
    private LinearLayout mLinearBottom;

    public EditDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public EditDialog builder() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_alert_edit, null);
        mLinear_layout = view.findViewById(R.id.linear_alert_dialog_bg);
        mTvTitle = view.findViewById(R.id.tv_alert_dialog_title);
        mEdtContent = view.findViewById(R.id.edt_alert_dialog_content);
        btn_neg = view.findViewById(R.id.btn_alert_dialog_cancel);
        btn_pos = view.findViewById(R.id.btn_alert_dialog_sure);
        img_line = view.findViewById(R.id.img_alert_dialog_line);
        mTvOK = view.findViewById(R.id.tv_ok);
        mLinearBottom = view.findViewById(R.id.two_ll);
        dialog = new Dialog(context, R.style.ActionGeneralDialog);
        dialog.setContentView(view);
        mLinear_layout.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * 0.85), LinearLayout.LayoutParams.WRAP_CONTENT));
        return this;
    }

    /**
     * 设置背景颜色
     *
     * @param id
     * @return
     */
    public EditDialog setBackgroundResource(int id) {
        mLinear_layout.setBackgroundResource(id);
        return this;
    }

    /**
     * 设置背景颜色
     * @param id
     * @return
     */
    public EditDialog setBackgroundColor(int id) {
        mLinear_layout.setBackgroundColor(id);
        return this;
    }



    /**
     * 设置输入的内容
     *
     * @param inputType
     * @return
     */
    public EditDialog setInputType(int inputType) {
        mEdtContent.setInputType(inputType);
        return this;
    }

    /**
     * @param title
     * @return
     */
    public EditDialog setTitle(String title) {
        if ("".equals(title)) {
            mTvTitle.setText("标题");
        } else {
            mTvTitle.setText(title);
        }
        return this;
    }


    /**
     * setting dialog center button
     *
     * @param text
     * @param listener
     * @return
     */
    public EditDialog setCenterButton(String text, final View.OnClickListener listener) {
        if ("".equals(text)) {
            mTvOK.setText("确认");
        } else {
            mTvOK.setText(text);
        }
        mLinearBottom.setVisibility(View.GONE);
        mTvOK.setVisibility(View.VISIBLE);

        mTvOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }


    /**
     * @param title
     * @param color
     * @param textSize
     * @return
     */
    public EditDialog setTitle(String title, int color, float textSize) {
        if ("".equals(title)) {
            mTvTitle.setText("标题");
        } else {
            mTvTitle.setText(title);
        }
        mTvTitle.setTextColor(color);
        mTvTitle.setTextSize(textSize);
        return this;
    }

    /**
     * @return
     */
    public String getMsg() {
        return mEdtContent.getText().toString();
    }

    /**
     * 设置提示文字
     *
     * @param str
     * @return
     */
    public EditDialog setHitText(String str) {
        mEdtContent.setHint(str);
        return this;
    }

    /**
     * 设置提示文字和颜色
     *
     * @param str
     * @return
     */
    public EditDialog setHitText(String str, int color) {
        mEdtContent.setHintTextColor(color);
        mEdtContent.setHint(str);
        return this;
    }


    /**
     * 设置提示文字和颜色
     *
     * @param str
     * @return
     */
    public EditDialog setHitText(String str, String color) {
        mEdtContent.setHintTextColor(Color.parseColor(color));
        mEdtContent.setHint(str);
        return this;
    }

    /**
     * @param cancelable
     * @return
     */
    public EditDialog setCancelable(boolean cancelable) {
        dialog.setCancelable(cancelable);
        return this;
    }

    /**
     * @param text
     * @param listener
     * @return
     */
    public EditDialog setPositiveButton(String text, final View.OnClickListener listener) {
        if ("".equals(text)) {
            btn_pos.setText("确定");
        } else {
            btn_pos.setText(text);
        }
        btn_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    /**
     * @param text
     * @param color
     * @param textSize
     * @param listener
     * @return
     */
    public EditDialog setPositiveButton(String text, int color, float textSize, final View.OnClickListener listener) {
        if ("".equals(text)) {
            btn_pos.setText("确定");
        } else {
            btn_pos.setText(text);
        }
        btn_pos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        btn_pos.setTextColor(color);
        btn_pos.setTextSize(textSize);
        return this;
    }

    /**
     * @param text
     * @param listener
     * @return
     */
    public EditDialog setNegativeButton(String text, final View.OnClickListener listener) {
        if ("".equals(text)) {
            btn_neg.setText("取消");
        } else {
            btn_neg.setText(text);
        }
        btn_neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    /**
     * @param text
     * @param color
     * @param textSize
     * @param listener
     * @return
     */
    public EditDialog setNegativeButton(String text, int color, float textSize,
                                        final View.OnClickListener listener) {
        if ("".equals(text)) {
            btn_neg.setText("取消");
        } else {
            btn_neg.setText(text);
        }
        btn_neg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        btn_neg.setTextColor(color);
        btn_neg.setTextSize(textSize);
        return this;
    }

    /**
     *
     */
    public void dismiss() {
        dialog.dismiss();
    }

    /**
     *
     */
    public void show() {
        dialog.show();
    }
}