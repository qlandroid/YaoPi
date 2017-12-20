package com.shqtn.yaopi.http.ksoap;

/**
 * Created by android on 2017/8/28.
 */

public class KsoapModelService {

    public static void run(Object params, Callback callback) {
        KsoapHttpUtils.http().params(params)
                .build().execute(callback);
    }
}
