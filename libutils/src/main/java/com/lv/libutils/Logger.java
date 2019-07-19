package com.lv.libutils;

import android.util.Log;

@SuppressWarnings("all")
public class Logger {
    /**
     * Wrapper API for logging
     */
    protected final static String TAG = "YourProjectName"; // /< LOG TAG
    protected final static int LOG_OUTPUT_MAX_LENGTH = 300;


    public static void output(String msg) {
        wrapperMsg(msg);
    }

    /**
     * Wrapper the message
     *
     * @param msg : The message to wrapper
     */
    protected static void wrapperMsg(String msg) {
        if (null == msg || !isDebugMode()) {
            return;
        }

        while (msg.length() > LOG_OUTPUT_MAX_LENGTH) {
            final String tempMsg = msg.substring(0, LOG_OUTPUT_MAX_LENGTH);
            msg = msg.substring(LOG_OUTPUT_MAX_LENGTH, msg.length());
            Log.d(TAG, tempMsg);
        }

        StackTraceElement elem = new Throwable().fillInStackTrace()
                .getStackTrace()[2];
        Log.d(TAG, msg + "(" + elem.getFileName() + " : " + elem.getLineNumber() + ")");
    }

    public static void i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void d(String tag, String msg) {
        Log.e(tag, msg);
    }

    private static boolean isDebugMode() {

        return false;
    }
}
