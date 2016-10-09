package com.example.supertramp.dmovie.view.activity;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.supertramp.dmovie.R;
import com.example.supertramp.dmovie.adapter.DividerLine;
import com.example.supertramp.dmovie.adapter.listener.OnItemClickListener;
import com.example.supertramp.dmovie.adapter.video.SearchVideoAdapter;
import com.example.supertramp.dmovie.base.BaseActivity;
import com.example.supertramp.dmovie.model.entity.home.BriefVideoEntity;
import com.example.supertramp.dmovie.presenter.SearchPresenter;
import com.example.supertramp.dmovie.utils.ActivityUtils;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by supertramp on 16/7/23.
 */
public class SearchActivity extends BaseActivity implements SearchContract.View {

    @Nullable
    @BindView(R.id.rl_cancel)
    RelativeLayout rlCancel;
    @Nullable
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @Nullable
    @BindView(R.id.rv_search)
    RecyclerView rvSearch;

    private boolean isLoading;
    private SearchPresenter presenter;
    private List<BriefVideoEntity> videos;
    private SearchVideoAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    protected void initData()
    {
        presenter = new SearchPresenter(this, this);
    }

    @Override
    protected void initListener()
    {
        super.initListener();
        etKeyword.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    if (!isLoading)
                    {
                        search();
                    }
                }
                return true;
            }
        });

        rvSearch.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
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

    @OnClick({R.id.rl_cancel})
    protected void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.rl_cancel:
                finish();
                break;
            default:break;
        }
    }

    private void search()
    {
        presenter.getSearchVideos();
    }

    @Override
    public String getKeyword() {
        return String.valueOf(etKeyword.getText());
    }

    @Override
    public void setKeyword(String keyword) {

        etKeyword.setText(keyword);

    }

    @Override
    public void updateList(final List<BriefVideoEntity> list)
    {

        isLoading = false;
        if (adapter == null)
        {
            adapter = new SearchVideoAdapter(this, list);
            DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
            dividerLine.setSize(1);
            dividerLine.setColor(getResources().getColor(R.color.black_1));
            rvSearch.addItemDecoration(dividerLine);
            rvSearch.setLayoutManager(new LinearLayoutManager(this));
            rvSearch.setAdapter(adapter);

            adapter.setOnItemClickListener(new OnItemClickListener()
            {
                @Override
                public void onItemClick(View view, int position)
                {
                    ActivityUtils.nextVideoActivity(SearchActivity.this, list.get(position).getPostid());
                }
            });
        }
        else
        {
            adapter.addList(list);
        }

    }

    private void loadMore()
    {
        isLoading = true;
        presenter.getSearchVideos();
    }

}
