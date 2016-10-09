package com.example.supertramp.dmovie.model.entity.home;

import java.util.List;

/**
 * Created by supertramp on 16/7/23.
 */
public class BriefVideoEntity {

    private String postid;
    private String title;
    private String image;   //封面图
    private String duration;    //时长
    private String rating;  //分数
    private String share_num;   //分享数
    private List<CategoryEntity> cates;

    public BriefVideoEntity(){}

    public BriefVideoEntity(String postid, String title, String image, String duration, List<CategoryEntity> cates)
    {
        this.postid = postid;
        this.title = title;
        this.image = image;
        this.duration = duration;
        this.cates = cates;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postId) {
        this.postid = postid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<CategoryEntity> getCates() {
        return cates;
    }

    public void setCates(List<CategoryEntity> cates) {
        this.cates = cates;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getShare_num() {
        return share_num;
    }

    public void setShare_num(String share_num) {
        this.share_num = share_num;
    }

}
