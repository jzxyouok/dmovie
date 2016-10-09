package com.example.supertramp.dmovie.presenter;

import android.content.Context;
import com.example.supertramp.dmovie.R;
import com.example.supertramp.dmovie.base.BasePresenter;
import com.example.supertramp.dmovie.model.api.VideoAPI;
import com.example.supertramp.dmovie.model.entity.BaseCallEntity;
import com.example.supertramp.dmovie.model.entity.home.BriefVideoEntity;
import com.example.supertramp.dmovie.model.entity.home.CategoryEntity;
import com.example.supertramp.dmovie.orm.DatabaseHelper;
import com.example.supertramp.dmovie.orm.bean.BriefVideo;
import com.example.supertramp.dmovie.utils.ImageUtils;
import com.example.supertramp.dmovie.view.fragment.VideoTabContract;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by supertramp on 16/7/22.
 */
public class VideoTabPresenter extends BasePresenter implements VideoTabContract.Presenter {

    private Context context;
    private int pageIndex = 1;
    private boolean hasData = true;
    private VideoTabContract.View view;

    public VideoTabPresenter(Context context, VideoTabContract.View view)
    {
        this.context = context;
        this.view = view;
    }

    @Override
    public void getVideoList() {

        if (!hasData)
        {
            showToast(context, R.string.video_none);
            return ;
        }

        VideoAPI.getVideoList(String.valueOf(pageIndex), "10", "latest", new Callback<BaseCallEntity<List<BriefVideoEntity>>>()
        {
            @Override
            public void onResponse(Call<BaseCallEntity<List<BriefVideoEntity>>> call, Response<BaseCallEntity<List<BriefVideoEntity>>> response) {

                List<BriefVideoEntity> list = response.body().getData();

                if (list == null || list.size() == 0)
                {
                    hasData = false;
                    showToast(context, R.string.video_none);
                }
                view.updateList(list);
                updateSQLite(list);
                pageIndex ++;

            }

            @Override
            public void onFailure(Call<BaseCallEntity<List<BriefVideoEntity>>> call, Throwable t) {

                try{

                    if (pageIndex != 1)
                    {
                        view.updateList(null);
                        return;
                    }
                    List<BriefVideo> dbList = DatabaseHelper.getHelper(context).getBvDao().queryForAll();
                    int size = dbList.size();
                    BriefVideoEntity entity;
                    List<CategoryEntity> cates;
                    List<BriefVideoEntity> list = new ArrayList<BriefVideoEntity>();

                    for (int i = 0;i < size;i ++)
                    {
                        cates = new ArrayList<CategoryEntity>();
                        CategoryEntity catentity = new CategoryEntity();
                        catentity.setCatename(dbList.get(i).getCatename());
                        cates.add(catentity);
                        entity = new BriefVideoEntity(dbList.get(i).getPostid(), dbList.get(i).getTitle(), dbList.get(i).getImage(), dbList.get(i).getDuration(),cates);
                        list.add(entity);
                    }
                    view.updateList(list);

                }catch (SQLException e)
                {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void updateSQLite(List<BriefVideoEntity> list) {

        if (pageIndex != 1)
        {
            return;
        }
        DatabaseHelper helper = DatabaseHelper.getHelper(context);

        try {

            BriefVideo video;
            int size = list.size();
            if (helper.getBvDao().queryForAll().size() == 10)
            {
                for (int i = 0; i < size; i++)
                {
                    BriefVideoEntity entity = list.get(i);
                    video = new BriefVideo(i + 1, entity.getPostid(), entity.getTitle(), ImageUtils.getUriFromUrl(entity.getImage()), entity.getDuration(), entity.getCates().get(0).getCatename());
                    helper.getBvDao().update(video);
                }
            }
            else
            {
                for (int i = 0; i < size; i++)
                {
                    BriefVideoEntity entity = list.get(i);
                    video = new BriefVideo(entity.getPostid(), entity.getTitle(), ImageUtils.getUriFromUrl(entity.getImage()), entity.getDuration(), entity.getCates().get(0).getCatename());
                    helper.getBvDao().createIfNotExists(video);
                }
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
