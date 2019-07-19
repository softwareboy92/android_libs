package com.lv.libmvp.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.lv.libmvp.R;
import com.lv.libmvp.contract.BaseContract;
import com.lv.libmvp.utils.ToastUtils;
import com.lv.libmvp.weight.DialogLoadding;
import com.trello.rxlifecycle2.LifecycleTransformer;


public abstract class RightDialog<P extends BaseContract.BasePresenter> extends DialogFragment implements BaseContract.BaseView {

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    protected View mView;
    protected Context mContext;
    protected P mPresenter;
    private DialogLoadding dialogLoadding;
    public final String TAG = getClass().getSimpleName();

    @Override
    public void show(FragmentManager manager, String tag) {
        if (manager == null) {
            return;
        }
        if (Integer.valueOf(Build.VERSION.SDK) > Build.VERSION_CODES.JELLY_BEAN) {
            if (manager.isDestroyed())
                return;
        }
        try {
            super.show(manager, tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置style
        setStyle(DialogFragment.STYLE_NORMAL, R.style.RightDialog);
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


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        getDialog().getWindow().setBackgroundDrawable(null);

    }


    /**
     * 初始化Presenter
     *
     * @return
     */
    protected abstract P initPresenter();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //去除标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        lp.height = dm.heightPixels;
        lp.width = (dm.widthPixels * 2) / 5;
        lp.gravity = Gravity.RIGHT; //底部
        window.setAttributes(lp);

        View view = createView(inflater, container);
        initSoftInputListener();
        return view;
    }


    //重写此方法，设置布局文件
    protected abstract View createView(LayoutInflater inflater, ViewGroup container);


    @Override
    public void onDestroy() {
        super.onDestroy();
        detachView();
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
    public void showLoading() {
        if (dialogLoadding == null) {
            dialogLoadding = new DialogLoadding(getActivity());
        }
        dialogLoadding.showDialog();
    }

    @Override
    public void hideLoading() {
        if (dialogLoadding != null)
            dialogLoadding.closeDialog();
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


    private void attachView() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }


    private void detachView() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.cancelAllRequest();
        }
    }


    /**
     * 点击非输入框区域时，自动收起键盘
     */
    private void initSoftInputListener() {
        getDialog().getWindow().getDecorView()
                .setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        InputMethodManager manager = (InputMethodManager) getActivity()
                                .getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            if (getDialog().getCurrentFocus() != null
                                    && getDialog().getCurrentFocus().getWindowToken() != null) {
                                manager.hideSoftInputFromWindow(
                                        getDialog().getCurrentFocus().getWindowToken(),
                                        InputMethodManager.HIDE_NOT_ALWAYS);
                            }
                        }
                        return false;
                    }
                });
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }
}