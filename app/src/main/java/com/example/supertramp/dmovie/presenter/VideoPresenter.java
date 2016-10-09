package com.example.supertramp.dmovie.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.example.supertramp.dmovie.model.api.VideoAPI;
import com.example.supertramp.dmovie.model.entity.BaseCallEntity;
import com.example.supertramp.dmovie.model.entity.video.VideoDetailEntity;
import com.example.supertramp.dmovie.view.activity.VideoContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by supertramp on 16/8/7.
 */
public class VideoPresenter implements VideoContract.Presenter {

    private Context context;
    private VideoContract.View view;
    private ScreenBroadcastReceiver screenReceiver;

    public VideoPresenter(Context context, VideoContract.View view)
    {
        this.context = context;
        this.view = view;
        screenReceiver = new ScreenBroadcastReceiver();
    }

    @Override
    public void getVideoDetail(String postid) {

        VideoAPI.getVideoDetail(postid, new Callback<BaseCallEntity<VideoDetailEntity>>() {
            @Override
            public void onResponse(Call<BaseCallEntity<VideoDetailEntity>> call, Response<BaseCallEntity<VideoDetailEntity>> response)
            {
                VideoDetailEntity entity = response.body().getData();
                view.updateView(entity);
            }

            @Override
            public void onFailure(Call<BaseCallEntity<VideoDetailEntity>> call, Throwable t)
            {
                Log.i("error", t.toString());
            }
        });

    }

    @Override
    public void registerListener() {

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);//屏幕开启
        filter.addAction(Intent.ACTION_SCREEN_OFF);//锁定屏幕
        filter.addAction(Intent.ACTION_USER_PRESENT);//解锁
        context.registerReceiver(screenReceiver, filter);

    }

    @Override
    public void releaseReceiver() {

        context.unregisterReceiver(screenReceiver);

    }

    class ScreenBroadcastReceiver extends BroadcastReceiver
    {
        private String action = null;

        @Override
        public void onReceive(Context context, Intent intent)
        {
            action = intent.getAction();
            if (Intent.ACTION_SCREEN_ON.equals(action))
            {
                view.screenOn();
            }
            else if (Intent.ACTION_SCREEN_OFF.equals(action))
            {
                view.screenOff();
            }
            else if (Intent.ACTION_USER_PRESENT.equals(action))
            {
                view.userPresent();
            }
        }

    }

}
