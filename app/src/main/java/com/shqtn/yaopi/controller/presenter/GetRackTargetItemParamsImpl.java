package com.shqtn.yaopi.controller.presenter;

import com.shqtn.yaopi.bean.params.Item;

/**
 * 创建时间:2017/12/22
 * 描述:
 *
 * @author ql
 */

public class GetRackTargetItemParamsImpl implements IGetItemParams {
    @Override
    public Item getItem(String decode) {
        Item item = new Item();
        item.setRackno(decode);
        return item;
    }
}
