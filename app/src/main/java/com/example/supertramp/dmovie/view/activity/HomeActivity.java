package com.example.supertramp.dmovie.view.activity;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.example.supertramp.dmovie.R;
import com.example.supertramp.dmovie.base.BaseFragmentActivity;
import com.example.supertramp.dmovie.utils.ActivityUtils;
import com.example.supertramp.dmovie.view.fragment.ArticleFragment;
import com.example.supertramp.dmovie.view.fragment.VideoFragment;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by supertramp on 16/7/21.
 */
public class HomeActivity extends BaseFragmentActivity {

    @Nullable
    @BindView(R.id.vp_home)
    ViewPager viewPager;

    private List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        super.initView();
        fragments.add(new VideoFragment());
        fragments.add(new ArticleFragment());
        initViewpager();
    }

    @Override
    protected void initListener() {
        super.initListener();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0)
                {
                    tvVideo.setTextColor(getResources().getColor(R.color.white));
                    tvVideoIndicator.setVisibility(View.VISIBLE);
                    tvArticle.setTextColor(getResources().getColor(R.color.gray));
                    tvArticleIndicator.setVisibility(View.GONE);
                    ivSearch.setVisibility(View.VISIBLE);
                }
                else
                {
                    tvVideo.setTextColor(getResources().getColor(R.color.gray));
                    tvVideoIndicator.setVisibility(View.GONE);
                    tvArticle.setTextColor(getResources().getColor(R.color.white));
                    tvArticleIndicator.setVisibility(View.VISIBLE);
                    ivSearch.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViewpager()
    {
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
    }

    @OnClick({R.id.iv_search, R.id.tv_title_video, R.id.tv_title_article})
    protected void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.iv_search:
                ActivityUtils.nextActivity(this, SearchActivity.class);
                break;
            case R.id.tv_title_video:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_title_article:
                viewPager.setCurrentItem(1);
                break;
            default:break;
        }
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }

}
