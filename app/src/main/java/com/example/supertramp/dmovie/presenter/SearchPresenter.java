package com.example.supertramp.dmovie.presenter;

import android.content.Context;
import com.example.supertramp.dmovie.model.api.VideoAPI;
import com.example.supertramp.dmovie.model.entity.BaseCallEntity;
import com.example.supertramp.dmovie.model.entity.home.BriefVideoEntity;
import com.example.supertramp.dmovie.view.activity.SearchContract;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by supertramp on 16/7/30.
 */
public class SearchPresenter implements SearchContract.Presenter {

    private Context context;
    private SearchContract.View view;

    private int page = 1;
    private String size = "10";
    private List<BriefVideoEntity> videos;


    public SearchPresenter(Context context, SearchContract.View view)
    {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getSearchVideos() {

        VideoAPI.getSearchVideos(String.valueOf(page), size, view.getKeyword(), new Callback<BaseCallEntity<List<BriefVideoEntity>>>()
        {
            @Override
            public void onResponse(Call<BaseCallEntity<List<BriefVideoEntity>>> call, Response<BaseCallEntity<List<BriefVideoEntity>>> response)
            {
                page ++;
                videos = response.body().getData();
                if (videos != null && videos.size() != 0)
                {
                    view.updateList(videos);
                }
            }

            @Override
            public void onFailure(Call<BaseCallEntity<List<BriefVideoEntity>>> call, Throwable t)
            {

            }
        });

    }

}
