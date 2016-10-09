package com.example.supertramp.dmovie.view.fragment;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.supertramp.dmovie.R;
import com.example.supertramp.dmovie.adapter.DividerLine;
import com.example.supertramp.dmovie.adapter.article.ArticleAdapter;
import com.example.supertramp.dmovie.model.entity.article.ArticleEntity;
import com.example.supertramp.dmovie.presenter.ArticleTabPresenter;
import com.example.supertramp.dmovie.utils.ActivityUtils;
import java.util.List;
import butterknife.BindView;

/**
 * Created by supertramp on 16/10/7.
 */
public class ArticleFragment extends BaseFragment implements ArticleTabContract.View {

    @Nullable
    @BindView(R.id.sl_home)
    SwipeRefreshLayout slHome;
    @Nullable
    @BindView(R.id.rv_home)
    RecyclerView rvHome;

    private final int SHOWTIME = 2000;
    private boolean isLoading;
    private ArticleTabPresenter presenter;
    private ArticleAdapter adapter;
    private Handler handler = new Handler();

    @Override
    public void initView() {
        super.initView();
        presenter = new ArticleTabPresenter(getActivity(), this);
    }

    @Override
    public void initData() {
        super.initData();
        presenter.getArticleList();
    }

    @Override
    public void initListener() {
        super.initListener();
        slHome.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                presenter.getArticleList();
                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        slHome.setRefreshing(false);
                    }
                }, SHOWTIME);
            }
        });

        rvHome.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int lastVisibleItem = manager.findLastVisibleItemPosition();
                int totalItemCount = manager.getItemCount();

                if (lastVisibleItem >= totalItemCount - 4 && dy > 0)
                {
                    if (!isLoading)
                    {
                        loadMore();
                    }
                }
            }
        });
    }

    @Override
    public void updateList(List<ArticleEntity> list)
    {

        if (list != null && list.size() > 0)
        {
            if (adapter == null)
            {
                adapter = new ArticleAdapter(getActivity(), list);
                DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
                dividerLine.setSize(2);
                dividerLine.setColor(getResources().getColor(R.color.white));
                rvHome.addItemDecoration(dividerLine);
                rvHome.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvHome.setAdapter(adapter);

                adapter.setOnItemClickListener(new ArticleAdapter.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(View view, int position)
                    {
                        ActivityUtils.nextArticleActivity(getContext(), adapter.getList().get(position).getArticle_sid());
                    }
                });

            }
            else
            {
                adapter.addList(list);
            }
        }

        isLoading = false;
    }

    @Override
    protected int getLayoutID()
    {
        return R.layout.fragment_new;
    }

    @Override
    public void loadMore() {
        isLoading = true;
        presenter.getArticleList();
    }

}
