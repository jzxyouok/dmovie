package com.example.supertramp.dmovie.model.api;

import com.example.supertramp.dmovie.model.entity.BaseCallEntity;
import com.example.supertramp.dmovie.model.entity.article.ArticleEntity;
import com.example.supertramp.dmovie.model.service.ArticleService;
import com.example.supertramp.dmovie.utils.Constants;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by supertramp on 16/10/7.
 */
public class ArticleAPI {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_ARTICLE)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static ArticleService service = retrofit.create(ArticleService.class);

    public static void getArticleList(String pageNum, String pageSize, String tagId, Callback<BaseCallEntity<List<ArticleEntity>>> callback)
    {
        Call<BaseCallEntity<List<ArticleEntity>>> call = service.getArticleList(pageNum, pageSize, tagId);
        call.enqueue(callback);
    }

}
