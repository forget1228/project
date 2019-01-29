package com.xj.ptgd.common.util;

import cebenc.softenc.SoftEnc;
import com.xj.ptgd.common.logs.LogUtils;
import org.slf4j.Logger;

import static com.xj.ptgd.common.config.YmlConfig.MAC_PAW_PATH;
import static com.xj.ptgd.common.config.YmlConfig.SYS_CHARSET;

public class MacUtil {
    private static Logger log = LogUtils.getHttpXMLLogger();

    public static String addMac(String reqXML){
        //请求报文加MAC，MAC值为16位
//        String reqXML="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n<in><head><Version>1.0.1</Version><InstId>BJCEB</InstId><AnsTranCode>BJCEBQBIReq</AnsTranCode><TrmSeqNum>00711497180212094224</TrmSeqNum></head><tin><billKey>finance|160396371336101889</billKey><companyId>020001792</companyId><beginNum>1</beginNum><queryNum>1</queryNum><filed1></filed1><filed2></filed2><filed3></filed3><filed4></filed4></tin></in>";
        //生成MAC
        log.info( "**********计算MAC***开始**********");
        String mac16=toMAC(reqXML);
        log.info("生成MAC："+mac16);
        //将MAC拼接在报文最后
        reqXML=reqXML+mac16;
        log.info("加MAC后的请求报文："+reqXML);
        return reqXML;
    }

    //更新密钥文件
    public static void updateWorkKey(String keyValue,String verifyValue) {
        //接收到的xml报文
        //例如     000540<?xml version="1.0" encoding="ISO-8859-1"?><out><head><Version>1.0.1</Version><InstId>BJCEB</InstId><AnsTranCode>BJCEBRWKRes</AnsTranCode><TrmSeqNum>admin180207110116</TrmSeqNum></head><tout><partnerCode>746</partnerCode><returnCode>00</returnCode><errorDescription></errorDescription><keyName>ZPK</keyName><keyValue>E5A915D81EB9B8BE816F4E46011B91C4</keyValue><verifyValue>032E5A89CDC16AE2</verifyValue><keyName1>ZAK</keyName1><keyValue1>D13A9DD478ABF1CB633DC4BAE1593CEA</keyValue1><verifyValue1>77F7AF1A9018A7F0</verifyValue1></tout></out>

        //获取报文中的keyValue、verifyValue标签中的值
//        String keyValue = "DF00BA0E7F83FB8F63B232321D1E454D";
//        String verifyValue = "BADA25DB94E37891";
        System.out.println("keyValue:"+keyValue+",verifyValue:"+verifyValue);

        // 密钥文件目录
        //.0.SYS和.1.SYS为申请的主密钥文件，以下方法会生成.3.SYS，为工作密钥，即计算MAC所使用密钥
        String fileDir = MAC_PAW_PATH;
        System.out.println("密钥文件路径=====["+fileDir+"]");

        try {
            SoftEnc.Init(fileDir);
            //将工作密钥更新到本地密钥文件中
            SoftEnc.WriteMACK(keyValue,verifyValue);
            System.out.println("工作密钥更新成功!!!!");
        } catch (Exception e) {
            System.out.println("工作密钥更新成功!!!!");
            e.printStackTrace();
        }
    }
    //使用MAC密钥对报文计算MAC
    public static String toMAC(String xml) {
        String mac16="";
        try {
            //生成MAC
            mac16 = SoftEnc.GenMac(xml.getBytes(SYS_CHARSET)); //"GB18030"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mac16;
    }

    public static boolean checkMAC(String resXML){
//        String resXML="000313<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><out><head><Version>1.0.1</Version><InstId>000000000000000</InstId><AnsTranCode>Error</AnsTranCode><TrmSeqNum>00711497180212094224</TrmSeqNum></head><tout><errorCode>DEF0002</errorCode><errorMessage></errorMessage><errorDetail></errorDetail></tout></out>32E5677A7BF5B546";
        //截取报文中最后16位
        String resMAC=resXML.substring(resXML.length() - 16,resXML.length());
        log.info("应答报文中的MAC："+resMAC);
        //根据应答报文生成MAC
        //去头和尾，头是报文长度，尾是MAC
        resXML=resXML.substring( 0, resXML.length() - 16 );
        //如果没有头，则不需要
        resXML=resXML.substring( 6, resXML.length());
        String MAC=toMAC(resXML);
        log.info("应答报文生成的MAC："+MAC);
        //判断收到的MAC与生成的是否一致
        if(resMAC.equals(MAC)){
            log.info("MAC校验通过!!");
            return true;
        }else{
            log.info("MAC校验不通过!!");
            return false;
        }
    }

    public static String subXMLForMAC(String MacAndXMl){
        String xml = null;
        //去头和尾，头是报文长度，尾是MAC
        MacAndXMl=MacAndXMl.substring( 0, MacAndXMl.length() - 16 );
        //如果没有头，则不需要
        xml=MacAndXMl.substring( 6, MacAndXMl.length());
        return xml;
    }

    public static String addLen(String s){
        //String retSrc = s.substring(43,s.length());
        //retSrc = retSrc.replaceAll(" ","")
        String retSrc = s;
        //retSrc = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" + retSrc;
        int len = (retSrc.length()+"").length();
        String lenSrc = "";
        while (len<6){
            len++;
            lenSrc = lenSrc+"0";
        }
        return lenSrc+retSrc.length()+retSrc;
    }
}
