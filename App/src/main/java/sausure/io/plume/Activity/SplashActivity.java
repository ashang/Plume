package sausure.io.plume.Activity;

import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.widget.ImageView;

import butterknife.Bind;
import rx.Observable;
import sausure.io.personallibrary.Enum.TransitionMode;
import sausure.io.personallibrary.Utils.ActivityUtil;
import sausure.io.plume.Presenter.Presenter;
import sausure.io.plume.Presenter.SplashPresenter;
import sausure.io.plume.R;

/**
 * Created by JOJO on 2015/9/5.
 */
public class SplashActivity extends BaseActivity implements SplashPresenter.SplashView
{
    @Bind(R.id.start_img)
    protected ImageView startImageView;

    @Override
    protected int getLayoutResId()
    {
        return R.layout.activity_splash;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode()
    {
        return TransitionMode.FADE;
    }

    @Override
    protected int getStatusBarColor()
    {
        return 0;
    }

    @Override
    protected Presenter getPresenter() {
        return new SplashPresenter(activity,this);
    }

    @Override
    public void animateBackgroundImage(Animation animation)
    {
        startImageView.startAnimation(animation);
    }

    @Override
    public void initializeView(String copyright, Observable<Bitmap> observable)
    {
        observable.subscribe(startImageView::setImageBitmap);
    }

    @Override
    public void navigateToMainActivity()
    {
        ActivityUtil.readyGo(activity,MainActivity.class,true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK)
            return true;

        return super.onKeyDown(keyCode, event);
    }
}
