package com.example.supertramp.dmovie.model.service;

import com.example.supertramp.dmovie.model.entity.BaseCallEntity;
import com.example.supertramp.dmovie.model.entity.article.ArticleEntity;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by supertramp on 16/10/7.
 */
public interface ArticleService {

    @GET("article/category")
    Call<BaseCallEntity<List<ArticleEntity>>> getArticleList(@Query("page_num") String pageNum, @Query("page_size") String pageSize, @Query("tag_id") String tagId);

}
