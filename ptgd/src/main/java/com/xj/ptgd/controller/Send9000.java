package com.xj.ptgd.controller;

import com.xj.ptgd.common.exception.ResultException;
import com.xj.ptgd.common.httpXml.HttpsUtils;
import com.xj.ptgd.common.result.ResultEnum;
import com.xj.ptgd.common.util.JaxbUtil;
import com.xj.ptgd.common.util.MacUtil;
import com.xj.ptgd.entity.body.StartRet;
import com.xj.ptgd.entity.out.StartRetXMLOut;

import static com.xj.ptgd.common.config.YmlConfig.TRANSACTION_URL;

public class Send9000 {

    public String StartXML(String xml){
        String result = "";
        result = HttpsUtils.sendByHttp(xml, TRANSACTION_URL);
        String res = result.substring(6,result.length());
        System.out.println( "**********更新密钥***开始**********");
        StartRetXMLOut xmlIn = new StartRetXMLOut();
        try {
            JaxbUtil ju = new JaxbUtil(StartRetXMLOut.class);
            xmlIn = ju.fromXml(res);
        }catch (Exception ex){
            if (ex instanceof NullPointerException || ex instanceof ClassCastException){
                throw new ResultException(ResultEnum.http_status_request_null,ResultEnum.http_status_request_null.getMsg());
            }else {
                throw new ResultException(ResultEnum.http_status_internal_server_error,ResultEnum.http_status_internal_server_error.getMsg());
            }
        }
        StartRet body  = xmlIn.getBody();
        if(body!=null){
            MacUtil.updateWorkKey(body.getMacKeyValue(),body.getMacVerifyValue());
        }
        System.out.println( "**********更新密钥***结束**********");
        return result;
    }

}
