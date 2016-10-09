package com.example.supertramp.dmovie.model.entity.video;

/**
 * Created by supertramp on 16/7/21.
 */
public class VideoDetailEntity {

    private String postid;
    private String title;
    private String app_fu_title;
    private String intro;
    private String count_comment;
    private String is_album;
    private String is_collect;
    private VideoContentEntity content;
    private String image;
    private String rating;
    private String publish_time;
    private String count_like;
    private String count_share;
    private String tags;
    private String share_sub_title;
    private String weibo_share_image;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApp_fu_title() {
        return app_fu_title;
    }

    public void setApp_fu_title(String app_fu_title) {
        this.app_fu_title = app_fu_title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getCount_comment() {
        return count_comment;
    }

    public void setCount_comment(String count_comment) {
        this.count_comment = count_comment;
    }

    public String getIs_album() {
        return is_album;
    }

    public void setIs_album(String is_album) {
        this.is_album = is_album;
    }

    public String getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(String is_collect) {
        this.is_collect = is_collect;
    }

    public VideoContentEntity getContent() {
        return content;
    }

    public void setContent(VideoContentEntity content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getCount_like() {
        return count_like;
    }

    public void setCount_like(String count_like) {
        this.count_like = count_like;
    }

    public String getCount_share() {
        return count_share;
    }

    public void setCount_share(String count_share) {
        this.count_share = count_share;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getShare_sub_title() {
        return share_sub_title;
    }

    public void setShare_sub_title(String share_sub_title) {
        this.share_sub_title = share_sub_title;
    }

    public String getWeibo_share_image() {
        return weibo_share_image;
    }

    public void setWeibo_share_image(String weibo_share_image) {
        this.weibo_share_image = weibo_share_image;
    }

}
