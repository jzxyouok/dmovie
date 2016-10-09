package com.example.supertramp.dmovie.utils;

/**
 * Created by supertramp on 16/6/28.
 */
public class Constants {

    public static String BASE_URL_VIDEO = "http://app.vmoiver.com/";
    public static String BASE_URL_ARTICLE = "http://metis.tvmore.com.cn/metis_interface/";
    public static String BASE_URL_MUSIC = "http://graph.luoo.net/";

    public static String BASE_URL_ARTICLE_WEB = "http://portal.moretv.com.cn/mportal/metis/server/v1.3/index.html";

    public interface INTENT {

        public static String INTENT_POSTID = "intent_postid";
        public static String INTENT_ARTICLEID = "intent_articleid";

    }

    public interface ActionConstant {

        int ACTION_BACK = 1;
        int ACTION_PLAY = 2;
        int ACTION_PAUSE = 3;
        int ACTION_FULL = 4;

    }

}
