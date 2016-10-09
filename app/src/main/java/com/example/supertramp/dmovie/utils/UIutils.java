package com.example.supertramp.dmovie.utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by supertramp on 16/8/7.
 */
public class UIutils {

    public static void showKeyboard(Context context, EditText view)
    {
        InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.showSoftInputFromInputMethod(view.getWindowToken(), 0);
    }

    public static void hideKeyboard(Context context, EditText view)
    {
        InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager.isActive())
        {
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
