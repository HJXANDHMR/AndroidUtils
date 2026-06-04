package utils;

import android.util.Log;

/**
 * Unified Log management class
 */
public class LogUtil {

    private LogUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // Whether to print logs, can be initialized in application's onCreate method
    public static boolean isDebug = true;
    private static final String TAG = "LogUtil";

    // The following four functions use the default tag
    public static void i(String msg)
    {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void d(String msg)
    {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void e(String msg)
    {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void v(String msg)
    {
        if (isDebug)
            Log.v(TAG, msg);
    }

    // The following functions accept a custom tag
    public static void i(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg)
    {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg)
    {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void v(String tag, String msg)
    {
        if (isDebug)
            Log.v(tag, msg);
    }
}

