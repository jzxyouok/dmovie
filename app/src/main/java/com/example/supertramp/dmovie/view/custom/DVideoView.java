package com.example.supertramp.dmovie.view.custom;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.supertramp.dmovie.base.BaseFrameLayout;
import com.example.supertramp.dmovie.utils.CommonUtils;
import com.example.supertramp.dmovie.utils.Constants;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.widget.PLVideoView;

/**
 * Created by supertramp on 16/8/7.
 */
public class DVideoView extends BaseFrameLayout implements BaseFrameLayout.OnChildViewClickListener {

    private PLVideoView videoView;
    private CustomMediaControl mediaControl;

    public DVideoView(Context context)
    {
        super(context);
    }

    public DVideoView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView()
    {
        super.initView();
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        videoView = new PLVideoView(getContext());
        mediaControl = new CustomMediaControl(getContext());
        videoView.setMediaController(mediaControl);
        mediaControl.show();
        initVideoView();
        addView(videoView, params);
    }

    private void initVideoView()
    {
        AVOptions options = new AVOptions();
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_GET_AV_FRAME_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_START_ON_PREPARED, 0);
        videoView.setAVOptions(options);
        videoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_PAVED_PARENT);
    }

    @Override
    protected void initListener()
    {
        super.initListener();
        mediaControl.setOnChildViewClickListener(this);
    }

    public void resetMediaControl(int orientation)
    {
        mediaControl.resetRootView(orientation);
    }

    public void play(String url)
    {
        videoView.setVideoPath(url);
        videoView.start();
    }

    public void resume()
    {
        videoView.start();
    }

    public void pause()
    {
        videoView.pause();
    }

    public void release()
    {
        videoView.stopPlayback();
    }

    @Override
    public void onChildViewClick(View view, int action, Object obj)
    {
        switch (action)
        {
            case Constants.ActionConstant.ACTION_BACK:
            case Constants.ActionConstant.ACTION_FULL:
                onItemClicked(view, action, obj);
                break;
            default:break;
        }
    }
}
