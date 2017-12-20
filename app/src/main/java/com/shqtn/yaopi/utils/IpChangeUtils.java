package com.shqtn.yaopi.utils;

import android.content.Context;

/**
 * Created by android on 2017/8/30.
 */

public class IpChangeUtils {

    private static final String FILE_IP = "file_ip";

    private static final String KEY_IP = "ip";
    private static final String KEY_POST = "post";

    public static void saveIp(Context context,String ip){
        PreferencesUtils.saveString(context, FILE_IP,KEY_IP,ip);
    }

    public static String getIp(Context context){
        return PreferencesUtils.queryString(context,FILE_IP,KEY_IP);
    }
    public static void savePost(Context context,String post){
        PreferencesUtils.saveString(context, FILE_IP,KEY_POST,post);
    }

    public static String getPost(Context context){
        return PreferencesUtils.queryString(context,FILE_IP,KEY_POST);
    }
}
