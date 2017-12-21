package com.shqtn.yaopi.controller;

import com.shqtn.yaopi.bean.ResultBean;
import com.shqtn.yaopi.bean.params.Item;
import com.shqtn.yaopi.bean.params.Safety;
import com.shqtn.yaopi.bean.params.SubmitParams;
import com.shqtn.yaopi.http.ksoap.KsoapModelService;
import com.shqtn.yaopi.http.ksoap.StringCallback;
import com.shqtn.yaopi.ui.fragment.BoxListFragment;
import com.shqtn.yaopi.utils.JsonUtils;
import com.shqtn.yaopi.utils.ResultUtils;
import com.shqtn.yaopi.view.IDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间:2017/12/21
 * 描述:
 *
 * @author ql
 */

public class AddBoxController {

    public interface IView extends IDialog {
        void setManifestNo(String manifestNo);

        void setBoxSize(int boxSize);

        void setAddBoxSize(int addSize);

        void updateList(List<BoxListFragment.Item> list);

        void setTitle(String title);

        void changeAddBoxFragment();

        void changeTextFragment();

        void setTextFragmentContent(String content);

        void setAddFragmentButton(String name);


    }

    public interface IPresenter extends IBasePresenter<IView> {

        public void clickAddFragmentButton();
    }


}
