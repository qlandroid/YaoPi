package com.shqtn.yaopi.http.ksoap;


import com.shqtn.yaopi.utils.LogUtils;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeoutException;

/**
 * Created by android on 2017/8/28.
 */

public abstract class StringCallback implements Callback<String> {
    @Override
    public void onAfter() {

    }

    @Override
    public String onResponse(SoapSerializationEnvelope response) throws Exception {
        SoapObject result = (SoapObject) response.bodyIn;
        String s;
        if (result.getPropertyCount() >0) {
            s = result.getProperty(0).toString();

        }else {
            s = "";
        }

        LogUtils.i("返回参数: " + s);
        return s;
    }
    @Override
    public void onFailed(SoapSerializationEnvelope response, Exception e) {
        String errorHint = "";
        if(e instanceof TimeoutException){
            //连接超时
            errorHint = "连接超时";
        }else if (e instanceof SocketTimeoutException){
            //连接超时
            errorHint = "连接超时";
        }else if (e instanceof SocketException){
            //连接异常
            errorHint = "连接异常";
        }else{
            //其他异常
            errorHint = "连接失败";
        }
        onFailed(errorHint,e);
    }

    protected abstract void onFailed(String error, Exception e);

    @Override
    public void onBefore() {

    }

}
