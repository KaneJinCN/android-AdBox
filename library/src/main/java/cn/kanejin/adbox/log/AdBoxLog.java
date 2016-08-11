package cn.kanejin.adbox.log;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Kane on 8/10/16.
 */
public class AdBoxLog {

    private static final String GlobalTag = "AdBox";

    private static String prependTag(String tag) {
        return GlobalTag + "_" + tag;
    }

    public static int d(String tag, String msg) {
        return Log.d(prependTag(tag), msg);
    }

    public static int d(String tag, String msg, Throwable tr) {
        return Log.d(prependTag(tag), msg, tr);
    }

    public static int d(String tag, ViewGroup.LayoutParams layoutParams) {
        return d(tag, ViewLogUtil.debugLayoutParams(layoutParams));
    }

    public static int d(String tag, View view) {
        return d(tag, ViewLogUtil.debugView(view, 0));
    }
}
