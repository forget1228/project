package com.xj.ptgd;

import com.xj.ptgd.common.httpXml.HttpsUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.xj.ptgd.common.config.YmlConfig.*;
import static com.xj.ptgd.common.util.MacUtil.addLen;
import static com.xj.ptgd.common.util.MacUtil.toMAC;

public class TestClassG {

    /*@Test
    public void test(){
        String url = "http://192.168.99.16:8081/abk/project/download";
        Map map = new HashMap();
        map.put("groupSubdomain","6");
        map.put("groupSAPrefix","6");
        map.put("templateId","6");
        map.put("data","6");
        String req = HttpsUtils.sendByHttp(map.toString(), url);
        System.out.println(req);
    }*/


    @Test
    public void G9000localhost(){
        String url = "http://localhost:8080/getSendTransaction";
        String tranDateTime = "";
        String operationDate = "";
        Date date = new Date();
        // 指定日期格式
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
        tranDateTime = fmt.format(date);
        System.out.println("DateTime"+tranDateTime);
        fmt = new SimpleDateFormat("yyyyMMdd");
        operationDate = fmt.format(date);

        String xml =
                "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>" +
                        "<In>" +
                        "<Head>" +
                        "<ChnlNo>303</ChnlNo>" +
                        "<FTranCode>9000</FTranCode>" +
                        "<InstID>03030005</InstID>" +
                        "<TrmSeqNum>99999999</TrmSeqNum>" +
                        "<TranDateTime>"+tranDateTime+"</TranDateTime>" +
                        "<ErrCode></ErrCode>" +
                        "</Head>" +
                        "<Body>" +
                        "<operationDate>"+operationDate+"</operationDate>" +
                        "</Body>" +
                        "</In>";
        xml = addLen(xml);
        String req = HttpsUtils.sendByHttp(xml, url);
        System.out.println(req);
    }

    @Test
    public void G9000(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat format2 =  new SimpleDateFormat("yyyyMMddHHmmss");;
        String date = format.format(new Date());
        String time = format2.format(new Date());
        String url = "http://10.100.44.39:8989/alnico11/getSendTransaction";
        String xml ="<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><In><Head><ChnlNo>303</ChnlNo><FTranCode>9000</FTranCode><InstID>03030005</InstID><TrmSeqNum>99999999</TrmSeqNum><TranDateTime>"+time+"</TranDateTime><ErrCode></ErrCode></Head><Body><operationDate>"+date+"</operationDate></Body></In>";
        xml = addLen(xml);
        String req = HttpsUtils.sendByHttp(xml, url);
        System.out.println(req);
    }



    @Test
    public void G371(){
        String url = "http://localhost:8080/tochinacscs";
        String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                "<In>\n" +
                "<Head>\n" +
                "<ChnlNo>111222333</ChnlNo>" +
                "<FTranCode>G371</FTranCode>" +
                "<InstID>G348</InstID>" +
                "<TrmSeqNum>trmSeqNum</TrmSeqNum>" +
                "<TranDateTime>2018</TranDateTime>" +
                "<ErrCode></ErrCode>" +
                "</Head>\n" +
                "<Body>\n" +
                "<CHANNEL_CODE>ptgd</CHANNEL_CODE>\n" +
                "<BRACH_COMPANY>SHANGHAI</BRACH_COMPANY>\n" +
                "<CERTIFICATE_NUMBER>3625199705207816</CERTIFICATE_NUMBER>\n" +
                "<CERTIFICATE_TYPE>1</CERTIFICATE_TYPE>\n" +
                "<CUSTOMER_CODE>124</CUSTOMER_CODE>\n" +
                "<CUSTOMER_TYPE>368客户</CUSTOMER_TYPE>\n" +
                "<CUST_NAME>357a25ee</CUST_NAME>\n" +
                "<CUST_NO>2238</CUST_NO>\n" +
                "<CUST_SORT_NAME>王XX</CUST_SORT_NAME>\n" +
                "<HUAWEI_BRAND_RATE>1</HUAWEI_BRAND_RATE>\n" +
                "<HUAWEI_EXPERIENCE_STORE_NUMBER>22</HUAWEI_EXPERIENCE_STORE_NUMBER>\n" +
                "<HUAWEI_TRADING_VOLUME>151</HUAWEI_TRADING_VOLUME>\n" +
                "<HUAWEI_TRADING_VOLUME_MONTH>22</HUAWEI_TRADING_VOLUME_MONTH>\n" +
                "<LEGAL_COMMISS>wkm</LEGAL_COMMISS>\n" +
                "<LICENCE_NO>1</LICENCE_NO>\n" +
                "<MANAGE_CITY>sh</MANAGE_CITY>\n" +
                "<MANAGE_PROVINCE>sh</MANAGE_PROVINCE>\n" +
                "<OWNER_PHONENO>123456</OWNER_PHONENO>\n" +
                "<SINGLE_TRADING_AMOUNT>123</SINGLE_TRADING_AMOUNT>\n" +
                "<STORE_NUMBER>22</STORE_NUMBER>\n" +
                "<TRADING_TIMES_MONTH>1</TRADING_TIMES_MONTH>\n" +
                "<TRADING_TIMES_YEAR>24</TRADING_TIMES_YEAR>\n" +
                "<TRADING_VOLUME_MONTH>14</TRADING_VOLUME_MONTH>\n" +
                "<TRADING_VOLUME_YEAR>354</TRADING_VOLUME_YEAR>\n" +
                "</Body>\n" +
                "</In>\n";
        xml = "000313" + xml + "24E522BE41CC9C23";
        System.out.println("请求报文"+xml);
        String req = HttpsUtils.sendByHttp(xml, url);
        System.out.println(req);
    }

    @Test
    public void G348(){
        String url = "http://localhost:8080/tochinacscs";
        String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                "<In>" +
                "<Head>" +
                "<ChnlNo>111222333</ChnlNo>" +
                "<FTranCode>G348</FTranCode>" +
                "<InstID>G348</InstID>" +
                "<TrmSeqNum>trmSeqNum</TrmSeqNum>" +
                "<TranDateTime>2018</TranDateTime>" +
                "<ErrCode></ErrCode>" +
                "</Head>" +
                "<Body>" +
                "<CHANNEL_CODE>ptgd</CHANNEL_CODE>" +
                "<CUSTOMER_CODE>56985693654289</CUSTOMER_CODE>" +
                "<CUSTOMER_NAME>name</CUSTOMER_NAME>" +
                "<CREDITLINE_STATE>1</CREDITLINE_STATE>" +
                "<TOTAL_LIMIT>10000</TOTAL_LIMIT>" +
                "<AVAILABLE_LIMIT>1</AVAILABLE_LIMIT>" +
                "<LIMIT_BEGIN_DATE>20180101</LIMIT_BEGIN_DATE>" +
                "<LIMIT_END_DATE>20180102</LIMIT_END_DATE>" +
                "</Body>" +
                "</In>";
        xml =  "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                "000313" + xml + "32E5677A7BF5B546";
        System.out.println("请求报文"+xml);
        String req = HttpsUtils.sendByHttp(xml, url);
        System.out.println(req);
    }

    @Test
    public void G353(){
        String url = "http://localhost:8080/tochinacscs";
        String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                "<In>" +
                "<Head>" +
                "<ChnlNo>渠道号：共3位长，由银行提供。</ChnlNo>" +
                "<FTranCode></FTranCode>" +
                "<InstID>机构号：共8位长，由银行提供。</InstID>" +
                "<TrmSeqNum>终端流水号：为终端流水号</TrmSeqNum>" +
                "<TranDateTime>交易日期时间：</TranDateTime>" +
                "<ErrCode>错误码：失败应答报文时，有值</ErrCode>" +
                "</Head>" +
                "<Body>" +
                "<CHANNEL_CODE>ptgd</CHANNEL_CODE>" +
                "<CUSTOMER_CODE>56985693654289</CUSTOMER_CODE>" +
                "<CUSTOMER_NAME>na</CUSTOMER_NAME>  " +
                "<ORDER_NO>00006</ORDER_NO>" +
                "<ORDER_AMOUNT>1</ORDER_AMOUNT>" +
                "<ORDER_DATE>20180806155300</ORDER_DATE>" +
                "<SELF_PAY>125</SELF_PAY>" +
                "<SELF_PAY_RATE>1</SELF_PAY_RATE>" +
                "<BRACH_COMPANY>所</BRACH_COMPANY>" +
                "<BRACH_ACCOUNT>分公司账户</BRACH_ACCOUNT>" +
                "</Body>" +
                "</In>";
        xml =
                "000313" + xml + "32E5677A7BF5B545";
        System.out.println("请求报文"+xml);
        String req = HttpsUtils.sendByHttp(xml, url);
        System.out.println(req);
    }

    @Test
    public void G362(){
        String url = "http://localhost:8080/tochinacscs";
        String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                "<In>\n" +
                "<Head>\n" +
                "<ChnlNo>渠道号：共3位长，由银行提供。</ChnlNo>\n" +
                "<FTranCode>G362</FTranCode>\n" +
                "<InstID>机构号：共8位长，由银行提供。</InstID>\n" +
                "<TrmSeqNum>终端流水号：为终端流水号</TrmSeqNum>\n" +
                "<TranDateTime>交易日期时间：</TranDateTime>\n" +
                "<ErrCode>错误码：失败应答报文时，有值</ErrCode>\n" +
                "</Head>\n" +
                "<Body>\n" +
                "<CHANNEL_CODE>ptgd</CHANNEL_CODE>\n" +
                "<ORDER_NO>00004</ORDER_NO>\n" +
                "<CUSTOMER_NAME>name</CUSTOMER_NAME>\n" +
                "<CUSTOMER_CODE>56985693654289</CUSTOMER_CODE>\n" +
                "</Body>\n" +
                "</In>";
        xml = "000313" + xml + "32E5677A7BF5B546";
        System.out.println("请求报文"+xml);
        String req = HttpsUtils.sendByHttp(xml, url);
        System.out.println(req);
    }

    @Test
    public void G373(){
        String url = "http://localhost:8080/tochinacscs";
        String xml = "000817<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><In><Head><ChnlNo>303</ChnlNo><FTranCode>G373</FTranCode><InstID>03030005</InstID><TrmSeqNum>915313402</TrmSeqNum><TranDateTime>20181014151301</TranDateTime><ErrCode></ErrCode></Head><Body><CHANNEL_CODE>03030005</CHANNEL_CODE><CUSTOMER_CODE>9151010067969479XM</CUSTOMER_CODE><CUSTOMER_NAME>成都市倍佳骏业通讯有限公司</CUSTOMER_NAME><ORDER_NO>o15340004</ORDER_NO><ORDER_STATUS>9</ORDER_STATUS><REJECT_REASON>放款失败:起息日期【20181014】必须是当前业务日期【20181013】</REJECT_REASON><EXECUTE_RATE></EXECUTE_RATE><LOUS_NO></LOUS_NO><LOAN_MONEY></LOAN_MONEY><LOAN_START_DATE></LOAN_START_DATE><LOAN_END_DATE></LOAN_END_DATE><ATTRIBUTE1></ATTRIBUTE1><ATTRIBUTE2></ATTRIBUTE2><ATTRIBUTE3></ATTRIBUTE3><ATTRIBUTE4></ATTRIBUTE4><ATTRIBUTE5></ATTRIBUTE5></Body></In>415B9E79E44C8CFF";
        //xml = "000313" + xml + "FD6CC234821D4C62";
        System.out.println("请求报文"+xml);
        String req = HttpsUtils.sendByHttp(xml, url);
        System.out.println(req);
    }

    @Test
    public void G374(){
        String url = "http://localhost:8080/tochinacscs";
        String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                "<Head>\n" +
                "<ChnlNo>123</ChnlNo>\n" +
                "<FTranCode>G374</FTranCode>\n" +
                "<TrmSeqNum>G374</TrmSeqNum>\n" +
                "<TranDateTime>2018/01/01</TranDateTime>\n" +
                "</Head>\n" +
                "<Body>\n" +
                "<CHANNEL_CODE>1001</CHANNEL_CODE>\n" +
                "<REPAY_NO>3625199705207816</REPAY_NO>\n" +
                "<LOAN_NO>1234567890</LOAN_NO>\n" +
                "<LOAN_BALANCE>2236</LOAN_BALANCE>\n" +
                "<REPAY_AMOUNT>1000</REPAY_AMOUNT>\n" +
                "<INTEREST_MONEY>10</INTEREST_MONEY>\n" +
                "<REPAY_DATETIME>20180101162000</REPAY_DATETIME>\n" +
                "<HQ_FLAG>0</HQ_FLAG>" +
                "<JIEJU_END_DATE>20190101</JIEJU_END_DATE>\n" +
                "</Body>\n" +
                "</In>";
        xml = "000313" + xml + "32E5677A7BF5B546";
        System.out.println("请求报文"+xml);
        String req = HttpsUtils.sendByHttp(xml, url);
        System.out.println(req);
    }

    @Test
    public void G390(){
        String url = "http://localhost:8080/tochinacscs";
        String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                "<In>\n" +
                "<Head>\n" +
                "<ChnlNo>渠道号：共3位长，由银行提供。</ChnlNo>\n" +
                "<FTranCode>G390</FTranCode>\n" +
                "<InstID>机构号：共8位长，由银行提供。</InstID>\n" +
                "<TrmSeqNum>终端流水号：为终端流水号</TrmSeqNum>\n" +
                "<TranDateTime>交易日期时间：</TranDateTime>\n" +
                "<ErrCode>错误码：失败应答报文时，有值</ErrCode>\n" +
                "</Head>\n" +
                "<Body>\n" +
                "<CHANNEL_CODE>ptg</CHANNEL_CODE>\n" +
                "<OPERATION_TYPE>2</OPERATION_TYPE>\n" +
                "<REFUSAL_REASON>2311</REFUSAL_REASON>\n" +
                "<CONTRACT_BATCH_NUMBER>00003</CONTRACT_BATCH_NUMBER>\n" +
                "<CUSTOMER_CODE>911101080717398659</CUSTOMER_CODE>\n" +
                "<CUSTOMER_NAME>客户名称21</CUSTOMER_NAME>\n" +
                "<AGREEMENT_NUMBER>四方协议编号21</AGREEMENT_NUMBER>\n" +
                "<CUSTOMER_REPRESENTATIVE>客户法定代表人21</CUSTOMER_REPRESENTATIVE>\n" +
                "<PRINCIPAL_AGENT>客户委托代理人21</PRINCIPAL_AGENT>\n" +
                "<CUSTOMER_ADDRESS>客户地址21</CUSTOMER_ADDRESS>\n" +
                "<CUSTOMER_PHONE>客户电话21</CUSTOMER_PHONE>\n" +
                "<CONTRACT_NO>最高额保证合同编号21</CONTRACT_NO>\n" +
                "<MAX_CREDIT_LINE>1000021</MAX_CREDIT_LINE>\n" +
                "<CUSTOMER_REGISTRATION_ADDRESS>address21</CUSTOMER_REGISTRATION_ADDRESS>\n" +
                "<CUSTOMER_POSTAL_ADDRESS>CUSTOMER_POSTAL_ADDRESS21</CUSTOMER_POSTAL_ADDRESS>\n" +
                "<CUSTOMER_CONTACT>CUSTOMER_CONTACT21</CUSTOMER_CONTACT>\n" +
                "<CONTACT_NUMBER>CONTACT_NUMBER21</CONTACT_NUMBER>\n" +
                "<CONTACT_FAX>CONTACT_FAX21</CONTACT_FAX>\n" +
                "<CONTACT_POSTAL_CODE>1</CONTACT_POSTAL_CODE>\n" +
                "<TOTAL_CREDIT>999921</TOTAL_CREDIT>\n" +
                "</Body>\n" +
                "</In>";
        xml = "000313" + xml + "32E5677A7BF5B546";
        System.out.println("请求报文"+xml);
        String req = HttpsUtils.sendByHttp(xml, url);
        System.out.println(req);
    }

    @Test
    public void G392(){
        String url = "http://localhost:8080/tochinacscs";
        String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>" +
                "<In>\n" +
                "<Head>\n" +
                "<ChnlNo>渠道号：共3位长，由银行提供。</ChnlNo>\n" +
                "<FTranCode>G392</FTranCode>\n" +
                "<InstID>机构号：共8位长，由银行提供。</InstID>\n" +
                "<TrmSeqNum>终端流水号：为终端流水号</TrmSeqNum>\n" +
                "<TranDateTime>交易日期时间：</TranDateTime>\n" +
                "<ErrCode>错误码：失败应答报文时，有值</ErrCode>\n" +
                "</Head>\n" +
                "<Body>\n" +
                "<CHANNEL_CODE>ptgd</CHANNEL_CODE>\n" +
                "<CUSTOMER_CODE>911101080717398659</CUSTOMER_CODE>\n" +
                "<CUSTOMER_NAME>客户2</CUSTOMER_NAME>\n" +
                "<CONTRACT_BATCH_NUMBER>00003</CONTRACT_BATCH_NUMBER>\n" +
                "<LIST_OBJ>\n" +
                "<FILE_NAME>ptgd</FILE_NAME>\n" +
                "<FILE_PATH>ptgd</FILE_PATH>\n" +
                "<CONTRACT_TYPE>1</CONTRACT_TYPE>\n" +
                "</LIST_OBJ>\n" +
                "<LIST_OBJ>\n" +
                "<FILE_NAME>ptgd22</FILE_NAME>\n" +
                "<FILE_PATH>ptgd22</FILE_PATH>\n" +
                "<CONTRACT_TYPE>2</CONTRACT_TYPE>\n" +
                "</LIST_OBJ>\n" +
                "<LIST_OBJ>\n" +
                "<FILE_NAME>ptgd33</FILE_NAME>\n" +
                "<FILE_PATH>ptgd33</FILE_PATH>\n" +
                "<CONTRACT_TYPE>3</CONTRACT_TYPE>\n" +
                "</LIST_OBJ>\n" +
                "<LIST_OBJ>\n" +
                "<FILE_NAME>ptgd44</FILE_NAME>\n" +
                "<FILE_PATH>ptgd44</FILE_PATH>\n" +
                "<CONTRACT_TYPE>4</CONTRACT_TYPE>\n" +
                "</LIST_OBJ>\n" +
                "</Body>\n" +
                "</In>";
        xml =  "000313" + xml + "32E5677A7BF5B546";
        System.out.println("请求报文"+xml);
        String req = HttpsUtils.sendByHttp(xml, url);
        System.out.println(req);
    }
}
