package com.shqtn.yaopi;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.shqtn.yaopi.bind.BindViewUtils;
import com.shqtn.yaopi.ui.widget.TitleView;
import com.shqtn.yaopi.utils.ActivityUtils;
import com.shqtn.yaopi.utils.ToastUtils;
import com.shqtn.yaopi.view.IDialog;
import com.shqtn.yaopi.zxing.activity.CaptureActivity;

/**
 * 创建时间:2017/12/20
 * 描述:
 *
 * @author ql
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener, TitleView.OnClickToBackListener, IDialog, OnClickBackListener {
    public static final int REQUEST_CODE_DECODE = 16;
    private static final int REQUEST_CODE_ASK_CAMERA = 0x33;
    public TitleView mTitleView;


    private QMUITipDialog loadingDialog;
    private AlertDialog msgDialog;
    private BaseFragment currentKJFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createView();
        ActivityUtils.getInstance().addAty(this);
        BindViewUtils.find(this);

        View viewById = findViewById(R.id.titleView);
        if (viewById != null) {
            mTitleView = (TitleView) viewById;
            mTitleView.setOnToBackClickListener(this);
        }
        initData();
        initWidget();
    }

    /**
     * 用于初始化布局
     */
    public abstract void createView();

    public void initData() {
    }


    public void initWidget() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.getInstance().removeAty(this);
    }

    public void startActivity(Class aty) {
        Intent intent = new Intent(this, aty);
        startActivity(intent);
    }

    public void startActivity(Class aty, int requestCode) {
        Intent intent = new Intent(this, aty);
        startActivityForResult(intent, requestCode);
    }

    public void startActivity(Class aty, Bundle bundle) {
        Intent intent = new Intent(this, aty);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        widgetClick(v);

    }

    public Bundle getBundle() {
        Intent intent = getIntent();
        if (intent == null) {
            return null;
        }
        return intent.getExtras();
    }

    public void widgetClick(View v) {

    }

    @Override
    public void clickBack() {
        onBackPressed();
    }

    public void toScanningActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    /**
                     * 这个API主要用于给用户一个申请权限的解释，
                     * 该方法只有在用户在上一次已经拒绝过你的这个权限申请。
                     * 也就是说，用户已经拒绝一次了，
                     * 你又弹个授权框，你需要给用户一个解释，为什么要授权，则使用该方法。
                     */
                    ToastUtils.show(this, "没有权限");
                } else {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_ASK_CAMERA);
                }
                return;
            } else {
                startActivity(CaptureActivity.class, REQUEST_CODE_DECODE);
            }
        } else {
            startActivity(CaptureActivity.class, REQUEST_CODE_DECODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_CODE_ASK_CAMERA:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    toScanningActivity();
                } else {
                    ToastUtils.show(this, "请到系统设置中，给予当前APP权限");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_DECODE) {
            if (resultCode == Activity.RESULT_OK) {
                resultDecode(CaptureActivity.getResultContent(data));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void resultDecode(String resultContent) {


    }

    @Override
    public void displayLoadingDialog() {
        displayLoadingDialog("");
    }

    @Override
    public void cancelLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog.cancel();
        }
    }

    @Override
    public void displayLoadingDialog(String msg) {
        if (loadingDialog == null) {
            loadingDialog = new QMUITipDialog.Builder(this)
                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                    .create();
        }
        loadingDialog.setTipWord(msg);
        loadingDialog.show();
    }

    @Override
    public void displayMsgDialog(String title, String msg) {
        if (msgDialog == null) {
            msgDialog = new AlertDialog.Builder(this)
                    .create();
        }
        msgDialog.setMessage(msg);
        msgDialog.setTitle(title);
        msgDialog.show();
    }

    @Override
    public void displayMsgDialog(String msg) {
        displayMsgDialog("", msg);
    }

    @Override
    public void cancelMsgDialog() {
        msgDialog.cancel();
    }

    public void toast(String msg) {
        ToastUtils.show(this, msg
        );
    }


    public void changeFragment(int fm_content, BaseFragment targetFragment) {
        if (targetFragment == null) {
            return;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction.add(fm_content, targetFragment, targetFragment.getClass().getName());
        }
        if (currentKJFragment != null && currentKJFragment.isAdded() && !currentKJFragment.isHidden()) {
            transaction.hide(currentKJFragment);
        }
        currentKJFragment = targetFragment;

        transaction.show(targetFragment).commit();
    }

    @Override
    public void onClickBack() {
        onBackPressed();
    }

    @Override
    public void onClickQuit() {
        ActivityUtils.getInstance().quit();
    }
}
