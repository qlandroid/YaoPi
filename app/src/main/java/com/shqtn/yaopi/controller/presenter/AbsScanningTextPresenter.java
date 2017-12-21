package com.shqtn.yaopi.controller.presenter;

import android.os.Bundle;

import com.shqtn.yaopi.C;
import com.shqtn.yaopi.bean.ResultBean;
import com.shqtn.yaopi.bean.params.Item;
import com.shqtn.yaopi.bean.params.Safety;
import com.shqtn.yaopi.bean.params.SubmitParams;
import com.shqtn.yaopi.controller.ScanningTextController;
import com.shqtn.yaopi.http.ksoap.KsoapModelService;
import com.shqtn.yaopi.http.ksoap.StringCallback;
import com.shqtn.yaopi.utils.JsonUtils;

/**
 * 创建时间:2017/12/21
 * 描述:
 *
 * @author ql
 */

public abstract class AbsScanningTextPresenter implements ScanningTextController.IPresenter {

    private Bundle bundle;
    private ScanningTextController.IView iView;
    private String mScanningManifest;
    private boolean isSubmiting;

    @Override
    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public void setView(ScanningTextController.IView view) {
        this.iView = view;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public ScanningTextController.IView getView() {
        return iView;
    }


    @Override
    public void resultCode(String code) {
        getView().displayLoadingDialog("查询中");
        Safety manifestSafety = getScanningManifest();
        SubmitParams params = new SubmitParams();
        params.setSafety(manifestSafety);
        Item item = new Item();
        item.setBillcode(code);
        params.setItem(item);
        mScanningManifest = code;

        KsoapModelService.run(params, new StringCallback() {
            @Override
            protected void onFailed(String error, Exception e) {
                getView().displayMsgDialog(error);
            }

            @Override
            public void onReplaceResponse(String s) {
                ResultBean result = JsonUtils.getObject(s, ResultBean.class);

                if (!ResultBean.Ncback.RESULT_TAG_YES.equals(result.getNcback().getResult())) {
                    onFailed(result.getNcback().getErrormsg(), null);
                    return;
                }


                onSuccessAfter(result);

            }


            @Override
            public void onAfter() {
                super.onAfter();
                isSubmiting = false;
                getView().cancelLoadingDialog();
            }
        });

    }

    private void onSuccessAfter(ResultBean result) {
        Bundle bundle = new Bundle();
        putBundle(bundle, result, mScanningManifest);

        putPresenter(getToActivityPresenter(), bundle);

        getView().toActivity(getToActivity(), bundle);
    }

    private void putPresenter(Class addBoxPresenter, Bundle bundle) {
        bundle.putString(C.BUNDLE_PRESENTER_NAME, addBoxPresenter.getCanonicalName());
    }

    /**
     * 将数据保存到bundle 进行 页面之间的信息传递
     *
     * @param bundle
     * @param resultBean
     * @param scanningCode
     */
    public void putBundle(Bundle bundle, ResultBean resultBean, String scanningCode) {
        bundle.putString(C.DECODE, scanningCode);
        bundle.putString(C.DEOCDE_QTY, resultBean.getNcback().getQuantity());
    }


    protected abstract Class getToActivityPresenter();

    protected abstract Class getToActivity();

    abstract public Safety getScanningManifest();


}
