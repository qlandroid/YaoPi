package com.shqtn.yaopi.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.shqtn.yaopi.App;
import com.shqtn.yaopi.BaseActivity;
import com.shqtn.yaopi.R;
import com.shqtn.yaopi.bind.BindView;
import com.shqtn.yaopi.utils.IpChangeUtils;
import com.shqtn.yaopi.utils.StringUtils;

public class IpChangeActivity extends BaseActivity {

    @BindView(R.id.activity_ip_change_et_input_ip)
    EditText etIp;
    @BindView(R.id.activity_ip_change_et_input_port)
    EditText etPort;
    @BindView(R.id.activity_ip_change_btn_yes)
    Button btnYes;
    private QMUITipDialog tipDialog;

    @Override
    public void createView() {
        setContentView(R.layout.activity_ip_change);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        btnYes.setOnClickListener(this);
        tipDialog = new QMUITipDialog.Builder(this).setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                .setTipWord("请设置ip").create();
        String ip = IpChangeUtils.getIp(this);
        String port = IpChangeUtils.getPort(this);
        if (!StringUtils.isEmpty(ip)) {
            etIp.setText(ip);
        }

        if (!StringUtils.isEmpty(port)) {
            etPort.setText(port);
        }
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.activity_ip_change_btn_yes:
                String ip = etIp.getText().toString();
                String port = etPort.getText().toString();
                if (TextUtils.isEmpty(ip)) {

                    tipDialog.setTipWord("请设置Ip");
                    tipDialog.show();
                    App.getInstance().handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tipDialog.cancel();
                        }
                    }, 1000);
                    return;
                }
                if (TextUtils.isEmpty(port)) {
                    tipDialog.setTipWord("请设置端口号");
                    tipDialog.show();
                    App.getInstance().handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tipDialog.cancel();
                        }
                    }, 1000);
                    return;
                }

                IpChangeUtils.savePost(this, port);
                IpChangeUtils.saveIp(this, ip);
                finish();
                break;
            default:
        }
    }
}
