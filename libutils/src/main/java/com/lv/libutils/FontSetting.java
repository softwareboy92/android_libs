package com.lv.libutils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;
import android.widget.Toast;

public class FontSetting {

    public FontSetting() {
    }

    public static void setFont(Context context, TextView textView, String fontsPath) {
        try {
            Typeface fromAsset = Typeface.createFromAsset(context.getAssets(), fontsPath);
            textView.setTypeface(fromAsset);
        } catch (Exception e) {
            Toast.makeText(context, "服务器出错啦！", Toast.LENGTH_SHORT).show();
        }
    }
}