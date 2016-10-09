package com.example.supertramp.dmovie.model.service;

import com.example.supertramp.dmovie.model.entity.BaseCallEntity;
import com.example.supertramp.dmovie.model.entity.home.BannerEntity;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by supertramp on 16/7/20.
 */
public interface HomeService {

    @GET("apiv3/index/getBanner")
    Call<BaseCallEntity<List<BannerEntity>>> getBanner();



}
