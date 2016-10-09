package com.example.supertramp.dmovie.model.api;

import com.example.supertramp.dmovie.model.entity.BaseCallEntity;
import com.example.supertramp.dmovie.model.entity.home.BannerEntity;
import com.example.supertramp.dmovie.model.entity.home.BriefVideoEntity;
import com.example.supertramp.dmovie.model.service.HomeService;
import com.example.supertramp.dmovie.utils.Constants;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by supertramp on 16/7/20.
 */
public class HomeAPI {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_VIDEO)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static HomeService service = retrofit.create(HomeService.class);
}
