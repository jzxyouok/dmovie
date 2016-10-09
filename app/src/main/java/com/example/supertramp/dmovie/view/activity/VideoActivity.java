package com.example.supertramp.dmovie.view.activity;

import android.content.res.Configuration;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.supertramp.dmovie.R;
import com.example.supertramp.dmovie.base.BaseActivity;
import com.example.supertramp.dmovie.base.BaseFrameLayout;
import com.example.supertramp.dmovie.model.entity.video.VideoDetailEntity;
import com.example.supertramp.dmovie.presenter.VideoPresenter;
import com.example.supertramp.dmovie.utils.CommonUtils;
import com.example.supertramp.dmovie.utils.Constants;
import com.example.supertramp.dmovie.view.custom.DVideoView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by supertramp on 16/7/20.
 */
public class VideoActivity extends BaseActivity implements VideoContract.View, BaseFrameLayout.OnChildViewClickListener {

    @Nullable
    @BindView(R.id.videoView)
    DVideoView videoView;
    @Nullable
    @BindView(R.id.tv_back)
    TextView tvBack;
    @Nullable
    @BindView(R.id.wv_summary)
    WebView summary;

    private String postid;
    private VideoPresenter presenter;

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_video;
    }

    @Override
    protected void initView() {
        super.initView();
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        tvBack.setTypeface(iconfont);
    }

    @Override
    protected void initData()
    {
        super.initData();
        postid = getIntent().getStringExtra(Constants.INTENT.INTENT_POSTID);
        presenter = new VideoPresenter(this, this);
        presenter.registerListener();
        presenter.getVideoDetail(postid);
    }

    @Override
    protected void initListener()
    {

        super.initListener();
        videoView.setOnChildViewClickListener(this);

    }

    @Override
    public void updateView(VideoDetailEntity entity)
    {

        videoView.play(entity.getContent().getVideo().get(0).getQiniu_url());
        WebSettings settings = summary.getSettings();
        settings.setJavaScriptEnabled(true);
        summary.loadUrl(Constants.BASE_URL_VIDEO + "/" + entity.getPostid() + "?qingapp=app_new");

    }

    @OnClick(R.id.rl_back)
    public void onClick(View view)
    {
        finish();
    }

    @Override
    public void onChildViewClick(View view, int action, Object obj)
    {
        switch (action)
        {
            case Constants.ActionConstant.ACTION_BACK:
                finish();
                break;
            case Constants.ActionConstant.ACTION_FULL:
                break;
            default:break;
        }
    }

    /**
     * 开屏
     */
    @Override
    public void screenOn()
    {}

    /**
     * 锁屏
     */
    @Override
    public void screenOff()
    {
        videoView.pause();
    }

    /**
     * 解锁
     */
    @Override
    public void userPresent()
    {
        videoView.resume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {

        super.onConfigurationChanged(newConfig);
        int orientation = getResources().getConfiguration().orientation;
        RelativeLayout.LayoutParams params;
        if (orientation != Configuration.ORIENTATION_PORTRAIT)//竖屏转横屏
        {
            params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            videoView.setLayoutParams(params);
        }
        else
        {
            params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonUtils.dip2px(this, 240));
            videoView.setLayoutParams(params);
        }
        videoView.resetMediaControl(orientation);

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        videoView.release();
        presenter.releaseReceiver();
    }

}
