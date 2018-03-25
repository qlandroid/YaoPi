package com.shqtn.yaopi.controller.presenter;

import android.os.Bundle;

import com.shqtn.yaopi.App;
import com.shqtn.yaopi.C;
import com.shqtn.yaopi.bean.ResultBean;
import com.shqtn.yaopi.bean.params.Item;
import com.shqtn.yaopi.bean.params.ItemValues;
import com.shqtn.yaopi.bean.params.Psn;
import com.shqtn.yaopi.bean.params.Safety;
import com.shqtn.yaopi.bean.params.SubmitParams;
import com.shqtn.yaopi.controller.AddBoxController;
import com.shqtn.yaopi.http.ksoap.Callback;
import com.shqtn.yaopi.http.ksoap.KsoapModelService;
import com.shqtn.yaopi.http.ksoap.StringCallback;
import com.shqtn.yaopi.ui.fragment.BoxListFragment;
import com.shqtn.yaopi.utils.JsonUtils;
import com.shqtn.yaopi.utils.ParamsFactory;
import com.shqtn.yaopi.utils.ResultUtils;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * 创建时间:2017/12/21
 * 描述:
 *
 * @author ql
 */

public abstract class AbsScanningBoxPresenter implements AddBoxController.IPresenter {
    public static final int SHOW_TEXT = 0x222;
    public static final int SHOW_LIST = 0X333;
    public int showTag = SHOW_TEXT;

    private AddBoxController.IView mView;
    private Bundle bundle;
    private SubmitParams params;
    public List<BoxListFragment.Item> mBoxList = new ArrayList<>();
    private Item addBoxParams;
    private Callback mAddBoxCallback;
    public boolean isSubmiting;
    private Item mScanningRackParams;


    public AddBoxController.IView getView() {
        return mView;
    }

    public Bundle getBundle() {
        return bundle;
    }

    @Override
    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public void setView(AddBoxController.IView view) {
        this.mView = view;
    }


    public void decodeBox(String code) {
        if (params == null) {
            params = new SubmitParams();
            Safety s = getScanningBoxNoSafety();
            params.setSafety(s);
            addBoxParams = new Item();

            setAddBoxItemParams(addBoxParams);
            params.setItem(addBoxParams);

        }

        addBoxParams.setBoxno(code);

        if (mAddBoxCallback == null) {
            mAddBoxCallback = createAddBoxCallback();
        }
        if (isSubmiting) {
            return;
        }
        getView().displayLoadingDialog("解码中");
        isSubmiting = true;
        KsoapModelService.run(params, mAddBoxCallback);
    }

    public void setAddBoxItemParams(Item addBoxParams) {
        /*if (rackAddBox) {
            String string = getBundle().getString(C.MANIFEST_NO);
            this.addBoxParams.setRackno(string);
        } else {*/
        String string = getBundle().getString(C.MANIFEST_NO);
        this.addBoxParams.setBillcode(string);
    }

    private Callback createAddBoxCallback() {
        return new StringCallback() {
            @Override
            protected void onFailed(String error, Exception e) {
                getView().displayMsgDialog(error);
            }

            @Override
            public void onReplaceResponse(String s) {
                ResultBean result = JsonUtils.getObject(s, ResultBean.class);
                ResultBean.Ncback ncback = result.getNcback();
                if (ncback == null) {
                    onFailed("返回失败", null);
                    return;
                }
                boolean isSuccess = ResultUtils.isSuccess(ncback);
                if (!isSuccess) {
                    onFailed(ncback.getErrormsg(), null);
                    return;
                }
                for (BoxListFragment.Item s1 : mBoxList) {
                    if (s1.boxNo.equals(addBoxParams.getBoxno())) {
                        getView().displayMsgDialog("不能重复添加:" + addBoxParams.getBoxno());
                        return;
                    }
                }
                BoxListFragment.Item item = new BoxListFragment.Item();
                item.boxNo = addBoxParams.getBoxno();
                //添加成功
                mBoxList.add(item);

                getView().setAddBoxSize(mBoxList.size());
                getView().updateList(mBoxList);
                if (mBoxList.size() == 0) {
                    getView().changeTextFragment();
                } else {
                    getView().changeAddBoxFragment();
                }
            }

            @Override
            public void onAfter() {
                super.onAfter();
                isSubmiting = false;
                getView().cancelLoadingDialog();
            }
        };
    }


    /**
     * 用于解码 区域编码
     *
     * @param code
     */
    public void decodeRack(String code) {

        Safety saveRackSafety = getRackSafety();
        SubmitParams params = new SubmitParams();
        params.setSafety(saveRackSafety);
        mScanningRackParams = new Item();
        mScanningRackParams.setBillcode(getBundle().getString(C.MANIFEST_NO));

        mScanningRackParams.setRackno(code);
        params.setItem(mScanningRackParams);
        KsoapModelService.run(params, new StringCallback() {
            @Override
            protected void onFailed(String error, Exception e) {
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
                decodeRackSuccess();
            }

            @Override
            public void onAfter() {
                super.onAfter();
                getView().cancelLoadingDialog();
            }
        });
    }

    /**
     * 解码货位成功的回调
     */
    public void decodeRackSuccess() {

    }

    /**
     * 用于提交箱子。
     */
    public void submit() {
        if (isSubmiting) {
            return;
        }
        isSubmiting = true;
        SubmitParams params = new SubmitParams();

        Safety submitSafety = getSubmitSafety();
        params.setSafety(submitSafety);

        Psn psn = ParamsFactory.getPsn(App.getInstance());
        params.setPsn(psn);
        /*
         * 用于判断当前是否扫描了货位，如果扫描到了货位需要提交货位信息
         */
        if (mScanningRackParams == null) {
            mScanningRackParams = new Item();
            mScanningRackParams.setBillcode(getBundle().getString(C.MANIFEST_NO));
        }
        params.setItem(mScanningRackParams);

        ArrayList<ItemValues> boxList = new ArrayList<>();
        ArrayList<String> addBox = new ArrayList<>();
        for (BoxListFragment.Item item : mBoxList) {
            addBox.add(item.boxNo);
        }
        for (String s : addBox) {
            ItemValues box = new ItemValues();
            box.setValue(s);
            boxList.add(box);
        }

        params.setBoxno(boxList);

        KsoapModelService.run(params, new StringCallback() {


            @Override
            public void onFailed(String errorHint, Exception e) {
                getView().setTextFragmentContent(errorHint);
            }

            @Override
            public void onAfter() {
                super.onAfter();
                isSubmiting = false;
                getView().cancelLoadingDialog();
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
                submitSuccess();

            }
        });
    }

    public void submitSuccess() {

    }


    public abstract Safety getScanningBoxNoSafety();

    @Override
    public void init() {

    }

    public Safety getSubmitSafety() {
        return null;
    }

    public Safety getRackSafety() {
        return null;
    }
}
