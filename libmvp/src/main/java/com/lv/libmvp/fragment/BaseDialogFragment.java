package com.lv.libmvp.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.lv.libmvp.contract.BaseContract;
import com.lv.libmvp.utils.ToastUtils;
import com.lv.libmvp.weight.DialogLoadding;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.RxDialogFragment;

import static com.lv.libmvp.utils.StateInfo.BROADCAST_ACTION_DISC;
import static com.lv.libmvp.utils.StateInfo.BROADCAST_PERMISSION_DISC;


/**
 * 作者：create by albert on 2018/11/11 5:18 PM
 * 邮箱：lvhzongdi@iclou.com
 */
public abstract class BaseDialogFragment<P extends BaseContract.BasePresenter> extends RxDialogFragment implements BaseContract.BaseView {

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";


    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    protected P mPresenter;

    public final String TAG = getClass().getSimpleName();

    private DialogLoadding mDialogLoadding;
    private BroadcastReceiver receiveBroadCast;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(RxDialogFragment.STYLE_NORMAL, getThemeStyle());
        mPresenter = initPresenter();
        attachView();
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = getGravity(); //底部
        window.setAttributes(lp);

        mView = inflater.inflate(getFragmentLayoutID(), container, false);
        mActivity = getActivity();
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContext = mActivity;
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //绑定
        initView(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置 dialog 的宽高
        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout((int) (dm.widthPixels * getWightPercent()), (int) (dm.heightPixels * getHeightPercent()));
        }
        //设置背景为空
        getDialog().getWindow().setBackgroundDrawable(null);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        detachView();
    }


    /**
     * 设置弹出的宽度百分比 0-1 之间的数字
     *
     * @return
     */
    protected abstract double getWightPercent();

    /**
     * 设置弹出的高度的百分比 0——1 之间的数字
     *
     * @return
     */
    protected abstract double getHeightPercent();

    private void initBroadCaseRevierce() {
        // 注册广播接收
        receiveBroadCast = new ReceiveBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BROADCAST_ACTION_DISC); // 只有持有相同的action的接受者才能接收此广播
        getActivity().registerReceiver(receiveBroadCast, filter, BROADCAST_PERMISSION_DISC, null);
    }

    /**
     * 设置弹出位置
     *
     * @return
     */
    protected abstract int getGravity();

    /**
     * 设置弹出动画样式
     *
     * @return
     */
    protected abstract int getThemeStyle();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    /**
     * 初始化Presenter
     *
     * @return
     */
    protected abstract P initPresenter();

    /**
     * 获取Fragment的LayoutID
     *
     * @return
     */
    protected abstract int getFragmentLayoutID();

    /**
     * 初始化View
     *
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);


    @Override
    public void showLoading() {
        if (mDialogLoadding==null){
            mDialogLoadding = new DialogLoadding(getActivity());
        }
        mDialogLoadding.showDialog();
    }

    @Override
    public void hideLoading() {
        if (mDialogLoadding != null) {
            mDialogLoadding.closeDialog();
        }
    }

    @Override
    public void showSuccess(String successMsg) {
        ToastUtils.getInstanc(getActivity()).showToast(successMsg);
    }

    @Override
    public void showFailed(String errorMsg) {
        ToastUtils.getInstanc(getActivity()).showToast(errorMsg);
    }

    @Override
    public void showNoNet() {
    }

    @Override
    public void onRetry() {
        ToastUtils.getInstanc(getActivity()).showToast("Retry");
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }


    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initBroadCaseRevierce();
    }


    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.cancelAllRequest();
        }
        if (receiveBroadCast != null)
            getActivity().unregisterReceiver(receiveBroadCast);

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
     * 所有的刷新数据操作都在这里面进行
     */
    protected abstract void refershDatas(Context context, Intent intent);
}
