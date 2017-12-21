package com.shqtn.yaopi.controller;

import android.os.Bundle;

import com.shqtn.yaopi.view.IDialog;

/**
 * 创建时间:2017/12/21
 * 描述:
 *
 * @author ql
 */

public class ScanningTextController {

    public interface IView extends IDialog {
        void setTitle(String title);

        void setText(String text);

        void toActivity(Class aty, Bundle bundle);

        void closeActivity();
    }

    public interface IPresenter extends IBasePresenter<IView> {

    }
}
