package com.example.supertramp.dmovie.model.entity.article;

import java.util.List;

/**
 * Created by supertramp on 16/10/7.
 */
public class ArticleListEntity {

    private Long pre_date;
    private List<DailyArticleEntity> daily_articles;

    public Long getPre_date() {
        return pre_date;
    }

    public void setPre_date(Long pre_date) {
        this.pre_date = pre_date;
    }

    public List<DailyArticleEntity> getDaily_articles() {
        return daily_articles;
    }

    public void setDaily_articles(List<DailyArticleEntity> daily_articles) {
        this.daily_articles = daily_articles;
    }

}
