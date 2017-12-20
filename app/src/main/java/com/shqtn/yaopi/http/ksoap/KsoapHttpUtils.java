package com.shqtn.yaopi.http.ksoap;

import android.os.Handler;
import android.os.Looper;


import com.shqtn.yaopi.utils.LogUtils;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by android on 2017/8/28.
 */

public class KsoapHttpUtils {
    private String WSDL_URI = "http://27.115.105.66:8000/uapws/service/nc.itf.qberp.pda.QbInterfaceService";//wsdl 的uri
    private String namespace = "http://pda.qberp.itf.nc/QbInterfaceService";//namespace
    private String methodName = "qbInterfaceService";//要调用的方法名称

    private static final int INIT_SIZE = 5;
    private ExecutorService fixedThreadPool;
    private Handler mHandler;
    private static KsoapHttpUtils mInstance;
    public static KsoapHttpUtils getInstance() {
        if(mInstance == null){
            synchronized (KsoapHttpUtils.class){
                if (mInstance == null){
                    mInstance = new KsoapHttpUtils();
                }
            }
        }
        return mInstance;
    }

    private KsoapHttpUtils() {
        init(INIT_SIZE);
    }

    private void init(int runSum) {
        fixedThreadPool = Executors.newFixedThreadPool(runSum);
        mHandler = new Handler(Looper.getMainLooper());
    }


    public void execute(final String url, final String namespace, final String methodName, final String jsonParams, final Callback callback) {
        LogUtils.i("url : " + url);
        LogUtils.i("请求参数:" + jsonParams);
        final Callback fCallback = callback;
        Runnable run = new Runnable() {
            @Override
            public void run() {
                SoapSerializationEnvelope envelope = null;
                try {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            fCallback.onBefore();
                        }
                    });
                    envelope = runResultBody(url, namespace, methodName, jsonParams);
                    final SoapSerializationEnvelope finalEnvelope = envelope;
                    sendResultSuccess(finalEnvelope, callback);

                } catch (final Exception e) {
                    e.printStackTrace();
                    final SoapSerializationEnvelope finalEnvelope1 = envelope;
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            fCallback.onFailed(finalEnvelope1, e);
                        }
                    });

                }
            }
        };
        fixedThreadPool.execute(run);
    }

    private void sendResultSuccess(final SoapSerializationEnvelope finalEnvelope, final Callback callback) {
        if (callback == null) return;
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Object obj = null;
                try {
                    obj = callback.onResponse(finalEnvelope);
                    callback.onReplaceResponse(obj);

                } catch (final Exception e) {
                    e.printStackTrace();
                    callback.onFailed(null, e);
                }
                callback.onAfter();

            }
        });
    }


    private SoapSerializationEnvelope runResultBody(String url, String namespace, String methodName, String jsonParams) throws Exception {
        HttpTransportSE ht = new HttpTransportSE(url);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
        SoapObject request = new SoapObject(namespace, methodName);
        PropertyInfo pi = new PropertyInfo();
        pi.setName("string");
        pi.setValue(jsonParams);
        pi.setType(String.class);
        request.addProperty(pi);
        envelope.bodyOut = request;
        ht.call(namespace + "#" + methodName, envelope);//调用
        return envelope;
    }

    public static KsoapModel http() {
        return new KsoapModel();
    }
}
