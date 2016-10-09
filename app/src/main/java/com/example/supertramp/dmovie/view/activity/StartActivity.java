package com.example.supertramp.dmovie.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import com.example.supertramp.dmovie.R;
import com.example.supertramp.dmovie.utils.ActivityUtils;

/**
 * Created by supertramp on 16/10/9.
 */
public class StartActivity extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        imageView = (ImageView) findViewById(R.id.imageView);

        ScaleAnimation anim = (ScaleAnimation) AnimationUtils.loadAnimation(this, R.anim.scale_big);

        anim.setFillAfter(true);
        anim.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                ActivityUtils.nextActivity(StartActivity.this, HomeActivity.class);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });

        try
        {

            Thread.sleep(500);

        }catch (Exception e)
        {
            ActivityUtils.nextActivity(StartActivity.this, HomeActivity.class);
            finish();
        }

        imageView.startAnimation(anim);
    }
}
