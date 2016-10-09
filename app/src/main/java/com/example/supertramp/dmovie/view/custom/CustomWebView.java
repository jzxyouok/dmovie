package com.example.supertramp.dmovie.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by supertramp on 16/10/7.
 */
public class CustomWebView extends WebView {

    private OnScrollChangeCallback callback;

    public CustomWebView(Context context) {
        super(context);
    }

    public CustomWebView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public CustomWebView(Context context, AttributeSet attrs, int style)
    {
        super(context, attrs, style);
    }

    public void setOnScrollChangeCallback(OnScrollChangeCallback callback)
    {
        this.callback = callback;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy)
    {
        super.onScrollChanged(x, y, oldx, oldy);
        callback.onScroll(x, y, x - oldx, y - oldy);
    }

    public static interface OnScrollChangeCallback {

        public void onScroll(int x, int y, int dx, int dy);

    }

}
