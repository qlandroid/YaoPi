package com.shqtn.yaopi.http;

import java.util.Map;

/**
 * Created by Administrator on 2017-7-5.
 */
public class OkHttpUtils {
    private static OkHttpUtils mInstance;

    private OkHttpUtils(){

    }
    public static void addHeaderParams(Map<String,String> headerParams){
        PostStringBuilder.setInitHeaderParams(headerParams);
    }

    public static PostStringBuilder post(){
        return new PostStringBuilder();
    }

    public static void cancel(Object tag){
        com.zhy.http.okhttp.OkHttpUtils.getInstance().cancelTag(tag);
    }


}
