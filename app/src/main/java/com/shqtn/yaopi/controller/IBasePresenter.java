package com.shqtn.yaopi.controller;

import android.os.Bundle;

/**
 * 创建时间:2017/12/21
 * 描述:
 *
 * @author ql
 */

public interface IBasePresenter<T> {
    void setBundle(Bundle bundle);

    void setView(T view);

    void resultCode(String code);

    void init();
}
