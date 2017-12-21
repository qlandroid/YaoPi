package com.shqtn.yaopi.controller.presenter;

import android.os.Parcel;
import android.os.Parcelable;

import com.shqtn.yaopi.bean.params.Safety;

/**
 * 创建时间:2017/12/21
 * 描述:
 *
 * @author ql
 */

public class TextPresenterBean implements Parcelable {

    private String title;
    private String content;
    private Class toActivity;
    private Class presenter;
    private Safety safety;

    private OperateBoxBean operateBoxBean;

    public TextPresenterBean() {
    }

    public TextPresenterBean(String title, String content, Class toActivity, Class presenter, Safety safety, OperateBoxBean operateBoxBean) {
        this.title = title;
        this.content = content;
        this.toActivity = toActivity;
        this.presenter = presenter;
        this.safety = safety;
        this.operateBoxBean = operateBoxBean;
    }

    public OperateBoxBean getOperateBoxBean() {
        return operateBoxBean;
    }

    public void setOperateBoxBean(OperateBoxBean operateBoxBean) {
        this.operateBoxBean = operateBoxBean;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Class getToActivity() {
        return toActivity;
    }

    public void setToActivity(Class toActivity) {
        this.toActivity = toActivity;
    }

    public Class getPresenter() {
        return presenter;
    }

    public void setPresenter(Class presenter) {
        this.presenter = presenter;
    }

    public Safety getSafety() {
        return safety;
    }

    public void setSafety(Safety safety) {
        this.safety = safety;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.content);
        dest.writeSerializable(this.toActivity);
        dest.writeSerializable(this.presenter);
        dest.writeParcelable(this.safety, flags);
        dest.writeParcelable(this.operateBoxBean, flags);
    }

    protected TextPresenterBean(Parcel in) {
        this.title = in.readString();
        this.content = in.readString();
        this.toActivity = (Class) in.readSerializable();
        this.presenter = (Class) in.readSerializable();
        this.safety = in.readParcelable(Safety.class.getClassLoader());
        this.operateBoxBean = in.readParcelable(OperateBoxBean.class.getClassLoader());
    }

    public static final Creator<TextPresenterBean> CREATOR = new Creator<TextPresenterBean>() {
        @Override
        public TextPresenterBean createFromParcel(Parcel source) {
            return new TextPresenterBean(source);
        }

        @Override
        public TextPresenterBean[] newArray(int size) {
            return new TextPresenterBean[size];
        }
    };
}
