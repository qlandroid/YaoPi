package com.shqtn.yaopi.http.ksoap;


import com.shqtn.yaopi.App;
import com.shqtn.yaopi.utils.IpChangeUtils;
import com.shqtn.yaopi.utils.JsonUtils;

/**
 * Created by android on 2017/8/28.
 */

public class KsoapModel {
    private String WSDL_URI ;//wsdl 的uri
    private String namespace = "http://pda.qberp.itf.nc/QbInterfaceService";//namespace
    private String methodName = "qbInterfaceService";//要调用的方法名称

    private String paramsContent;//请求体
    public KsoapModel() {
        String ip = IpChangeUtils.getIp(App.getInstance());
        String post = IpChangeUtils.getPost(App.getInstance());
        WSDL_URI = ip + ":" + post + "/uapws/service/nc.itf.qberp.pda.QbInterfaceService";
    }



    public KsoapModel url(String url) {
        WSDL_URI = url;
        return this;
    }

    public KsoapModel namespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    public KsoapModel methodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public KsoapModel params(Object params) {
        this.paramsContent = JsonUtils.toJson(params);
        return this;
    }

    public KsoapRequestCall build() {
        return  new KsoapRequestCall(WSDL_URI,namespace,methodName,paramsContent);
    }

}
