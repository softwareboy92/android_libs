package com.lv.mvp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.lv.libmvp.adapter.BaseListAdapter;
import com.lv.mvp.R;
import com.lv.mvp.model.ListViewModel;

import java.util.List;

/**
 * 作者：created by albert on 2019-07-19 14:37
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class ListViewAdapter extends BaseListAdapter<ListViewModel> {


    public ListViewAdapter(Context context) {
        super(context);
    }

    public ListViewAdapter(Context context, List<ListViewModel> list) {
        super(context, list);
    }

    @Override
    public int getContentView() {
        return R.layout.item_01;
    }

    @Override
    public void onInitView(View view, int position) {
        TextView tv_title = view.findViewById(R.id.tv_title);
        TextView tv_content = view.findViewById(R.id.tv_content);

        ListViewModel model = getList().get(position);

        tv_title.setText(model.getTitle());
        tv_content.setText(model.getContract());
    }
}
