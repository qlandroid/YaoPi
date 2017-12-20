package com.shqtn.yaopi.http.ksoap;

/**
 * Created by android on 2017/8/28.
 */

public class KsoapRequestCall {

    private String url;
    private String namespace;
    private String methodName;
    private String paramsContent;

    public KsoapRequestCall(String url ,String namespace,String methodName,String paramsContent) {
        if (url == null){
            throw new  RuntimeException("请求参数url不能为空");
        }
        if(namespace == null){
            throw new  RuntimeException("请求参数请求方法不能为空");
        }
        this.url = url;
        this.namespace = namespace;
        this.methodName = methodName;
        this.paramsContent = paramsContent;
    }

    public void execute(Callback callback) {
        KsoapHttpUtils.getInstance().execute(url,namespace,methodName,paramsContent, callback);
    }
}
