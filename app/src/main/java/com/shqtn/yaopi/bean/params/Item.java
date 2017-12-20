package com.shqtn.yaopi.bean.params;

/**
 * Created by android on 2017/8/30.
 */

public class Item {
    private String billcode;//单号
    private String boxno;//箱号
    private String rackno;//货位
    private String location;
    private String location2;

    public String getLocation2() {
        return location2;
    }

    public void setLocation2(String location2) {
        this.location2 = location2;
    }

    public String getBillcode() {
        return billcode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (location != null) {
            location = location.trim();
        }
        this.location = location;
    }

    public void setBillcode(String billcode) {
        if (billcode != null) {
            billcode = billcode.trim();
        }
        this.billcode = billcode;
    }

    public String getBoxno() {
        return boxno;
    }

    public void setBoxno(String boxno) {
        if (boxno != null) {
            boxno = boxno.trim();
        }
        this.boxno = boxno;
    }

    public String getRackno() {
        return rackno;
    }

    public void setRackno(String rackno) {
        if (rackno != null) {
            rackno = rackno.trim();
        }
        this.rackno = rackno;
    }
}
