package com.shqtn.yaopi.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.shqtn.yaopi.BaseFragment;
import com.shqtn.yaopi.OnClickBackListener;
import com.shqtn.yaopi.R;
import com.shqtn.yaopi.bind.BindView;
import com.shqtn.yaopi.ui.adapter.CommonAdapter;

import java.util.List;

/**
 * 创建时间:2017/12/21
 * 描述:
 *
 * @author ql
 */

public class BoxListFragment extends BaseFragment {
    @BindView(R.id.frag_box_list_lv)
    ListView lv;
    @BindView(R.id.frag_box_list_btn)
    Button btn;

    CommonAdapter<Item> mBoxAddItemAdapter;
    private OnClickBoxButtonListener onClickBoxButtonListener;

    public static BoxListFragment newInstance(OnClickBackListener l, OnClickBoxButtonListener boxL) {

        Bundle args = new Bundle();

        BoxListFragment fragment = new BoxListFragment();
        fragment.setOnClickBackListener(l);
        fragment.setOnClickBoxButtonListener(boxL);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int createView() {
        return R.layout.frag_box_list;
    }

    @Override
    public void initData() {
        super.initData();
        mBoxAddItemAdapter = new CommonAdapter<Item>(getContext(), null, R.layout.item_add_box) {
            @Override
            public void setItemContent(ViewHolder holder, Item item, int position) {
                holder.setText(R.id.item_add_box_tv_batchNo, item.batchNo)
                        .setText(R.id.item_add_box_tv_box, item.boxNo)
                        .setText(R.id.item_add_box_tv_QB, item.qb);
            }
        };
    }

    @Override
    public void initWidget(View view) {
        lv.setAdapter(mBoxAddItemAdapter);
        btn.setOnClickListener(this);
    }

    public void update(List<Item> list) {
        mBoxAddItemAdapter.update(list);
    }

    public void setButton(String name) {
        btn.setText(name);
    }

    @Override
    public void clickWidget(View v) {
        super.clickWidget(v);
        if (v.getId() == R.id.frag_box_list_btn) {
            onClickBoxButtonListener.onClickBoxButton();
        }
    }

    public void setOnClickBoxButtonListener(OnClickBoxButtonListener onClickBoxButtonListener) {
        this.onClickBoxButtonListener = onClickBoxButtonListener;
    }

    public interface OnClickBoxButtonListener {
        void onClickBoxButton();
    }

    public static class Item {
        public String boxNo;
        public String batchNo;
        public String qb;//无聊
    }
}
