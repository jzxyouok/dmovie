package com.example.supertramp.dmovie.model.entity.home;

/**
 * Created by supertramp on 16/7/20.
 */
public class BannerEntity {

    private String bannerid;
    private String title;
    private String image;
    private String description;
    private String addtime;
    private String extra;
    private String end_time;
    private BannerExtraEntity extra_data;

    public String getBannerid() {
        return bannerid;
    }

    public void setBannerid(String bannerid) {
        this.bannerid = bannerid;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public BannerExtraEntity getExtra_data() {
        return extra_data;
    }

    public void setExtra_data(BannerExtraEntity extra_data) {
        this.extra_data = extra_data;
    }
}
