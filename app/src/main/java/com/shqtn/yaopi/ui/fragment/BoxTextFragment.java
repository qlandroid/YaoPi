package com.shqtn.yaopi.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shqtn.yaopi.BaseFragment;
import com.shqtn.yaopi.OnClickBackListener;
import com.shqtn.yaopi.R;
import com.shqtn.yaopi.bind.BindView;

/**
 * 创建时间:2017/12/21
 * 描述:
 *
 * @author ql
 */

public class BoxTextFragment extends BaseFragment {

    @BindView(R.id.frag_box_text_tv)
    TextView tv;

    private boolean isCreate;
    private String content;

    public static BoxTextFragment newInstance(OnClickBackListener l) {

        Bundle args = new Bundle();

        BoxTextFragment fragment = new BoxTextFragment();
        fragment.setOnClickBackListener(l);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int createView() {
        return R.layout.frag_box_text;
    }

    @Override
    public void initWidget(View view) {
        super.initWidget(view);
        if (content != null) {
            tv.setText(content);
        }
        isCreate = true;
    }

    public void setContent(String content) {
        if (isCreate) {
            tv.setText(content);
        }
        this.content = content;
    }
}
