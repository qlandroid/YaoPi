package com.shqtn.yaopi.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.shqtn.yaopi.BaseActivity;
import com.shqtn.yaopi.R;
import com.shqtn.yaopi.bean.ResultBean;
import com.shqtn.yaopi.bean.params.LoginParams;
import com.shqtn.yaopi.bean.params.Safety;
import com.shqtn.yaopi.bind.BindView;
import com.shqtn.yaopi.http.ksoap.KsoapModelService;
import com.shqtn.yaopi.http.ksoap.StringCallback;
import com.shqtn.yaopi.ui.adapter.CommonAdapter;
import com.shqtn.yaopi.ui.widget.TitleView;
import com.shqtn.yaopi.utils.JsonUtils;
import com.shqtn.yaopi.utils.ParamsFactory;
import com.shqtn.yaopi.utils.UserUtils;

import java.util.ArrayList;

public class LoginSelectActivity extends BaseActivity {

    @BindView(R.id.titleView)
    TitleView titleView;
    @BindView(R.id.login_select_lv_clazz)
    ListView lvClazz;
    @BindView(R.id.login_select_lv_date)
    ListView lvDate;
    @BindView(R.id.login_select_lv_team)
    ListView lvTeam;
    @BindView(R.id.login_select_btn_submit)
    Button btnSubmit;
    private CommonAdapter<ResultBean.Values> mDateAdapter;
    private CommonAdapter<ResultBean.Values> mClazzAdapter;
    private CommonAdapter<ResultBean.Values> mTeamAdapter;
    private ArrayList<ResultBean.Values> mDateList;
    private ArrayList<ResultBean.Values> mClazzList;
    private ArrayList<ResultBean.Values> mTeamList;

    private String mSelectDate;
    private String mSelectClazz;
    private String mSelectTeam;

    @Override
    public void createView() {
        setContentView(R.layout.activity_login_select);
    }

    @Override
    public void initData() {

        mDateAdapter = new CommonAdapter<ResultBean.Values>(this, null, R.layout.item_tv) {
            @Override
            public void setItemContent(ViewHolder holder, ResultBean.Values s, int position) {
                holder.setText(R.id.item_tv, s.getValue());
            }
        };
        mClazzAdapter = new CommonAdapter<ResultBean.Values>(this, null, R.layout.item_tv) {
            @Override
            public void setItemContent(ViewHolder holder, ResultBean.Values s, int position) {
                holder.setText(R.id.item_tv, s.getValue());
            }

        };
        mTeamAdapter = new CommonAdapter<ResultBean.Values>(this, null, R.layout.item_tv) {
            @Override
            public void setItemContent(ViewHolder holder, ResultBean.Values s, int position) {
                holder.setText(R.id.item_tv, s.getValue());
            }
        };
    }

    @Override
    public void initWidget() {
        titleView.setOnToBackClickListener(new TitleView.OnClickToBackListener() {
            @Override
            public void clickBack() {
                startActivity(LoginActivity.class);
                finish();
            }
        });

        btnSubmit.setOnClickListener(this);
        lvClazz.setAdapter(mClazzAdapter);
        lvDate.setAdapter(mDateAdapter);
        lvTeam.setAdapter(mTeamAdapter);

        lvClazz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSelectClazz = mClazzList.get(i).getValue();
                view.setSelected(true);
            }
        });
        lvDate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSelectDate = mDateList.get(i).getValue();
                view.setSelected(true);
            }
        });
        lvTeam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSelectTeam = mTeamList.get(i).getValue();
                view.setSelected(true);
            }
        });

        displayLoadingDialog();

        String loginCode = UserUtils.getCode(this);
        LoginParams loginParams = new LoginParams();
        LoginParams.Item item = new LoginParams.Item();
        item.setCode(loginCode);
        loginParams.setItem(item);
        Safety safety = ParamsFactory.createLoginSafety();

        loginParams.setSafety(safety);
        KsoapModelService.run(loginParams, new StringCallback() {
            @Override
            protected void onFailed(String error, Exception e) {
                cancelLoadingDialog();
                toast(error);
            }

            @Override
            public void onReplaceResponse(String s) {
                cancelLoadingDialog();
                ResultBean ncback = JsonUtils.getObject(s, ResultBean.class);
                if (ncback == null) {
                    toast("返回参数不正确");
                    return;
                }
                if (!"Y".equals(ncback.getNcback().getResult())) {
                    //登陆失败
                    toast(ncback.getNcback().getErrormsg());
                    return;
                }

                mTeamList = ncback.getNcback().getListTeam();
                mDateList = ncback.getNcback().getListData();
                mClazzList = ncback.getNcback().getListClazz();


                mDateAdapter.update(mDateList);
                mTeamAdapter.update(mTeamList);
                mClazzAdapter.update(mClazzList);
                if (!isEmpty(mClazzList)) {
                    lvClazz.setSelection(0);
                    mSelectClazz = mClazzList.get(0).getValue();
                }
                if (!isEmpty(mTeamList)) {
                    lvTeam.setSelection(0);
                    mSelectTeam = mTeamList.get(0).getValue();
                }
                if (!isEmpty(mDateList)) {
                    lvDate.setSelection(0);
                    mSelectDate = mDateList.get(0).getValue();
                }
            }
        });
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.login_select_btn_submit:
                clickSubmit();
                break;
            default:
        }
    }

    private void clickSubmit() {
        UserUtils.saveClazz(this, mSelectClazz);
        UserUtils.saveTeam(this, mSelectTeam);
        // startActivity(FunctionActivity.class);
        finish();
    }

    private boolean isEmpty(ArrayList<ResultBean.Values> mClazzList) {
        if (mClazzList == null || mClazzList.size() == 0) {
            return true;
        }
        return false;
    }

}
