package com.example.supertramp.dmovie.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.example.supertramp.dmovie.R;
import com.example.supertramp.dmovie.view.activity.ArticleActivity;
import com.example.supertramp.dmovie.view.activity.VideoActivity;

/**
 * Created by supertramp on 16/7/21.
 */
public class ActivityUtils {

    public static void nextActivity(Context context, Class activity)
    {
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }

    public static void nextVideoActivity(Context context, String postid)
    {
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtra(Constants.INTENT.INTENT_POSTID, postid);
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public static void nextArticleActivity(Context context, String articleId)
    {
        Intent intent = new Intent(context, ArticleActivity.class);
        intent.putExtra(Constants.INTENT.INTENT_ARTICLEID, articleId);
        context.startActivity(intent);
    }

}
