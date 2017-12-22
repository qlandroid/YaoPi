package com.shqtn.yaopi.controller.presenter.impl;

import android.os.Bundle;

import com.shqtn.yaopi.C;
import com.shqtn.yaopi.bean.params.Safety;
import com.shqtn.yaopi.controller.presenter.AbsScanningBoxPresenter;
import com.shqtn.yaopi.controller.presenter.OperateBoxBean;

/**
 * 创建时间:2017/12/21
 * 描述:
 *
 * @author ql
 */

public class NormalScanningBoxPresenter extends AbsScanningBoxPresenter {
    public static final int SHOW_RACK = 0x444;
    OperateBoxBean bean;

    public NormalScanningBoxPresenter() {
    }

    public NormalScanningBoxPresenter(OperateBoxBean bean) {
        this.bean = bean;
    }

    @Override
    public void resultCode(String code) {
        if (showTag == SHOW_LIST || showTag == SHOW_TEXT) {
            decodeBox(code);
        } else if (showTag == SHOW_RACK) {
            //扫描库位
            decodeRack(code);
        }
    }

    @Override
    public void decodeRackSuccess() {
        getView().displayLoadingDialog("正在提交");
        submit();
    }

    @Override
    public void submitSuccess() {
        getView().displayMsgDialog("提交成功");
    }

    @Override
    public void init() {
        super.init();
        Bundle bundle = getBundle();
        String manifestNo = bundle.getString(C.DECODE);
        getView().setManifestNo(manifestNo);
        String qty = bundle.getString(C.DECODE_QTY);
        getView().setBoxSize(Integer.parseInt(qty));
        if (bean.isScanningRack()) {
            getView().setAddFragmentButton("扫描库位");
        } else {
            getView().setAddFragmentButton("提交");
        }

    }



    @Override
    public void clickAddFragmentButton() {
        if (bean.isScanningRack()) {
            getView().changeTextFragment();
            getView().setTextFragmentContent("请扫描库位");
            showTag = SHOW_RACK;
        } else {
            getView().changeTextFragment();
            getView().setTextFragmentContent("正在提交");
            submit();
        }
    }


    @Override
    public Safety getScanningBoxNoSafety() {
        return bean.getAddBoxSafety();
    }

    @Override
    public Safety getRackSafety() {
        return bean.getRackSafety();
    }

    @Override
    public Safety getSubmitSafety() {
        return bean.getSubmitSafety();
    }
}
