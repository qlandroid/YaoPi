package com.shqtn.yaopi.bean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-7-10.
 */
public class ResultBean {

    private Ncback ncback;


    public Ncback getNcback() {
        return ncback;
    }

    public void setNcback(Ncback ncback) {
        this.ncback = ncback;
    }


    public static class Ncback {
        public static final String RESULT_TAG_YES = "Y";
        private ArrayList<Values> listData;
        private ArrayList<Values> listTeam;
        private ArrayList<Values> listClazz;
        private String result;//Y 是正确
        private String errormsg;//错误消息

        private String quantity;//扫描任务单后，需要扫描箱子的数量

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getErrormsg() {
            return errormsg;
        }

        public void setErrormsg(String errormsg) {
            this.errormsg = errormsg;
        }

        public ArrayList<Values> getListData() {
            return listData;
        }

        public void setListData(ArrayList<Values> listDate) {
            this.listData = listDate;
        }

        public ArrayList<Values> getListTeam() {
            return listTeam;
        }

        public void setListTeam(ArrayList<Values> listTeam) {
            this.listTeam = listTeam;
        }

        public ArrayList<Values> getListClazz() {
            return listClazz;
        }

        public void setListClazz(ArrayList<Values> listClazz) {
            this.listClazz = listClazz;
        }
    }

    public static class Values {
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }


}
