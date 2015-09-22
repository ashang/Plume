package sausure.io.plume.Activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import sausure.io.plume.Presenter.Presenter;
import sausure.io.plume.Presenter.ViewDetailPresenter;
import sausure.io.plume.R;
import sausure.io.plume.Retrofit.Entity.ViewPoint;

/**
 * Created by JOJO on 2015/9/17.
 */
public class ViewDetailActivity extends BaseActivity implements ViewDetailPresenter.ViewDetailView
{
    public static String VIEW_DETAIL = "VIEW_DETAIL";

    private ViewPoint viewPoint;

    @Bind(R.id.image)
    protected ImageView imageView;

    @Bind(R.id.collapsing_toolbar)
    protected CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.web_view)
    protected WebView webView;

    @Override
    protected void onActivityCreated() {
        super.onActivityCreated();
        Picasso.with(activity).load(viewPoint.getImages().get(0)).into(imageView);
        collapsingToolbarLayout.setTitle(viewPoint.getTitle());
    }

    @Override
    protected Presenter getPresenter()
    {
        return new ViewDetailPresenter(activity,this);
    }

    @Override
    protected int getLayoutResId()
    {
        return R.layout.activity_view_detail;
    }

    @Override
    protected void handleBundleExtras(Bundle extras)
    {
        viewPoint = (ViewPoint) extras.getSerializable(VIEW_DETAIL);
    }

    @Override
    protected boolean canNaviBack() {
        return true;
    }

    @Override
    public ViewPoint getViewPoint() {
        return viewPoint;
    }

    @Override
    public void refreshImage(String imageUrl) {
        Picasso.with(activity).load(imageUrl).into(imageView);
    }

    @Override
    public void initializeWebView(String html) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }
}