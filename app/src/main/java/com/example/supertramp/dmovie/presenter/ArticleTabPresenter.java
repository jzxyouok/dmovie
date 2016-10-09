package com.example.supertramp.dmovie.presenter;

import android.content.Context;
import com.example.supertramp.dmovie.base.BasePresenter;
import com.example.supertramp.dmovie.model.api.ArticleAPI;
import com.example.supertramp.dmovie.model.entity.BaseCallEntity;
import com.example.supertramp.dmovie.model.entity.article.ArticleEntity;
import com.example.supertramp.dmovie.view.fragment.ArticleTabContract;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by supertramp on 16/10/7.
 */
public class ArticleTabPresenter extends BasePresenter implements ArticleTabContract.Presenter {

    private int pageNum = 1;
    private Context context;
    private ArticleTabContract.View view;

    public ArticleTabPresenter(Context context, ArticleTabContract.View view)
    {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getArticleList() {

        ArticleAPI.getArticleList(String.valueOf(pageNum), "10", "6jnof5121cuw", new Callback<BaseCallEntity<List<ArticleEntity>>>()
        {

            @Override
            public void onResponse(Call<BaseCallEntity<List<ArticleEntity>>> call, Response<BaseCallEntity<List<ArticleEntity>>> response)
            {

                List<ArticleEntity> list = response.body().getData();
                if (list == null || list.size() == 0)
                {
                    showToast(context, "没有更多影评了哦");
                }

                view.updateList(list);
                pageNum ++;

            }

            @Override
            public void onFailure(Call<BaseCallEntity<List<ArticleEntity>>> call, Throwable t)
            {}

        });

    }

}
