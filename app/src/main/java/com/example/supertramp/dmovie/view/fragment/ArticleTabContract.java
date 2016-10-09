package com.example.supertramp.dmovie.view.fragment;

import com.example.supertramp.dmovie.model.entity.article.ArticleEntity;
import java.util.List;

/**
 * Created by supertramp on 16/10/7.
 */
public class ArticleTabContract {

    public interface View {

        void updateList(List<ArticleEntity> list);

        void loadMore();

    }

    public interface Presenter {

        void getArticleList();

    }

}
