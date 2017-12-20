package com.shqtn.yaopi.http.ksoap;

import org.ksoap2.serialization.SoapSerializationEnvelope;

/**
 * Created by android on 2017/8/28.
 */

public interface Callback<T> {
    // SoapObject result = (SoapObject) envelope.bodyIn;
    // result.getProperty(0).toString();
    public void onAfter();

    public T onResponse(SoapSerializationEnvelope response) throws Exception;

    public void onFailed(SoapSerializationEnvelope response, Exception e);

    public void onBefore();

    void onReplaceResponse(T t);
}
