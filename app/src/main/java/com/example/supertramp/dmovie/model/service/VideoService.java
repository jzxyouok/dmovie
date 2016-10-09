package com.example.supertramp.dmovie.model.service;

import com.example.supertramp.dmovie.model.entity.BaseCallEntity;
import com.example.supertramp.dmovie.model.entity.home.BriefVideoEntity;
import com.example.supertramp.dmovie.model.entity.video.VideoDetailEntity;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by supertramp on 16/7/21.
 */
public interface VideoService {

    @GET("apiv3/post/getPostByTab")
    Call<BaseCallEntity<List<BriefVideoEntity>>> getVideoList(@Query("p") String page, @Query("size") String size, @Query("tab") String tab);

    @GET("apiv3/search/index")
    Call<BaseCallEntity<List<BriefVideoEntity>>> getSearchVideos(@Query("p") String page, @Query("size") String size, @Query("kw") String keyword);

    @GET("apiv3/post/view")
    Call<BaseCallEntity<VideoDetailEntity>> getVideoDetail(@Query("postid") String postid);

}
