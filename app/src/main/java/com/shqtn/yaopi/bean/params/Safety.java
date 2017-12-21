package com.shqtn.yaopi.bean.params;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by android on 2017/8/30.
 */

public class Safety implements Parcelable {
    private String module;
    private String reqtype;


    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getReqtype() {
        return reqtype;
    }

    public void setReqtype(String reqtype) {
        this.reqtype = reqtype;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.module);
        dest.writeString(this.reqtype);
    }

    public Safety() {
    }

    protected Safety(Parcel in) {
        this.module = in.readString();
        this.reqtype = in.readString();
    }

    public static final Parcelable.Creator<Safety> CREATOR = new Parcelable.Creator<Safety>() {
        @Override
        public Safety createFromParcel(Parcel source) {
            return new Safety(source);
        }

        @Override
        public Safety[] newArray(int size) {
            return new Safety[size];
        }
    };
}
