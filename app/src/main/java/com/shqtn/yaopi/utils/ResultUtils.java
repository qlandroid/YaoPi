package com.shqtn.yaopi.utils;


import com.shqtn.yaopi.bean.ResultBean;

/**
 * Created by android on 2017/8/30.
 */

public class ResultUtils {
    public static boolean isSuccess(ResultBean.Ncback result){
        if(!ResultBean.Ncback.RESULT_TAG_YES.equals(result.getResult())){
            return false;
        }
        return true;
    }
}
