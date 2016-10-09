package com.example.supertramp.dmovie.orm.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by supertramp on 16/7/23.
 */
@DatabaseTable(tableName = "tb_video_brief")
public class BriefVideo {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "postid")
    private String postid;
    @DatabaseField(columnName = "title")
    private String title;
    @DatabaseField(columnName = "image")
    private String image;
    @DatabaseField(columnName = "duration")
    private String duration;
    @DatabaseField(columnName = "catename")
    private String catename;

    public BriefVideo(){}

    public BriefVideo(String postid, String title, String image, String duration, String catename)
    {
        this.postid = postid;
        this.title = title;
        this.image = image;
        this.duration = duration;
        this.catename = catename;
    }

    public BriefVideo(int id, String postid, String title, String image, String duration, String catename)
    {
        this.id = id;
        this.postid = postid;
        this.title = title;
        this.image = image;
        this.duration = duration;
        this.catename = catename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getCatename()
    {
        return catename;
    }

    public void setCatename(String catename)
    {
        this.catename = catename;
    }
}
