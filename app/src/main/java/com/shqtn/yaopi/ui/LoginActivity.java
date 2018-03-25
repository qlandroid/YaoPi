package com.shqtn.yaopi.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.shqtn.yaopi.BaseActivity;
import com.shqtn.yaopi.R;
import com.shqtn.yaopi.bean.ResultBean;
import com.shqtn.yaopi.bean.params.LoginParams;
import com.shqtn.yaopi.bean.params.Safety;
import com.shqtn.yaopi.bind.BindView;
import com.shqtn.yaopi.http.ksoap.KsoapModelService;
import com.shqtn.yaopi.http.ksoap.StringCallback;
import com.shqtn.yaopi.utils.JsonUtils;
import com.shqtn.yaopi.utils.ParamsFactory;
import com.shqtn.yaopi.utils.UserUtils;
import com.shqtn.yaopi.zxing.activity.CaptureActivity;
import com.zhy.m.permission.MPermissions;
import com.zhy.m.permission.PermissionDenied;
import com.zhy.m.permission.PermissionGrant;
import com.zhy.m.permission.ShowRequestPermissionRationale;

public class LoginActivity extends BaseActivity {

    private static final int REQUECT_CODE_SDCARD = 2;
    @BindView(R.id.activity_login_btn_change_ip)
    Button btnChangeIp;
    @BindView(R.id.activity_login_btn_scanning)
    Button btnScanning;
    private String mScanningCode;
    private boolean isDoingCode;

    @Override
    public void createView() {
        setContentView(R.layout.activity_login);
    }


    @Override
    public void initData() {
        super.initData();
        MPermissions.requestPermissions(this, REQUECT_CODE_SDCARD, Manifest.permission.CAMERA);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionGrant(REQUECT_CODE_SDCARD)
    public void requestSdcardSuccess() {
        //统一
        Toast.makeText(this, "获得所有权限成功", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied(REQUECT_CODE_SDCARD)
    public void requestSdcardFailed() {
        displayMsgDialog("启动相机权限是本程序必备的权限，请用户前往应用权限管理，赋予该权限");
    }

    @ShowRequestPermissionRationale(REQUECT_CODE_SDCARD)
    public void showdialog() {
        if (!MPermissions.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA, REQUECT_CODE_SDCARD)) {
            MPermissions.requestPermissions(this, REQUECT_CODE_SDCARD, Manifest.permission.CAMERA);
        }
    }

    @Override
    public void initWidget() {
        super.initWidget();
        btnChangeIp.setOnClickListener(this);
        btnScanning.setOnClickListener(this);

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.activity_login_btn_change_ip:
                startActivity(IpChangeActivity.class);
                break;
            case R.id.activity_login_btn_scanning:
                toScanningActivity();
                break;
            default:
        }
    }

    @Override
    public void resultDecode(String resultContent) {
        super.resultDecode(resultContent);
        toLogin(resultContent);
    }

    private void toLogin(String content) {
        if (isDoingCode) {
            return;
        }
        mScanningCode = content;
        displayLoadingDialog();
        LoginParams loginParams = new LoginParams();
        LoginParams.Item item = new LoginParams.Item();
        item.setCode(content);
        loginParams.setItem(item);
        Safety safety = ParamsFactory.createLoginSafety();

        loginParams.setSafety(safety);
        KsoapModelService.run(loginParams, new StringCallback() {
            @Override
            public void onReplaceResponse(String s) {
                ResultBean resultBean = JsonUtils.getObject(s, ResultBean.class);
                ResultBean.Ncback ncback = resultBean.getNcback();
                if (ncback == null) {
                    //失败
                    onFailed("登陆失败", null);
                    return;
                }

                if (!ResultBean.Ncback.RESULT_TAG_YES.equals(ncback.getResult())) {
                    onFailed(ncback.getErrormsg(), null);
                    return;
                }
                UserUtils.saveCode(LoginActivity.this, mScanningCode);
                startActivity(LoginSelectActivity.class);
                finish();
            }

            @Override
            protected void onFailed(String errorHint, Exception e) {
                toast(errorHint);
            }

            @Override
            public void onBefore() {
                isDoingCode = false;
                cancelLoadingDialog();
            }
        });
    }
}
