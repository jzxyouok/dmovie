package com.example.supertramp.dmovie.utils;

/**
 * Created by supertramp on 16/7/25.
 */
public class StringUtils {

    public static boolean isEmpty(String str)
    {
        if (str == null || str.equals(""))
        {
            return true;
        }
        return false;
    }

}
