package com.shqtn.yaopi;

import android.app.Application;
import android.os.Handler;

/**
 * 创建时间:2017/12/20
 * 描述:
 *
 * @author ql
 */

public class App extends Application {
    public static App mInstance;

    public static App getInstance() {
        return mInstance;
    }

    public Handler handler = new Handler();
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
