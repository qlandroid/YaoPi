package com.shqtn.yaopi.controller.presenter.impl;

import android.os.Bundle;

import com.shqtn.yaopi.bean.ResultBean;
import com.shqtn.yaopi.bean.params.Safety;
import com.shqtn.yaopi.controller.presenter.AbsScanningTextPresenter;
import com.shqtn.yaopi.controller.presenter.TextPresenterBean;
import com.shqtn.yaopi.ui.ScanningBoxActivity;
import com.shqtn.yaopi.utils.ParamsFactory;

/**
 * 创建时间:2017/12/21
 * 描述:
 *
 * @author ql
 */

public class NormalTextPresenterImpl extends AbsScanningTextPresenter {
    private TextPresenterBean textPresenterBean;

    public NormalTextPresenterImpl() {

    }

    public void setTextPresenterBean(TextPresenterBean textPresenterBean) {
        this.textPresenterBean = textPresenterBean;
    }


    @Override
    public void init() {
        getView().setTitle(textPresenterBean.getTitle());
        getView().setText(textPresenterBean.getContent());
    }

    @Override
    public void putBundle(Bundle bundle, ResultBean resultBean, String scanningCode) {
        super.putBundle(bundle, resultBean, scanningCode);
        if (textPresenterBean != null) {
            ScanningBoxActivity.put(textPresenterBean.getOperateBoxBean(), bundle);
        }
    }

    @Override
    protected Class getToActivityPresenter() {
        return textPresenterBean.getPresenter();
    }

    @Override
    protected Class getToActivity() {
        return textPresenterBean.getToActivity();
    }

    @Override
    public Safety getScanningManifest() {
        return textPresenterBean.getSafety();
    }


}
