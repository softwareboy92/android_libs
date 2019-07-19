package com.lv.libmvp.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.lv.libmvp.contract.BaseContract;
import com.lv.libmvp.languageutils.MultiLanguageUtil;
import com.lv.libmvp.languageutils.OnChangeLanguageEvent;
import com.lv.libmvp.utils.AppManager;
import com.lv.libmvp.utils.ToastUtils;
import com.lv.libmvp.weight.DialogLoadding;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.lv.libmvp.utils.StateInfo.BROADCAST_ACTION_DISC;
import static com.lv.libmvp.utils.StateInfo.BROADCAST_PERMISSION_DISC;


/**
 * 作者：create by albert on 2018/11/11 5:18 PM
 * 邮箱：lvhzongdi@iclou.com
 */
public abstract class BaseActivity<P extends BaseContract.BasePresenter>
        extends RxAppCompatActivity implements BaseContract.BaseView {


    protected Activity mContext;
    protected P mPresenter;
    private DialogLoadding dialogLoadding;

    private BroadcastReceiver receiveBroadCast;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.addActivity(this);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(getActivityLayoutID());
        //绑定 Activity
        //设置语言
        MultiLanguageUtil.init(this);
        MultiLanguageUtil.getInstance().setConfiguration();
        //强制竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initBroadCaseRevierce();
        mPresenter = initPresenter();
        mContext = this;
        attachView();
        initView(savedInstanceState);
    }


    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiveBroadCast != null)
            unregisterReceiver(receiveBroadCast);
        detachView();
    }

    private void initBroadCaseRevierce() {
        // 注册广播接收
        receiveBroadCast = new ReceiveBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BROADCAST_ACTION_DISC); // 只有持有相同的action的接受者才能接收此广播
        registerReceiver(receiveBroadCast, filter, BROADCAST_PERMISSION_DISC, null);
    }

    /**
     * 挂载view
     */
    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 卸载view
     */
    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.cancelAllRequest();
        }
    }

    /**
     * 初始化View
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 在子View中初始化Presenter
     *
     * @return
     */
    protected abstract P initPresenter();

    /**
     * 设置Activity的布局ID
     *
     * @retur
     */
    protected abstract int getActivityLayoutID();

    @Override
    public void showLoading() {
        if (dialogLoadding==null){
            dialogLoadding = new DialogLoadding(this);
        }
        dialogLoadding.showDialog();
    }

    @Override
    public void hideLoading() {
        if (dialogLoadding != null)
            dialogLoadding.closeDialog();
    }

    @Override
    public void showSuccess(String message) {
        ToastUtils.getInstanc(this).showToast(message);
    }

    @Override
    public void showFailed(String message) {
        ToastUtils.getInstanc(this).showToast(message);
    }

    @Override
    public void showNoNet() {
        ToastUtils.getInstanc(this).showToast("无网络");
    }

    @Override
    public void onRetry() {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(newBase));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangeLanguageEvent(OnChangeLanguageEvent event) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        MultiLanguageUtil.getInstance().setConfiguration();
    }

    /**
     * 注册动态广播
     */
    public class ReceiveBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BROADCAST_ACTION_DISC)) {
                refershDatas(context, intent);
            }
        }

    }

    /**
     * 刷新操作都在这里面进行
     */
    protected abstract void refershDatas(Context context, Intent intent);
}