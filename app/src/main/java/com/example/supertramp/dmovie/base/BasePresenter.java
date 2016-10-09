package com.example.supertramp.dmovie.base;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by supertramp on 16/7/26.
 */
public class BasePresenter {

    public void showToast(Context context, String toast)
    {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }

    public void showToast(Context context, int resId)
    {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

}
