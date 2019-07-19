package com.lv.mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lv.libdialog.BottomPopupWindow;
import com.lv.libdialog.EditDialog;
import com.lv.libdialog.EnsureDialog;
import com.lv.libmvp.activity.BaseActivity;
import com.lv.libmvp.adapter.CommonViewHolder;
import com.lv.libscan.activity.CaptureActivity;
import com.lv.libutils.FontSetting;
import com.lv.mvp.R;
import com.lv.mvp.adapter.MultiTypeAdapter;
import com.lv.mvp.contract.MainContract;
import com.lv.mvp.model.HotCoinsResponse;
import com.lv.mvp.presenter.MainPresenter;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, CommonViewHolder.onItemCommonClickListener {

    private RecyclerView recycleview;
    private MultiTypeAdapter mAdapter;
    private List<HotCoinsResponse> mList = new ArrayList<>();
    private int REQUEST_CODE = 11;
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
