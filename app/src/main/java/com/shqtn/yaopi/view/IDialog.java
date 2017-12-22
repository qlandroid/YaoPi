package com.shqtn.yaopi.view;

/**
 * 创建时间:2017/12/21
 * 描述:
 *
 * @author ql
 */

public interface IDialog {
    void displayLoadingDialog(String msg);

    void displayLoadingDialog();

    void cancelLoadingDialog();

    void displayMsgDialog(String title, String msg);

    void displayMsgDialog(String msg);

    void cancelMsgDialog();

    void toast(String toast);
}
