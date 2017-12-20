package com.shqtn.yaopi.bean.params;

/**
 * Created by android on 2017/8/30.
 */

public class ItemValues {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if (value != null) {
            value = value.trim();
        }
        this.value = value;
    }
}
