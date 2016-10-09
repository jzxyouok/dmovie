package com.example.supertramp.dmovie.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.supertramp.dmovie.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by supertramp on 16/7/4.
 */
public abstract class BaseActivity extends Activity {

    @Nullable
    @BindView(R.id.tv_title)
    protected TextView tvTitle;
    @Nullable
    @BindView(R.id.ll_category)
    protected LinearLayout llCategory;
    @Nullable
    @BindView(R.id.tv_title_video)
    protected TextView tvVideo;
    @Nullable
    @BindView(R.id.tv_title_video_indicator)
    protected TextView tvVideoIndicator;
    @Nullable
    @BindView(R.id.tv_title_article)
    protected TextView tvArticle;
    @Nullable
    @BindView(R.id.tv_title_article_indicator)
    protected TextView tvArticleIndicator;
    @Nullable
    @BindView(R.id.iv_search)
    protected ImageView ivSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    protected abstract int getLayoutId();

    protected void initView() {}

    protected void initData() {};

    protected void initListener() {};

}
