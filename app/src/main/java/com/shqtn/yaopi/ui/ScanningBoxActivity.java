package com.shqtn.yaopi.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import com.shqtn.yaopi.BaseActivity;
import com.shqtn.yaopi.C;
import com.shqtn.yaopi.R;
import com.shqtn.yaopi.bind.BindView;
import com.shqtn.yaopi.controller.AddBoxController;
import com.shqtn.yaopi.controller.presenter.OperateBoxBean;
import com.shqtn.yaopi.controller.presenter.impl.NormalScanningBoxPresenter;
import com.shqtn.yaopi.ui.fragment.BoxListFragment;
import com.shqtn.yaopi.ui.fragment.BoxTextFragment;
import com.shqtn.yaopi.ui.widget.LabelTextView;

import java.util.List;

/**
 * 添加箱子页面
 * 包含文字Fragment ,添加箱子的Fragment;
 */
public class ScanningBoxActivity extends BaseActivity implements BoxListFragment.OnClickBoxButtonListener, AddBoxController.IView {
    public static final String NORMAL = "normal";
    @BindView(R.id.activity_scanning_box_ltv_add_size)
    LabelTextView ltvAddSize;
    @BindView(R.id.activity_scanning_box_ltv_box_size)
    LabelTextView ltvBoxSize;
    @BindView(R.id.activity_scanning_box_ltv_manifest)
    LabelTextView ltvManifest;
    @BindView(R.id.activity_scanning_box_btn_scanning)
    Button btnScanning;

    public static void put(OperateBoxBean bean, Bundle bundle) {
        bundle.putParcelable(NORMAL, bean);
    }

    public static void putPresenter(Class presenter, Bundle bundle) {
        bundle.putString(C.BUNDLE_PRESENTER_NAME, presenter.getCanonicalName());
    }

    private BoxTextFragment mBoxTextFragment;
    private BoxListFragment mBoxListFragment;

    private AddBoxController.IPresenter mPresenter;


    @Override
    public void createView() {
        setContentView(R.layout.activity_scanning_box);
    }

    @Override
    public void initData() {
        super.initData();
        mBoxTextFragment = BoxTextFragment.newInstance(this);
        mBoxListFragment = BoxListFragment.newInstance(this, this);
        OperateBoxBean bean = getBundle().getParcelable(NORMAL);
        if (bean == null) {
            String presenterName = getBundle().getString(C.BUNDLE_PRESENTER_NAME);
            try {
                mPresenter = (AddBoxController.IPresenter) Class.forName(presenterName).newInstance();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            mPresenter = new NormalScanningBoxPresenter(bean);

        }

        mPresenter.setBundle(getBundle());
        mPresenter.setView(this);
        mPresenter.init();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        btnScanning.setOnClickListener(this);
        changeFragment(R.id.activity_scanning_box_fl_content, mBoxTextFragment);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        int id = v.getId();
        if (id == R.id.activity_scanning_box_btn_scanning) {
            toScanningActivity();
        }

    }


    @Override
    public void setManifestNo(String manifestNo) {
        ltvManifest.setText(manifestNo);
    }

    @Override
    public void setManifestLabel(String manifestLabel) {
        ltvManifest.setLabelText(manifestLabel);
    }

    @Override
    public void setBoxSize(int boxSize) {
        ltvBoxSize.setText(String.valueOf(boxSize));
    }

    @Override
    public void setAddBoxSize(int addSize) {
        ltvAddSize.setText(String.valueOf(addSize));
    }

    @Override
    public void updateList(List<BoxListFragment.Item> list) {
        mBoxListFragment.update(list);
    }

    @Override
    public void setTitle(String title) {
        mTitleView.setTitle(title);
    }

    @Override
    public void changeAddBoxFragment() {
        changeFragment(R.id.activity_scanning_box_fl_content, mBoxListFragment);
    }

    @Override
    public void changeTextFragment() {
        changeFragment(R.id.activity_scanning_box_fl_content, mBoxTextFragment);
    }

    @Override
    public void setTextFragmentContent(String content) {
        mBoxTextFragment.setContent(content);
    }

    @Override
    public void setAddFragmentButton(String name) {
        mBoxListFragment.setButton(name);
    }

    @Override
    public void resultDecode(String resultContent) {
        mPresenter.resultCode(resultContent);
    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public void onClickBoxButton() {
        mPresenter.clickAddFragmentButton();
    }
}
