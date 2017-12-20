package com.shqtn.yaopi.bean.params;

import java.util.List;

/**
 * Created by android on 2017/8/30.
 */

public class SubmitParams {
    private Safety safety;
    private Psn psn;
    private Item item;
    private List<ItemValues> boxno;

    public Safety getSafety() {
        return safety;
    }

    public void setSafety(Safety safety) {
        this.safety = safety;
    }

    public Psn getPsn() {
        return psn;
    }

    public void setPsn(Psn psn) {
        this.psn = psn;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<ItemValues> getBoxno() {
        return boxno;
    }

    public void setBoxno(List<ItemValues> boxno) {
        this.boxno = boxno;
    }
}
