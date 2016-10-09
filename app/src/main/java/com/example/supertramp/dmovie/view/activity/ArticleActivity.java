package com.example.supertramp.dmovie.view.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.supertramp.dmovie.R;
import com.example.supertramp.dmovie.base.BaseActivity;
import com.example.supertramp.dmovie.utils.Constants;
import com.example.supertramp.dmovie.view.custom.CustomWebView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by supertramp on 16/10/7.
 */
public class ArticleActivity extends BaseActivity {

    @Nullable
    @BindView(R.id.rl_tabview)
    RelativeLayout tabView;
    @Nullable
    @BindView(R.id.icon_back)
    TextView iconBack;
    @Nullable
    @BindView(R.id.tab_line)
    View line;
    @Nullable
    @BindView(R.id.webView)
    CustomWebView webView;

    private int tabHeight = 300;

    @Override
    protected int getLayoutId()
    {
        return R.layout.activity_article;
    }

    @Override
    protected void initView()
    {
        super.initView();
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        iconBack.setTypeface(iconfont);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDomStorageEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        if (Build.VERSION.SDK_INT >= 19)
        {
            settings.setLoadsImagesAutomatically(true);
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        else
        {
            settings.setLoadsImagesAutomatically(false);
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        settings.setBlockNetworkImage(true);//拦截图片的加载

    }

    @Override
    protected void initData()
    {

        super.initData();
        String id = getIntent().getStringExtra(Constants.INTENT.INTENT_ARTICLEID);
        webView.loadUrl(Constants.BASE_URL_ARTICLE_WEB + "?articleId=" + id);

    }

    @Override
    protected void initListener()
    {
        super.initListener();
        webView.setWebViewClient(new WebViewClient()
        {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                super.onPageStarted(view, url, favicon);
                view.loadUrl("javascript:function setTop(){document.getElementById('bottomBar').style.display=\"none\";}setTop();");
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:function setTop(){document.getElementById('bottomBar').style.display=\"none\";}setTop();");
                webView.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setBlockNetworkImage(false);
            }
        });

        webView.setOnScrollChangeCallback(new CustomWebView.OnScrollChangeCallback()
        {
            @Override
            public void onScroll(int x, int y, int dx, int dy)
            {
                if (y < tabHeight)
                {
                    float scale = (float) y / tabHeight;
                    float alpha = (255 * scale);
                    tabView.setBackgroundColor(Color.argb((int)alpha, 255, 255, 255));

                    line.setVisibility(View.GONE);
                    iconBack.setTextColor(getResources().getColor(R.color.white));
                }
                else if (y >= tabHeight && dy > 0)
                {
                    line.setVisibility(View.VISIBLE);
                    iconBack.setTextColor(getResources().getColor(R.color.black));
                }
            }
        });
    }

    @OnClick({R.id.rl_back})
    public void onClick(View view)
    {

        switch (view.getId())
        {
            case R.id.rl_back:
                finish();
                break;
            default:break;
        }

    }

}
