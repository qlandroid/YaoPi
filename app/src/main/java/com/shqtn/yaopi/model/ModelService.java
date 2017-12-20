package com.shqtn.yaopi.model;

import android.support.annotation.NonNull;

import com.shqtn.yaopi.http.OkHttpUtils;
import com.shqtn.yaopi.utils.ActivityUtils;
import com.shqtn.yaopi.utils.LogUtils;
import com.zhy.http.okhttp.callback.Callback;


/**
 * Created by android on 2017/7/13.
 */

public class ModelService {

    public static void post(@NonNull String url, Object params, Callback callback) {
        LogUtils.i("请求地址:" + url);
        OkHttpUtils.post().params(params)
                .url(url)
                .tag(ActivityUtils.getInstance().getTopAty())
                .build()
                .execute(callback);
    }
}
