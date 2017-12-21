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

public class OperateBoxBean implements Parcelable {

    private boolean isScanningRack;//是否扫描货位
    private Safety rackSafety;//货位的请求参数
    private Safety submitSafety;//提交的请求参数；
    private Safety addBoxSafety;//添加箱子的请求参数;

    public OperateBoxBean(boolean isScanningRack, Safety addBoxSafety, Safety rackSafety, Safety submitSafety) {
        this.isScanningRack = isScanningRack;
        this.rackSafety = rackSafety;
        this.submitSafety = submitSafety;
        this.addBoxSafety = addBoxSafety;
    }

    public boolean isScanningRack() {
        return isScanningRack;
    }

    public void setScanningRack(boolean scanningRack) {
        isScanningRack = scanningRack;
    }

    public Safety getRackSafety() {
        return rackSafety;
    }

    public void setRackSafety(Safety rackSafety) {
        this.rackSafety = rackSafety;
    }

    public Safety getSubmitSafety() {
        return submitSafety;
    }

    public void setSubmitSafety(Safety submitSafety) {
        this.submitSafety = submitSafety;
    }

    public Safety getAddBoxSafety() {
        return addBoxSafety;
    }

    public void setAddBoxSafety(Safety addBoxSafety) {
        this.addBoxSafety = addBoxSafety;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isScanningRack ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.rackSafety, flags);
        dest.writeParcelable(this.submitSafety, flags);
        dest.writeParcelable(this.addBoxSafety, flags);
    }

    public OperateBoxBean() {
    }

    protected OperateBoxBean(Parcel in) {
        this.isScanningRack = in.readByte() != 0;
        this.rackSafety = in.readParcelable(Safety.class.getClassLoader());
        this.submitSafety = in.readParcelable(Safety.class.getClassLoader());
        this.addBoxSafety = in.readParcelable(Safety.class.getClassLoader());
    }

    public static final Parcelable.Creator<OperateBoxBean> CREATOR = new Parcelable.Creator<OperateBoxBean>() {
        @Override
        public OperateBoxBean createFromParcel(Parcel source) {
            return new OperateBoxBean(source);
        }

        @Override
        public OperateBoxBean[] newArray(int size) {
            return new OperateBoxBean[size];
        }
    };
}
