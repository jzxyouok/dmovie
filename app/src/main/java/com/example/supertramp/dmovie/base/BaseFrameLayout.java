package com.example.supertramp.dmovie.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.ButterKnife;

/**
 * Created by supertramp on 16/8/7.
 */
public abstract class BaseFrameLayout extends FrameLayout {

    private OnChildViewClickListener listener;

    public BaseFrameLayout(Context context) {
        super(context);
        init(context);
    }

    public BaseFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context)
    {
        if (getLayoutId() != 0)
        {
            View view = LayoutInflater.from(context).inflate(getLayoutId(), this, true);
            if (view != null)
            {
                ButterKnife.bind(this, view);
            }
        }
        initView();
        initData();
        initListener();
    }

    public abstract int getLayoutId();

    protected void initView()
    {

    }

    protected void initData()
    {

    }

    protected void initListener()
    {

    }

    public void setOnChildViewClickListener(OnChildViewClickListener listener)
    {
        this.listener = listener;
    }

    public void onItemClicked(View view, int action, Object obj)
    {
        if (listener != null)
        {
            listener.onChildViewClick(view, action, obj);
        }
    }

    public interface OnChildViewClickListener {

        public void onChildViewClick(View view, int action, Object obj);

    }
}
