package com.shqtn.yaopi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shqtn.yaopi.bind.BindViewUtils;

/**
 * 创建时间:2017/12/21
 * 描述:
 *
 * @author ql
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private long click_last_time;
    private static final long MIN_CLICK_DOUBLE = 300; //过滤 连续点击

    private OnClickBackListener mOnClickBackListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        int viewID = createView();
        View view = inflater.inflate(viewID, container, false);
        return view;
    }

    public abstract int createView();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initData();
        BindViewUtils.find(this, view);
        View back = view.findViewById(R.id.back);
        if (back != null) {
            back.setOnClickListener(this);
        }
        View quit = view.findViewById(R.id.quit);
        if (quit != null) {
            quit.setOnClickListener(this);
        }
        initWidget(view);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.quit) {
            mOnClickBackListener.onClickQuit();
        } else if (v.getId() == R.id.back) {
            mOnClickBackListener.onClickBack();
        } else {
            long clickTime = System.currentTimeMillis();
            if (clickTime - click_last_time > MIN_CLICK_DOUBLE) {
                clickWidget(v);
                click_last_time = clickTime;
            }
        }
    }

    public void clickWidget(View v) {

    }

    public void initWidget(View view) {

    }

    public void initData() {

    }

    public void setOnClickBackListener(OnClickBackListener onClickBackListener) {
        this.mOnClickBackListener = onClickBackListener;
    }
}
