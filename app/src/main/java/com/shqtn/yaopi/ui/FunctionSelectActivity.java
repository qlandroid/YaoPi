package com.shqtn.yaopi.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shqtn.yaopi.BaseActivity;
import com.shqtn.yaopi.R;
import com.shqtn.yaopi.bind.BindView;
import com.shqtn.yaopi.controller.presenter.OperateBoxBean;
import com.shqtn.yaopi.controller.presenter.TextPresenterBean;
import com.shqtn.yaopi.controller.presenter.impl.GoodsAdjustRackTextPresenterImpl;
import com.shqtn.yaopi.controller.presenter.impl.NormalScanningBoxPresenter;
import com.shqtn.yaopi.utils.ParamsFactory;

import java.util.Arrays;
import java.util.List;

public class FunctionSelectActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {


    @BindView(R.id.activity_function_select_rv)
    RecyclerView rv;

    BaseQuickAdapter<String, BaseViewHolder> functionAdapter;

    private List<String> functions;
    private String[] function = {"材料入库", "生产领料", "成品入库", "销售出库", "调拨入库", "调拨出库"
            , "货位调整", "退出"};

    private TextPresenterBean[] beans = {
            new TextPresenterBean("材料入库", "请扫描到货单条码",
                    ScanningBoxActivity.class, NormalScanningBoxPresenter.class, ParamsFactory.InDepot.createManifestSafety(),
                    new OperateBoxBean(true, ParamsFactory.InDepot.createScanningBox(),
                            ParamsFactory.InDepot.createSaveRackSafety(),
                            ParamsFactory.InDepot.createSubmitSafety())),
            new TextPresenterBean("生产领料", "请扫备料计划单条码",
                    ScanningBoxActivity.class, NormalScanningBoxPresenter.class, ParamsFactory.Receive.createScanningManfiest(),
                    new OperateBoxBean(false, ParamsFactory.Receive.createScanningBoxNo(),
                            null,
                            ParamsFactory.Receive.createSubmit())),
            new TextPresenterBean("成品入库", "请扫完工报告条码",
                    ScanningBoxActivity.class, NormalScanningBoxPresenter.class, ParamsFactory.MatureInDepot.createScanningManifest(),
                    new OperateBoxBean(true, ParamsFactory.MatureInDepot.createScanningBoxNo(),
                            ParamsFactory.MatureInDepot.createRack(),
                            ParamsFactory.MatureInDepot.createSubmit())),
            new TextPresenterBean("销售出库", "请扫发货单条码",
                    ScanningBoxActivity.class, NormalScanningBoxPresenter.class, ParamsFactory.SellOutDepot.createScanningManfiest(),
                    new OperateBoxBean(false, ParamsFactory.SellOutDepot.createScanningBoxNo(),
                            null,
                            ParamsFactory.SellOutDepot.createSubmit())),
            new TextPresenterBean("调拨入库", "请扫调拨出库单条码",
                    ScanningBoxActivity.class, NormalScanningBoxPresenter.class, ParamsFactory.AllocationInDepot.createScanningManifest(),
                    new OperateBoxBean(true, ParamsFactory.AllocationInDepot.createScanningBoxNo(),
                            ParamsFactory.AllocationInDepot.createScanningRack(),
                            ParamsFactory.AllocationInDepot.createSubmit())),
            new TextPresenterBean("调拨出库", "请扫调拨订单条码",
                    ScanningBoxActivity.class, NormalScanningBoxPresenter.class, ParamsFactory.AllocationOutDepot.createScanningManfiest(),
                    new OperateBoxBean(false, ParamsFactory.AllocationOutDepot.createScanningBoxNo(),
                            null,
                            ParamsFactory.AllocationOutDepot.createSubmit()))};

    @Override
    public void createView() {
        setContentView(R.layout.activity_function_select);
    }


    @Override
    public void initData() {
        super.initData();
        functions = Arrays.asList(function);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        functionAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_function, functions) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.item_function_tv, item);
            }
        };

        functionAdapter.setOnItemClickListener(this);

        rv.setLayoutManager(new GridLayoutManager(this, 2));

        rv.setAdapter(functionAdapter);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        if (position == function.length - 1) {
            onBackPressed();
        } else if (position == function.length - 2) {
            //货位调整
            Bundle bundle = new Bundle();
            ScanningTextActivity.put(GoodsAdjustRackTextPresenterImpl.class, bundle);
            startActivity(ScanningBoxActivity.class, bundle);
        } else {
            Bundle bundle = new Bundle();
            ScanningTextActivity.put(beans[position], bundle);
            startActivity(ScanningTextActivity.class, bundle);
        }
    }
}
