package com.shqtn.yaopi.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shqtn.yaopi.BaseActivity;
import com.shqtn.yaopi.R;
import com.shqtn.yaopi.bind.BindView;
import com.shqtn.yaopi.controller.ScanningTextController;
import com.shqtn.yaopi.controller.presenter.TextPresenterBean;
import com.shqtn.yaopi.controller.presenter.impl.NormalTextPresenterImpl;

/**
 * 功能性扫描入口
 * 如提示扫描某任务单号
 */
public class ScanningTextActivity extends BaseActivity implements ScanningTextController.IView {
    public static final String PRESENTER_NAME = "presenterName";

    public static final String SHOW_TAG = "showTag";

    public static void put(TextPresenterBean bean, Bundle b) {
        b.putParcelable(SHOW_TAG, bean);
    }

    public static void put(Class presenter, Bundle b) {
        b.putString(PRESENTER_NAME, presenter.getCanonicalName());
    }

    @BindView(R.id.activity_scanning_text_tv)
    TextView tv;

    private ScanningTextController.IPresenter mPresenter;

    @Override
    public void createView() {
        setContentView(R.layout.activity_scanning_text);
    }

    @Override
    public void initData() {
        super.initData();
        Bundle bundle = getBundle();
        TextPresenterBean bean = bundle.getParcelable(SHOW_TAG);
        if (bean == null) {
            String presenterName = bundle.getString(PRESENTER_NAME);
            ClassLoader classLoader = ScanningTextController.IPresenter.class.getClassLoader();
            try {
                Class<?> aClass = classLoader.loadClass(presenterName);
                mPresenter = (ScanningTextController.IPresenter) aClass.newInstance();
                mPresenter.setView(this);
                mPresenter.setBundle(getBundle());

                mPresenter.init();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            NormalTextPresenterImpl p = new NormalTextPresenterImpl();
            p.setTextPresenterBean(bean);
            mPresenter = p;
        }
        mPresenter.setView(this);
        mPresenter.setBundle(getBundle());

        mPresenter.init();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        tv.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.activity_scanning_text_tv:
                toScanningActivity();
                break;
            default:
        }
    }

    @Override
    public void resultDecode(String resultContent) {
        super.resultDecode(resultContent);
        mPresenter.resultCode(resultContent);
    }

    @Override
    public void setTitle(String title) {
        mTitleView.setTitle(title);
    }

    @Override
    public void setText(String text) {
        tv.setText(text);
    }

    @Override
    public void toActivity(Class aty, Bundle bundle) {
        startActivity(aty, bundle);
    }

    @Override
    public void closeActivity() {
        finish();
    }
}
