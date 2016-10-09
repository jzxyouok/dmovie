package com.example.supertramp.dmovie.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;

/**
 * Created by supertramp on 16/7/3.
 */
public abstract class BaseFragment extends Fragment{

    protected View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        rootView = inflater.inflate(getLayoutID(), container, false);
        ButterKnife.bind(this, rootView);
        initView();
        initListener();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract int getLayoutID();

    public void initView(){};

    public void initData(){};

    public void initListener(){};
}
