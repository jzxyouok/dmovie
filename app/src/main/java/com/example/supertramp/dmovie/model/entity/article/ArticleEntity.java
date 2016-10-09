package com.example.supertramp.dmovie.model.entity.article;

/**
 * Created by supertramp on 16/10/7.
 */
public class ArticleEntity {

    private String article_sid;
    private String title;
    private String display_title;
    private String tag;
    private String tag_color;
    private String img_url;

    public String getArticle_sid() {
        return article_sid;
    }

    public void setArticle_sid(String article_sid) {
        this.article_sid = article_sid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplay_title() {
        return display_title;
    }

    public void setDisplay_title(String display_title) {
        this.display_title = display_title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag_color() {
        return tag_color;
    }

    public void setTag_color(String tag_color) {
        this.tag_color = tag_color;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

}
