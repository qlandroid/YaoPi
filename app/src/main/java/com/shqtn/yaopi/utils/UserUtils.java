package com.shqtn.yaopi.utils;

import android.content.Context;

/**
 * Created by android on 2017/8/30.
 */

public class UserUtils {

    private static final String FILE_NAME = "clazz";
    private static final String KEY_CLAZZ = "clazz";
    private static final String KEY_CODE = "code";
    private static final String KEY_TEAM = "team";

    public static void saveClazz(Context context, String clazz) {
        PreferencesUtils.saveString(context, FILE_NAME, KEY_CLAZZ, clazz);
    }

    public static void saveCode(Context context, String code) {
        PreferencesUtils.saveString(context, FILE_NAME, KEY_CODE, code);
    }

    public static void saveTeam(Context context, String team) {
        PreferencesUtils.saveString(context, FILE_NAME, KEY_TEAM, team);
    }

    public static String getClazz(Context context) {
        return PreferencesUtils.queryString(context, FILE_NAME, KEY_CLAZZ);
    }

    public static String getCode(Context context) {
        return PreferencesUtils.queryString(context, FILE_NAME, KEY_CODE);
    }

    public static String getTeam(Context context) {
        return PreferencesUtils.queryString(context, FILE_NAME, KEY_TEAM);
    }

    public static void clear(Context context) {
        PreferencesUtils.deleteFile(context, FILE_NAME);
    }

}
