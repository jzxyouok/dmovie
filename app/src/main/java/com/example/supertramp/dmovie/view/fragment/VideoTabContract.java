package com.example.supertramp.dmovie.view.fragment;

import com.example.supertramp.dmovie.model.entity.home.BriefVideoEntity;
import java.util.List;

/**
 * Created by supertramp on 16/7/22.
 */
public class VideoTabContract {

    public interface View {

        void updateList(List<BriefVideoEntity> list);
        void loadMore();

    }

    public interface Presenter {

        void getVideoList();    //获取首页最新视频

        void updateSQLite(List<BriefVideoEntity> list);

    }

}
