package com.example.supertramp.dmovie.view.activity;

import com.example.supertramp.dmovie.model.entity.BaseCallEntity;
import com.example.supertramp.dmovie.model.entity.home.BriefVideoEntity;
import java.util.List;
import retrofit2.Callback;

/**
 * Created by supertramp on 16/7/30.
 */
public class SearchContract {

    public interface View {

        String getKeyword();

        void setKeyword(String keyword);

        void updateList(List<BriefVideoEntity> list);

    }

    public interface Presenter {

        void getSearchVideos();

    }

}
