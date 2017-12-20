package com.shqtn.yaopi.utils;

import android.content.Context;

import com.shqtn.yaopi.bean.params.Psn;
import com.shqtn.yaopi.bean.params.Safety;


/**
 * Created by android on 2017/8/30.
 */

public class ParamsFactory {
    public static Psn getPsn(Context context) {
        Psn psn = new Psn();
        psn.setClazz(UserUtils.getClazz(context));
        psn.setPsncode(UserUtils.getCode(context));
        psn.setTeam(UserUtils.getTeam(context));
        return psn;
    }

    public static Safety createLoginSafety() {
        Safety s = new Safety();
        s.setModule("DL");
        s.setReqtype("DL");
        return s;
    }

    public static class InDepot {


        /**
         * 到货单扫描
         *
         * @return
         */
        public static Safety createManifestSafety() {
            Safety s = new Safety();
            s.setModule("CLRK");
            s.setReqtype("DH");
            return s;
        }

        /**
         * 扫描货箱号
         *
         * @return
         */
        public static Safety createScanningBox() {
            Safety s = new Safety();
            s.setModule("CLRK");
            s.setReqtype("XH");
            return s;
        }

        /**
         * 扫描存货货位
         *
         * @return
         */
        public static Safety createSaveRackSafety() {
            Safety s = new Safety();
            s.setModule("CLRK");
            s.setReqtype("KW");
            return s;
        }

        /**
         * 扫描存货货位
         *
         * @return
         */
        public static Safety createSubmitSafety() {
            Safety s = new Safety();
            s.setModule("CLRK");
            s.setReqtype("TJ");
            return s;
        }
    }

    public static class Receive {
        /**
         * 领料单扫描，扫描任务单
         *
         * @return
         */
        public static Safety createScanningManfiest() {
            Safety s = new Safety();
            s.setModule("LLCK");
            s.setReqtype("CK");
            return s;
        }

        /**
         * 领料单扫描，扫描任务单
         *
         * @return
         */
        public static Safety createScanningBoxNo() {
            Safety s = new Safety();
            s.setModule("LLCK");
            s.setReqtype("XH");
            return s;
        }

        /**
         * 领料单扫描，扫描任务单
         *
         * @return
         */
        public static Safety createSubmit() {
            Safety s = new Safety();
            s.setModule("LLCK");
            s.setReqtype("TJ");
            return s;
        }
    }

    /**
     * 半成品，产成品日苦
     */
    public static class MatureInDepot {
        /**
         * 完工报告单扫描
         *
         * @return
         */
        public static Safety createScanningManifest() {
            Safety s = new Safety();
            s.setModule("WGRK");
            s.setReqtype("CP");
            return s;
        }

        /**
         * 箱号扫描
         *
         * @return
         */
        public static Safety createScanningBoxNo() {
            Safety s = new Safety();
            s.setModule("WGRK");
            s.setReqtype("XH");
            return s;
        }

        /**
         * 箱号扫描
         *
         * @return
         */
        public static Safety createRack() {
            Safety s = new Safety();
            s.setModule("WGRK");
            s.setReqtype("KW");
            return s;
        }

        /**
         * 提交
         *
         * @return
         */
        public static Safety createSubmit() {
            Safety s = new Safety();
            s.setModule("WGRK");
            s.setReqtype("TJ");
            return s;
        }
    }

    /**
     * 销售出库
     */
    public static class SellOutDepot {
        /**
         * 完工报告单扫描
         *
         * @return
         */
        public static Safety createScanningManfiest() {
            Safety s = new Safety();
            s.setModule("SXCK");
            s.setReqtype("CK");
            return s;
        }

        /**
         * 箱号扫描
         *
         * @return
         */
        public static Safety createScanningBoxNo() {
            Safety s = new Safety();
            s.setModule("SXCK");
            s.setReqtype("XH");
            return s;
        }

        /**
         * 提交
         *
         * @return
         */
        public static Safety createSubmit() {
            Safety s = new Safety();
            s.setModule("SXCK");
            s.setReqtype("TJ");
            return s;
        }
    }

    /**
     * 调拨出库
     */
    public static class AllocationOutDepot {
        /**
         * 完工报告单扫描
         *
         * @return
         */
        public static Safety createScanningManfiest() {
            Safety s = new Safety();
            s.setModule("DBCK");
            s.setReqtype("CK");
            return s;
        }

        /**
         * 箱号扫描
         *
         * @return
         */
        public static Safety createScanningBoxNo() {
            Safety s = new Safety();
            s.setModule("DBCK");
            s.setReqtype("XH");
            return s;
        }

        /**
         * 提交
         *
         * @return
         */
        public static Safety createSubmit() {
            Safety s = new Safety();
            s.setModule("DBCK");
            s.setReqtype("TJ");
            return s;
        }
    }

    /**
     * 调拨入库
     */
    public static class AllocationInDepot {
        /**
         * 完工报告单扫描
         *
         * @return
         */
        public static Safety createScanningManifest() {
            Safety s = new Safety();
            s.setModule("DBRK");
            s.setReqtype("CP");
            return s;
        }

        /**
         * 箱号扫描
         *
         * @return
         */
        public static Safety createScanningBoxNo() {
            Safety s = new Safety();
            s.setModule("DBRK");
            s.setReqtype("XH");
            return s;
        }

        /**
         * 箱号扫描
         *
         * @return
         */
        public static Safety createScanningRack() {
            Safety s = new Safety();
            s.setModule("DBRK");
            s.setReqtype("KW");
            return s;
        }

        /**
         * 提交
         *
         * @return
         */
        public static Safety createSubmit() {
            Safety s = new Safety();
            s.setModule("DBRK");
            s.setReqtype("TJ");
            return s;
        }
    }

    /**
     * 货位调整
     */
    public static class GoodsAdjust {
        /**
         * 完工报告单扫描
         *
         * @return
         */
        public static Safety createScanningManfiest() {
            Safety s = new Safety();
            s.setModule("TZRK");
            s.setReqtype("CK");
            return s;
        }

        /**
         * 箱号扫描
         *
         * @return
         */
        public static Safety createScanningBoxNo() {
            Safety s = new Safety();
            s.setModule("TZRK");
            s.setReqtype("XH");
            return s;
        }


        /**
         * 提交
         *
         * @return
         */
        public static Safety createSubmit() {
            Safety s = new Safety();
            s.setModule("TZRK");
            s.setReqtype("TJ");
            return s;
        }
    }
}
