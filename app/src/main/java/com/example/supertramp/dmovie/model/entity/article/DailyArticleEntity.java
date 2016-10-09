package com.example.supertramp.dmovie.model.entity.article;

import java.util.List;

/**
 * Created by supertramp on 16/10/7.
 */
public class DailyArticleEntity {

    private String article_date;
    private List<ArticleEntity> articles;

    public String getArticle_date() {
        return article_date;
    }

    public void setArticle_date(String article_date) {
        this.article_date = article_date;
    }

    public List<ArticleEntity> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleEntity> articles) {
        this.articles = articles;
    }

}
