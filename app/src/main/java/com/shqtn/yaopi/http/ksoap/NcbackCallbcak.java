package com.shqtn.yaopi.http.ksoap;


import com.shqtn.yaopi.bean.ResultBean;
import com.shqtn.yaopi.utils.JsonUtils;
import com.shqtn.yaopi.utils.LogUtils;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

/**
 * Created by android on 2017/8/30.
 */

public abstract class NcbackCallbcak implements Callback<ResultBean.Ncback> {

    @Override
    public void onAfter() {

    }

    @Override
    public ResultBean.Ncback onResponse(SoapSerializationEnvelope response) throws Exception {
        SoapObject result = (SoapObject) response.bodyIn;
        String json = result.getProperty(0).toString();
        LogUtils.i("返回参数：" + json);
        ResultBean resultBean = JsonUtils.getObject(json, ResultBean.class);
        ResultBean.Ncback ncback = resultBean.getNcback();
        return ncback;
    }

    @Override
    public void onFailed(SoapSerializationEnvelope response, Exception e) {
        String errorHint = "";
        if (e instanceof TimeoutException) {
            //连接超时
            errorHint = "连接超时";
        } else if (e instanceof SocketTimeoutException) {
            //连接超时
            errorHint = "连接超时";
        } else if (e instanceof SocketException) {
            //连接异常
            errorHint = "连接异常";
        } else {
            //其他异常
            errorHint = "连接失败";
        }
        onFailed(errorHint, e);
    }

    public abstract void onFailed(String errorHint, Exception e);

    @Override
    public void onBefore() {

    }

}
