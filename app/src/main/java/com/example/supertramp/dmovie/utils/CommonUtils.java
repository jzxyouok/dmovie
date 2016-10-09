package com.example.supertramp.dmovie.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by supertramp on 16/7/23.
 */
public class CommonUtils {

    public static String toFormatTime(String duration)
    {
        long time = Long.parseLong(duration);
        int second = (int)(time % 60);
        int min = (int)(time / 60);

        return min + "'" + second;
    }

    public static int getScreenWidth(Context context)
    {
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    public static int dip2px(Context context, float dipValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

}
