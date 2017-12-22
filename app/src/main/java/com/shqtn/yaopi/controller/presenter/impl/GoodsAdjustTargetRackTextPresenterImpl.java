package com.shqtn.yaopi.controller.presenter.impl;

import android.os.Bundle;

import com.shqtn.yaopi.C;
import com.shqtn.yaopi.bean.ResultBean;
import com.shqtn.yaopi.controller.presenter.GetRackTargetItemParamsImpl;
import com.shqtn.yaopi.controller.presenter.TextPresenterBean;
import com.shqtn.yaopi.ui.ScanningBoxActivity;
import com.shqtn.yaopi.ui.ScanningTextActivity;
import com.shqtn.yaopi.utils.ParamsFactory;

/**
 * 创建时间:2017/12/22
 * 描述:
 *
 * @author ql
 */

public class GoodsAdjustTargetRackTextPresenterImpl extends NormalTextPresenterImpl {
    public GoodsAdjustTargetRackTextPresenterImpl() {
        super();
        TextPresenterBean bean = new TextPresenterBean("货位调整", "请扫描调入货位条码",
                ScanningTextActivity.class, GoodsAdjustRackTextPresenterImpl.class, ParamsFactory.GoodsAdjust.createScanningManfiest()
                , null);
        setTextPresenterBean(bean);

        setGetItemParams(new GetRackTargetItemParamsImpl());
    }


    @Override
    public void putBundle(Bundle bundle, ResultBean resultBean, String scanningCode) {
        bundle.putString(C.RACK_NO_TARGET, scanningCode);
        ScanningBoxActivity.putPresenter(GoodsAdjustAddBoxPresenterImpl.class, bundle);
    }
}
