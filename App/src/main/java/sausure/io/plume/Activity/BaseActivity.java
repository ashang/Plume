package sausure.io.plume.Activity;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import rx.Observable;
import sausure.io.personallibrary.Activity.BaseAppCompatActivity;
import sausure.io.plume.Presenter.Presenter;
import sausure.io.plume.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by JOJO on 2015/9/5.
 */
public abstract class BaseActivity extends BaseAppCompatActivity
{
    /**
     * Activity Tool Bar
     */
    @Nullable
    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    protected Presenter presenter;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onActivityCreated()
    {
        if(toolbar != null)
        {
            setSupportActionBar(toolbar);

            if(canNaviBack())
                Observable.just(getSupportActionBar())
                        .filter(actionBar -> actionBar != null)
                        .doOnNext(actionBar -> actionBar.setHomeButtonEnabled(true))
                        .subscribe(actionBar ->actionBar.setDisplayHomeAsUpEnabled(true));
        }

        if(presenter == null)
            presenter = getPresenter();

        if(presenter == null)
            throw new IllegalArgumentException("you must provide a Presenter implement");
        else
            presenter.initialized();
    }

    @Override
    protected int getStatusBarColor()
    {
        return getResources().getColor(R.color.primary_dark);
    }

    /**
     * this activity can finish by navigation
     * @return
     */
    protected boolean canNaviBack()
    {
        return false;
    }

    protected abstract Presenter getPresenter();
}
