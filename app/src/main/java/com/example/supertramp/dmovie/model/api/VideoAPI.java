package com.example.supertramp.dmovie.model.api;

import com.example.supertramp.dmovie.model.entity.BaseCallEntity;
import com.example.supertramp.dmovie.model.entity.home.BriefVideoEntity;
import com.example.supertramp.dmovie.model.entity.video.VideoDetailEntity;
import com.example.supertramp.dmovie.model.service.VideoService;
import com.example.supertramp.dmovie.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by supertramp on 16/7/21.
 */
public class VideoAPI {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_VIDEO)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static VideoService service = retrofit.create(VideoService.class);

    public static void getVideoDetail(String postid, Callback<BaseCallEntity<VideoDetailEntity>> callback)
    {
        Call<BaseCallEntity<VideoDetailEntity>> call = service.getVideoDetail(postid);
        call.enqueue(callback);
    }

    //获取列表数据
    public static void getVideoList(String page, String size, String tab, Callback<BaseCallEntity<List<BriefVideoEntity>>> callback)
    {
        Call<BaseCallEntity<List<BriefVideoEntity>>> call = service.getVideoList(page, size, tab);
        call.enqueue(callback);
    }

    public static void getSearchVideos(String page, String size, String keyword, Callback<BaseCallEntity<List<BriefVideoEntity>>> callback)
    {
        Call<BaseCallEntity<List<BriefVideoEntity>>> call = service.getSearchVideos(page, size, keyword);
        call.enqueue(callback);
    }

}
