package com.example.supertramp.dmovie.app;

import android.app.Application;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by supertramp on 16/6/28.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate()
    {
        super.onCreate();
        initImageLoader();
    }

    private void initImageLoader()
    {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();

        ImageLoader.getInstance().init(config);
    }

}
