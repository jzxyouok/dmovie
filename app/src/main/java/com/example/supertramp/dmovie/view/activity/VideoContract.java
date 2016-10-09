package com.example.supertramp.dmovie.view.activity;

import com.example.supertramp.dmovie.model.entity.video.VideoDetailEntity;

/**
 * Created by supertramp on 16/8/7.
 */
public class VideoContract {

    public interface View {

        void updateView(VideoDetailEntity entity);

        void screenOn();

        void screenOff();

        void userPresent();

    }

    public interface Presenter {

        void getVideoDetail(String postid);

        void registerListener();

        void releaseReceiver();

    }

}
