package com.shqtn.yaopi.bean.params;

/**
 * Created by android on 2017/8/14.
 */

public class LoginParams {

    private Safety safety;
    private Item item;

    public Safety getSafety() {
        return safety;
    }

    public void setSafety(Safety safety) {
        this.safety = safety;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


    public static  class Item{
        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
