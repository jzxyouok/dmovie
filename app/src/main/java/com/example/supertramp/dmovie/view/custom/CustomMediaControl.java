package com.example.supertramp.dmovie.view.custom;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import com.example.supertramp.dmovie.R;
import com.example.supertramp.dmovie.adapter.listener.OnItemClickListener;
import com.example.supertramp.dmovie.base.BaseFrameLayout;
import com.example.supertramp.dmovie.utils.CommonUtils;
import com.example.supertramp.dmovie.utils.Constants;
import com.example.supertramp.dmovie.widget.PolicyCompat;
import com.pili.pldroid.player.IMediaController;
import java.util.Formatter;
import java.util.Locale;

/**
 * Created by supertramp on 16/8/18.
 */
public class CustomMediaControl extends BaseFrameLayout implements IMediaController {

    private IMediaController.MediaPlayerControl  mPlayer;
    private Context             mContext;
    private View                mAnchor;
    private View                mRoot;
    private WindowManager mWindowManager;
    private Window mWindow;
    private View                mDecor;
    private WindowManager.LayoutParams mDecorLayoutParams;
    private boolean             mShowing;
    private boolean             mDragging;
    private static final int    sDefaultTimeout = 3000;
    private static final int    FADE_OUT = 1;
    private static final int    SHOW_PROGRESS = 2;
    private int mCurrentPosition;
    StringBuilder               mFormatBuilder;
    Formatter mFormatter;

    private ImageView ivBack;
    private ImageView ivFull;
    private ImageView ivPlay;
    private TextView mEndTime, mCurrentTime;
    private SeekBar seekBar;

    private OnItemClickListener onItemClickListener;

    public CustomMediaControl(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mRoot = this;
        mContext = context;
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void onFinishInflate()
    {
        super.onFinishInflate();
        if (mRoot != null)
            initControllerView(mRoot);
    }

    public CustomMediaControl(Context context)
    {
        super(context);
        mContext = context;
        initFloatingWindowLayout();
        initFloatingWindow();
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        this.onItemClickListener = listener;
    }

    private void initFloatingWindow()
    {
        mWindowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        //这里得注意下，使用PolicyCompat替换原来的
        mWindow = PolicyCompat.createWindow(mContext);
        mWindow.setWindowManager(mWindowManager, null, null);
        mWindow.requestFeature(Window.FEATURE_NO_TITLE);
        mDecor = mWindow.getDecorView();
        mDecor.setOnTouchListener(mTouchListener);
        mWindow.setContentView(this);
        mWindow.setBackgroundDrawableResource(android.R.color.transparent);
        mWindow.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        setFocusable(true);
        setFocusableInTouchMode(true);
        setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        requestFocus();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void initFloatingWindowLayout()
    {
        mDecorLayoutParams = new WindowManager.LayoutParams();
        WindowManager.LayoutParams p = mDecorLayoutParams;
        p.gravity = Gravity.TOP | Gravity.LEFT;
        p.height = LayoutParams.WRAP_CONTENT;
        p.x = 0;
        p.format = PixelFormat.TRANSLUCENT;
        p.type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL;
        p.flags |= WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_SPLIT_TOUCH;
        p.token = null;
        p.windowAnimations = 0; // android.R.style.DropDownAnimationDown;
    }

    private void updateFloatingWindowLayout()
    {
        int [] anchorPos = new int[2];
        mAnchor.getLocationOnScreen(anchorPos);

        mDecor.measure(MeasureSpec.makeMeasureSpec(mAnchor.getWidth(), MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(mAnchor.getHeight(), MeasureSpec.AT_MOST));

        WindowManager.LayoutParams p = mDecorLayoutParams;
        p.width = mAnchor.getWidth();
        p.x = anchorPos[0] + (mAnchor.getWidth() - p.width) / 2;
        p.y = anchorPos[1] + mAnchor.getHeight() - mDecor.getMeasuredHeight();
    }

    private OnLayoutChangeListener mLayoutChangeListener =
            (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) ?
                    new OnLayoutChangeListener() {
                        public void onLayoutChange(View v, int left, int top, int right,
                                                   int bottom, int oldLeft, int oldTop, int oldRight,
                                                   int oldBottom) {
                            updateFloatingWindowLayout();
                            if (mShowing) {
                                mWindowManager.updateViewLayout(mDecor, mDecorLayoutParams);
                            }
                        }
                    } :
                    null;

    private OnTouchListener mTouchListener = new OnTouchListener()
    {
        public boolean onTouch(View v, MotionEvent event)
        {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
            {
                if (mShowing)
                {
                    hide();
                }
            }
            return false;
        }
    };

    public void setMediaPlayer(com.pili.pldroid.player.IMediaController.MediaPlayerControl player)
    {
        mPlayer = player;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void setAnchorView(View view)
    {
        boolean hasOnLayoutChangeListener = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB);
        if (hasOnLayoutChangeListener && mAnchor != null)
        {
            mAnchor.removeOnLayoutChangeListener(mLayoutChangeListener);
        }
        mAnchor = view;
        if (hasOnLayoutChangeListener && mAnchor != null)
        {
            mAnchor.addOnLayoutChangeListener(mLayoutChangeListener);
        }

        LayoutParams frameParams = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                CommonUtils.dip2px(getContext(), 240)
        );

        removeAllViews();
        //在这里更改底部布局，以及获取相应控件对象，进行操作，
        //建议原布局中控件类型和名字不要改，免得在这里又得修改，保证原功能完整
        //需要添加的控件，添加后做相应处理
        mRoot = makeControllerView();
        initControllerView(mRoot);
        addView(mRoot, frameParams);
    }

    public void resetRootView(int orientation)
    {
        FrameLayout.LayoutParams params;
        if (orientation != Configuration.ORIENTATION_PORTRAIT)
        {
            ivBack.setVisibility(View.GONE);
            params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        else
        {
            ivBack.setVisibility(View.VISIBLE);
            params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonUtils.dip2px(getContext(), 240));
        }
        mRoot.setLayoutParams(params);
    }

    protected View makeControllerView()
    {
        //MediaControllerd 布局
        LayoutInflater inflate = LayoutInflater.from(getContext());
        return inflate.inflate(R.layout.media_controller, null);
    }

    private void initControllerView(View v)
    {
        ivFull = (ImageView) mRoot.findViewById(R.id.iv_full);
        ivPlay = (ImageView) mRoot.findViewById(R.id.iv_mediacontrol_center);
        mCurrentTime = (TextView) mRoot.findViewById(R.id.tv_time_current);
        mEndTime = (TextView) mRoot.findViewById(R.id.tv_time_end);
        seekBar = (SeekBar) mRoot.findViewById(R.id.seekbar);
        seekBar.setMax(1000);
        seekBar.setOnSeekBarChangeListener(mSeekListener);

        mRoot.setOnTouchListener(mTouchListener);

        /*ivBack.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onItemClicked(v, Constants.ActionConstant.ACTION_BACK, null);
            }
        });*/

        ivPlay.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mPlayer.isPlaying())
                {
                    mPlayer.pause();
                    ivPlay.setImageResource(R.mipmap.icon_play);
                }
                else
                {
                    mPlayer.start();
                    ivPlay.setImageResource(R.mipmap.icon_pause);
                }
            }
        });

        ivFull.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                {
                    ivFull.setImageResource(R.mipmap.icon_full_close);
                    ((Activity)getContext()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                else
                {
                    ivFull.setImageResource(R.mipmap.icon_full);
                    ((Activity)getContext()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                onItemClicked(v, Constants.ActionConstant.ACTION_FULL, null);
            }
        });

        mFormatBuilder = new StringBuilder();
        mFormatter = new Formatter(mFormatBuilder, Locale.getDefault());
    }

    public void show()
    {
        show(sDefaultTimeout);
    }

    private void disableUnsupportedButtons()
    {
        try {



        } catch (IncompatibleClassChangeError ex)
        {
            ex.printStackTrace();
        }
    }

    public void show(int timeout)
    {
        try {

            if (!mShowing && mAnchor != null)
            {
                setProgress();
                updateFloatingWindowLayout();
                mWindowManager.addView(mDecor, mDecorLayoutParams);
                mShowing = true;
            }

            mHandler.sendEmptyMessage(SHOW_PROGRESS);
            Message msg = mHandler.obtainMessage(FADE_OUT);
            if (timeout != 0)
            {
                mHandler.removeMessages(FADE_OUT);
                mHandler.sendMessageDelayed(msg, timeout);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public boolean isShowing() {
        return mShowing;
    }


    /**
     * Remove the controller from the screen.
     */
    public void hide()
    {
        if (mAnchor == null)
            return;

        if (mShowing)
        {
            try {

                mHandler.removeMessages(SHOW_PROGRESS);
                mWindowManager.removeView(mDecor);

            } catch (IllegalArgumentException ex)
            {}
            mShowing = false;
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            int pos;
            switch (msg.what)
            {
                case FADE_OUT:
                    hide();
                    break;
                case SHOW_PROGRESS:
                    pos = setProgress();
                    if (!mDragging && mShowing && mPlayer.isPlaying())
                    {
                        msg = obtainMessage(SHOW_PROGRESS);
                        sendMessageDelayed(msg, 1000 - (pos % 1000));
                    }
                    break;
            }
        }
    };

    private String stringForTime(int timeMs)
    {
        int totalSeconds = timeMs / 1000;
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours   = totalSeconds / 3600;

        mFormatBuilder.setLength(0);
        if (hours > 0)
        {
            return mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString();
        }
        else
        {
            return mFormatter.format("%02d:%02d", minutes, seconds).toString();
        }
    }

    private int setProgress()
    {
        if (mPlayer == null || mDragging)
        {
            return 0;
        }
        int position = (int) mPlayer.getCurrentPosition();
        int duration = (int) mPlayer.getDuration();
        if (seekBar != null)
        {
            if (duration > 0)
            {
                long pos = 1000L * position / duration;
                seekBar.setProgress( (int) pos);
            }
            int percent = mPlayer.getBufferPercentage();
            seekBar.setSecondaryProgress(percent * 10);
        }

        if (mEndTime != null)
        {
            mEndTime.setText(stringForTime(duration));
        }

        if (mCurrentTime != null)
        {
            mCurrentTime.setText(stringForTime(position));
        }

        return position;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_UP:
                show(sDefaultTimeout); // start timeout
                break;
            case MotionEvent.ACTION_CANCEL:
                hide();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onTrackballEvent(MotionEvent ev)
    {
        show(sDefaultTimeout);
        return false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        final boolean uniqueDown = event.getRepeatCount() == 0
                && event.getAction() == KeyEvent.ACTION_DOWN;
        if (keyCode == KeyEvent.KEYCODE_MEDIA_PLAY) {
            if (uniqueDown && !mPlayer.isPlaying()) {
                mPlayer.start();
            }
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_MEDIA_STOP
                || keyCode == KeyEvent.KEYCODE_MEDIA_PAUSE) {
            if (uniqueDown && mPlayer.isPlaying()) {
                mPlayer.pause();
                show(sDefaultTimeout);
            }
            return true;
        }

        return super.dispatchKeyEvent(event);
    }

    private OnClickListener mPauseListener = new OnClickListener()
    {
        public void onClick(View v)
        {
            updatePausePlay();
        }
    };

    private void updatePausePlay()
    {
    }

    protected void updatePausePlay(boolean isPlaying, ImageView pauseButton)
    {
        if (isPlaying)
        {
            mPlayer.pause();
            pauseButton.setSelected(true);
        }
        else
        {
            mPlayer.start();
            pauseButton.setSelected(false);
        }
    }

    public void startPlayer()
    {
        mPlayer.start();
        if (mCurrentPosition != 0)
        {
            mPlayer.seekTo(mCurrentPosition);
            mCurrentPosition = 0;
        }
        setProgress();
        mHandler.sendEmptyMessage(SHOW_PROGRESS);
    }

    public void updatePauseBtn()
    {
        if (mPlayer.isPlaying())
        {
        }
        else
        {
        }
    }

    private SeekBar.OnSeekBarChangeListener mSeekListener = new SeekBar.OnSeekBarChangeListener()
    {
        public void onStartTrackingTouch(SeekBar bar)
        {
            show();
            mDragging = true;
            mHandler.removeMessages(SHOW_PROGRESS);
        }

        public void onProgressChanged(SeekBar bar, int progress, boolean fromuser)
        {
            if (!fromuser)
            {
                return;
            }

            long duration = mPlayer.getDuration();
            long newposition = (duration * progress) / 1000L;
            mPlayer.seekTo( (int) newposition);
            if (mCurrentTime != null)
                mCurrentTime.setText(stringForTime( (int) newposition));
        }

        public void onStopTrackingTouch(SeekBar bar)
        {
            mDragging = false;
            setProgress();
            mHandler.sendEmptyMessage(SHOW_PROGRESS);
        }
    };

    @Override
    public void setEnabled(boolean enabled)
    {
        if (seekBar != null)
        {
            seekBar.setEnabled(enabled);
        }
        disableUnsupportedButtons();
        super.setEnabled(enabled);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setClassName(MediaController.class.getName());
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        info.setClassName(MediaController.class.getName());
    }

    private OnClickListener mRewListener = new OnClickListener()
    {
        public void onClick(View v) {
            int pos = (int) mPlayer.getCurrentPosition();
            pos -= 5000; // milliseconds
            mPlayer.seekTo(pos);
            setProgress();

            show(sDefaultTimeout);
        }
    };

    private OnClickListener mFfwdListener = new OnClickListener()
    {
        public void onClick(View v) {
            int pos = (int) mPlayer.getCurrentPosition();
            pos += 15000; // milliseconds
            mPlayer.seekTo(pos);
            setProgress();

            show(sDefaultTimeout);
        }
    };

    public void seekTo(int mCurrentPosition)
    {
        if (mPlayer != null)
            mPlayer.seekTo(mCurrentPosition);
    }

    public interface MediaPlayerControl
    {
        void    start();
        void    pause();
        int     getDuration();
        int     getCurrentPosition();
        void    seekTo(int pos);
        boolean isPlaying();
        int     getBufferPercentage();
        boolean canPause();
    }

}
