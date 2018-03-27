package com.shqtn.yaopi.controller.presenter.impl;

import com.shqtn.yaopi.C;
import com.shqtn.yaopi.bean.ResultBean;
import com.shqtn.yaopi.bean.params.Item;
import com.shqtn.yaopi.bean.params.ItemValues;
import com.shqtn.yaopi.bean.params.Safety;
import com.shqtn.yaopi.bean.params.SubmitParams;
import com.shqtn.yaopi.controller.AddBoxController;
import com.shqtn.yaopi.controller.presenter.AbsScanningBoxPresenter;
import com.shqtn.yaopi.http.ksoap.KsoapModelService;
import com.shqtn.yaopi.http.ksoap.StringCallback;
import com.shqtn.yaopi.ui.fragment.BoxListFragment;
import com.shqtn.yaopi.utils.JsonUtils;
import com.shqtn.yaopi.utils.ParamsFactory;
import com.shqtn.yaopi.utils.ResultUtils;

import java.util.ArrayList;

/**
 * 创建时间:2017/12/22
 * 描述:
 *
 * @author ql
 */

public class GoodsAdjustAddBoxPresenterImpl extends AbsScanningBoxPresenter {


    @Override
    public void init() {
        super.init();
        AddBoxController.IView view = getView();
        view.setAddFragmentButton("提交");
        view.setManifestLabel("货位");
        String rackNo = getBundle().getString(C.RACK_NO);
        String targetRackNo = getBundle().getString(C.RACK_NO_TARGET);
        view.setManifestNo(String.format("%s \r\n->\r\n%s", rackNo, targetRackNo));
    }

    @Override
    public Safety getScanningBoxNoSafety() {
        return ParamsFactory.GoodsAdjust.createScanningBoxNo();
    }

    @Override
    public void clickAddFragmentButton() {
        if (mBoxList.size() <= 0) {
            getView().displayMsgDialog("请添加箱子");
            return;
        }
        getView().displayLoadingDialog("提交中");
        //点击跳转扫描货位
        ArrayList<ItemValues> list = new ArrayList<>();
        for (BoxListFragment.Item item : mBoxList) {
            ItemValues iValues = new ItemValues();
            iValues.setValue(item.boxNo);
            list.add(iValues);
        }

        Safety saveRackSafety = ParamsFactory.GoodsAdjust.createSubmit();
        SubmitParams params = new SubmitParams();
        params.setSafety(saveRackSafety);
        Item mScanningRackParams = new Item();

        mScanningRackParams.setLocation(getBundle().getString(C.RACK_NO));
        mScanningRackParams.setLocation2(getBundle().getString(C.RACK_NO_TARGET));

        params.setItem(mScanningRackParams);

        params.setBoxno(list);


        KsoapModelService.run(params, new StringCallback() {
            @Override
            protected void onFailed(String error, Exception e) {
                getView().cancelLoadingDialog();
                getView().displayMsgDialog(error);
            }

            @Override
            public void onReplaceResponse(String s) {
                ResultBean result = JsonUtils.getObject(s, ResultBean.class);
                ResultBean.Ncback ncback = result.getNcback();
                if (ncback == null) {
                    onFailed("返回参数错误", null);
                    return;
                }
                if (!ResultUtils.isSuccess(ncback)) {
                    onFailed(ncback.getErrormsg(), null);
                    return;
                }
                getView().toast("提交成功");
                getView().closeActivity();
            }

            @Override
            public void onAfter() {
                super.onAfter();
                isSubmiting = false;
                getView().cancelLoadingDialog();
            }
        });
    }

    @Override
    public void resultCode(String code) {
        decodeBoxByRack(code);
    }
}
