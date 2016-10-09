package com.example.supertramp.dmovie.utils;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by supertramp on 16/7/25.
 */
public class ImageUtils {

    private static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .displayer(new FadeInBitmapDisplayer(500))
            .build();

    public static void displayImage(String url, final ImageView imageView)
    {
        ImageLoader.getInstance().displayImage(url, imageView, options, new ImageLoadingListener()
        {
            @Override
            public void onLoadingStarted(String imageUri, View view)
            {
                imageView.setImageBitmap(null);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason)
            {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
            {

            }

            @Override
            public void onLoadingCancelled(String imageUri, View view)
            {

            }
        });
    }

    public static void displayImageFromLocal(String uri, ImageView imageView)
    {
        ImageLoader.getInstance().displayImage("file://" + uri, imageView);
    }

    public static String getUriFromUrl(String url)
    {
        return ImageLoader.getInstance().getDiskCache().get(url).getPath();
    }

}
