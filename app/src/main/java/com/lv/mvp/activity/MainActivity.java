package com.lv.mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lv.libdialog.BottomPopupWindow;
import com.lv.libdialog.EditDialog;
import com.lv.libdialog.EnsureDialog;
import com.lv.libimage.progress.CircleProgressView;
import com.lv.libimage.view.GlideImageView;
import com.lv.libmvp.activity.BaseActivity;
import com.lv.libmvp.adapter.CommonViewHolder;
import com.lv.libscan.activity.CaptureActivity;
import com.lv.libsqlite.utils.DatabaseUtils;
import com.lv.libutils.FontSetting;
import com.lv.mvp.R;
import com.lv.mvp.adapter.MultiTypeAdapter;
import com.lv.mvp.contract.MainContract;
import com.lv.mvp.model.HotCoinsResponse;
import com.lv.mvp.model.Persion;
import com.lv.mvp.presenter.MainPresenter;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, CommonViewHolder.onItemCommonClickListener {

    private RecyclerView recycleview;
    private MultiTypeAdapter mAdapter;
    private List<HotCoinsResponse> mList = new ArrayList<>();
    private int REQUEST_CODE = 11;

    private GlideImageView glide_view01;
    private GlideImageView glide_view02;
    private GlideImageView glide_view03;
    private GlideImageView glide_view04;
    private GlideImageView image31;
    private CircleProgressView progressView1;

    public static final String girl = "http://pic1.win4000.com/wallpaper/3/58b3bff670e11.jpg";
    public static final String girl_thumbnail = "http://pic1.win4000.com/wallpaper/3/58b3bff670e11.jpg";

    @Override
    protected void initView(Bundle savedInstanceState) {
        TextView show_font = findViewById(R.id.show_font);
        FontSetting.setFont(this, show_font, "fonts/苹方黑体-中粗-简.ttf");
        findViewById(R.id.scan_camare).setOnClickListener(view -> {
            //启动相机。然后返回相机扫描结果
            Intent intent = new Intent(this, CaptureActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        });
        findViewById(R.id.bottom).setOnClickListener(v -> {
            mPresenter.getVersion(this);
        });
        findViewById(R.id.hotcoins).setOnClickListener(view -> {
            mPresenter.getHotCoins(this);
        });
        findViewById(R.id.custom_dialog_01).setOnClickListener(view -> {
            EnsureDialog dialog = new EnsureDialog(this);
            dialog.builder();
            dialog.setCancelable(false);
            dialog.setCenterButton("确定", view1 -> {
                Toast.makeText(this, "点击了？", Toast.LENGTH_SHORT).show();
            });
            dialog.setTitle("提示");
            dialog.setSubTitle("点击测试？");
            dialog.show();
        });
        findViewById(R.id.custom_dialog_02).setOnClickListener(view -> {
            BottomPopupWindow bottomPopupWindow = new BottomPopupWindow(this);
            bottomPopupWindow.builder();
            bottomPopupWindow.addSheetItem("打开相机", Color.parseColor("#FF00FF"), (which) -> {

            });
            bottomPopupWindow.addSheetItem("打开相册", Color.parseColor("#FF00FF"), (which) -> {

            });
            bottomPopupWindow.setCancelView("取消", Color.parseColor("#FF00FF"));
            bottomPopupWindow.show();
        });
        findViewById(R.id.custom_dialog_03).setOnClickListener(view -> {
            EditDialog editDialog
                    = new EditDialog(this);
            editDialog.builder();
            editDialog.setCancelable(false);
            editDialog.setHitText("请输入点什么吧？");
            editDialog.setInputType(InputType.TYPE_CLASS_TEXT);
            editDialog.setNegativeButton("取消", view1 -> {

            });
            editDialog.setPositiveButton("确定", view1 -> {
                Toast.makeText(this, editDialog.getMsg(), Toast.LENGTH_SHORT).show();
            });
            editDialog.setTitle("提示");
            editDialog.show();
        });
        recycleview = findViewById(R.id.recycleview);
        recycleview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MultiTypeAdapter(this, mList, this);
        recycleview.setAdapter(mAdapter);
        mPresenter.getHotCoins(this);
        progressView1 = findViewById(R.id.progressView1);
        image31 = findViewById(R.id.image31);
        glide_view01 = findViewById(R.id.glide_view01);
        glide_view02 = findViewById(R.id.glide_view02);
        glide_view03 = findViewById(R.id.glide_view03);
        glide_view04 = findViewById(R.id.glide_view04);

        glide_view01.enableState(true).load("http://pic1.win4000.com/wallpaper/3/58b3bff670e11.jpg");
        glide_view02.loadCircle("http://pic1.win4000.com/wallpaper/3/58b3bff670e11.jpg");
        glide_view03.load("http://pic1.win4000.com/wallpaper/3/58b3bff670e11.jpg", R.mipmap.ic_launcher_round);
        glide_view04.fitCenter().load("http://pic1.win4000.com/wallpaper/3/58b3bff670e11.jpg", R.mipmap.ic_launcher_round, 10);

        image31.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MvpActivity.class);
            intent.putExtra(MvpActivity.KEY_IMAGE_URL, girl);
            intent.putExtra(MvpActivity.KEY_IMAGE_URL_THUMBNAIL, girl_thumbnail);
            ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, image31, getString(R.string.transitional_image));
            ActivityCompat.startActivity(MainActivity.this, intent, compat.toBundle());
        });

        image31.centerCrop()
                .error(R.mipmap.image_load_err)
                .load("http://pic1.win4000.com/wallpaper/3/58b3bff670e11.jpg", R.color.colorAccent,
                        (isComplete, percentage, bytesRead, totalBytes) -> {
                            if (isComplete) {
                                progressView1.setVisibility(View.GONE);
                            } else {
                                progressView1.setVisibility(View.VISIBLE);
                                progressView1.setProgress(percentage);
                            }
                        });

        //todo 数据库创建及测试
        DatabaseUtils.initHelper(this, "user.db");
        //String userName, int sex, int age, String brithDay
        Persion persion = new Persion("张三",0,18,"2019年07月26日16:40:36");
        DatabaseUtils.getHelper().save(persion);
        findViewById(R.id.database_quary).setOnClickListener(view -> {
            List<Persion> persions = DatabaseUtils.getHelper().queryAll(Persion.class);
            for (Persion persion1 : persions) {
                Log.e("lvlvlvl", "initView: "+ persion1.toString());
            }
        });



    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getActivityLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void refershDatas(Context context, Intent intent) {

    }

    @Override
    public void onSuccess(List<HotCoinsResponse> mList) {
        this.mList.addAll(mList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClickListener(int position) {

    }

    @Override
    public void onItemLongClickListener(int position) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra("extra_string");
                Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
