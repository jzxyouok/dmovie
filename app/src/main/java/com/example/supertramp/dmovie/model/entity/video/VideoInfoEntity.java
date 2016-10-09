package com.example.supertramp.dmovie.model.entity.video;

/**
 * Created by supertramp on 16/7/21.
 */
public class VideoInfoEntity {

    private String image;
    private String title;
    private String duration;
    private String filesize;
    private String source_link;
    private String qiniu_url;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getSource_link() {
        return source_link;
    }

    public void setSource_link(String source_link) {
        this.source_link = source_link;
    }

    public String getQiniu_url() {
        return qiniu_url;
    }

    public void setQiniu_url(String qiniu_url) {
        this.qiniu_url = qiniu_url;
    }

}
