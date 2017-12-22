package com.shqtn.yaopi.controller.presenter;

import com.shqtn.yaopi.bean.params.Item;

/**
 * 创建时间:2017/12/22
 * 描述:
 * Item item = new Item();
 * if (addRack) {
 * item.setRackno(code);
 * } else {
 * item.setBillcode(code);
 * }
 *
 * @author ql
 */

public interface IGetItemParams {

    Item getItem(String decode);

}
