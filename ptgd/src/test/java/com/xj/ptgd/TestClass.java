package com.xj.ptgd;

import com.xj.ptgd.common.httpXml.HttpsUtils;
import com.xj.ptgd.common.result.ResultUtil;
import com.xj.ptgd.common.util.JaxbUtil;
import com.xj.ptgd.entity.base.XMLHeadDto;
import com.xj.ptgd.entity.body.Dealer;
import com.xj.ptgd.entity.body.User;
import com.xj.ptgd.entity.in.DealerXMLIn;
import com.xj.ptgd.entity.out.TestXMlOut;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import static com.xj.ptgd.common.util.MacUtil.addLen;


public class TestClass{


	@Test
	public void G348(){
		String url = "http://localhost:8080/tochinacscs";
		String xml = "000763<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><In><Head><ChnlNo>303</ChnlNo><FTranCode>G373</FTranCode><InstID>03030005</InstID><TrmSeqNum>914872348</TrmSeqNum><TranDateTime>20180930165518</TranDateTime><ErrCode></ErrCode></Head><Body><CHANNEL_CODE>03030005</CHANNEL_CODE><CUSTOMER_CODE>91320585571385557C</CUSTOMER_CODE><CUSTOMER_NAME>太仓市远舰化纤有限</CUSTOMER_NAME><ORDER_NO>201809171958016032001</ORDER_NO><ORDER_STATUS>9</ORDER_STATUS><REJECT_REASON>1</REJECT_REASON><EXECUTE_RATE></EXECUTE_RATE><LOUS_NO></LOUS_NO><LOAN_MONEY></LOAN_MONEY><LOAN_START_DATE></LOAN_START_DATE><LOAN_END_DATE></LOAN_END_DATE><ATTRIBUTE1></ATTRIBUTE1><ATTRIBUTE2></ATTRIBUTE2><ATTRIBUTE3></ATTRIBUTE3><ATTRIBUTE4></ATTRIBUTE4><ATTRIBUTE5></ATTRIBUTE5></Body></In>" +
				"C0DF5E5A88123DD2";
		String req = HttpsUtils.sendByHttp(xml, url);
		System.out.println(req);
	}
	@Test
	public void G9000(){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat format2 =  new SimpleDateFormat("yyyyMMddHHmmss");;
		String date = format.format(new Date());
		String time = format2.format(new Date());
		String url = "http://localhost:8080/getSendTransaction";
		String xml ="<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><In><Head><ChnlNo>303</ChnlNo><FTranCode>9000</FTranCode><InstID>03030005</InstID><TrmSeqNum>99999999</TrmSeqNum><TranDateTime>"+time+"</TranDateTime><ErrCode></ErrCode></Head><Body><operationDate>"+date+"</operationDate></Body></In>";
		xml = addLen(xml);
		String req = HttpsUtils.sendByHttp(xml, url);
		System.out.println(req);
	}

	@Test
	public void showMarshaller() {
		String s = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>1";

		System.out.println();
        String sDateStr="20140124103709";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            System.out.println(sdf2.format(sdf.parse(sDateStr)));
        } catch (ParseException e) {
            //做转化失败的操作
        }
    }


	/**
	 *
	 */
	@Test
	public void ObjToXml() {
        TestXMlOut ret = new TestXMlOut();
        XMLHeadDto head = new XMLHeadDto();
        User user = new User();
        user.setUserId(12243);
        head.setChnlNo("123");
        ret.setHead(head);
        ret.setBody(user);
        String srt = ResultUtil.getResult(ret,TestXMlOut.class);
		String srt2 = "";
        try {
			System.out.println(ret.getClass().getName());
        	srt2 = ResultUtil.setHeadAndGetResult(head,ret,User.class);
		}catch (Exception e){
        	System.out.println(e.getMessage());
		}
		srt2 = ResultUtil.setHeadAndGetResult(head,ret,User.class);
		System.out.println(srt2);

     /*   JaxbUtil ju2 =new JaxbUtil(TestXMlOut.class);
        String s = ju2.toXml(ret,"ISO-8859-1");
        System.out.println(s);*/
	}


	@Test
	public void G371(){
		String url = "http://192.168.99.17:8080/tochinacscs";
		String xml = retXml();
		String req = HttpsUtils.sendByHttp(xml, url);
		System.out.println(req);
	}



	@Test
	public void G373(){
		String url = "http://192.168.99.17:8080/amarspi/tochinacscs";
		String xml = "000313<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
				"<In>\n" +
				"    <Head>\n" +
				"        <ChnlNo>123</ChnlNo>\n" +
				"        <FTranCode>G373</FTranCode>\n" +
				"        <TrmSeqNum>G373</TrmSeqNum>\n" +
				"        <TranDateTime>2018/01/01</TranDateTime>\n" +
				"    </Head>\n" +
				"    <Body>\n" +
				"        <CHANNEL_CODE>1001</CHANNEL_CODE>\n" +  //接入机构号
				"        <CUSTOMER_CODE>1234567890</CUSTOMER_CODE>\n" + //社会统一信用代码
				"        <CUSTOMER_NAME>1234567890</CUSTOMER_NAME>\n" +            //客户名称

				"        <OWNER_NAME>王XX</OWNER_NAME>\n" +            //企业主姓名
				"        <OWNER_IDCARD>362519970520XXXX</OWNER_IDCARD>\n" +            //企业主身份证号
				"        <OWNER_PHONENO>18897948788</OWNER_PHONENO>\n" +            //企业主手机号

				"        <ORDER_NO>2236</ORDER_NO>\n" +  //订单编号
				"        <ORDER_STATUS>4</ORDER_STATUS>\n" +  //订单状态
				"        <REJECT_REASON>信用额度</REJECT_REASON>\n" +  //受理拒绝原因
				"        <EXECUTE_RATE>15.6</EXECUTE_RATE>\n" +  // 执行利率
				"        <LOUS_NO>1234567890</LOUS_NO>\n" + 					//  借据编号
				"        <LOAN_MONEY>1000</LOAN_MONEY>\n" +  //放款金额
				"        <LOAN_START_DATE>20180101</LOAN_START_DATE>\n" +  //放款起始日期
				"        <LOAN_END_DATE>20190101</LOAN_END_DATE>\n" +  //放款到期日期
				"    </Body>\n" +
				"</In>32E5677A7BF5B546\n";
		String req = HttpsUtils.sendByHttp(xml, url);
		System.out.println(req);
	}

	@Test
	public void G374(){
		String url = "http://192.168.99.17:8080/tochinacscs";
		String xml = "000313<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
				"<In>\n" +
				"    <Head>\n" +
				"        <ChnlNo>123</ChnlNo>\n" +
				"        <FTranCode>G374</FTranCode>\n" +
				"        <TrmSeqNum>G374</TrmSeqNum>\n" +
				"        <TranDateTime>2018/01/01</TranDateTime>\n" +
				"    </Head>\n" +
				"    <Body>\n" +
				"        <CHANNEL_CODE>1001</CHANNEL_CODE>\n" +  //接入机构号
				"        <REPAY_NO>3625199705207816</REPAY_NO>\n" + //还款流水号
				"        <LOAN_NO>1234567890</LOAN_NO>\n" +            //借据号
				"        <LOAN_BALANCE>2236</LOAN_BALANCE>\n" +  //借据余额
				"        <REPAY_AMOUNT>1000</REPAY_AMOUNT>\n" +  //还款本金金额
				"        <INTEREST_MONEY>10</INTEREST_MONEY>\n" +  //还息金额
				"        <REPAY_DATETIME>20180101162000</REPAY_DATETIME>\n" +  // 还款时间
				"        <HQ_FLAG>0</HQ_FLAG>\n" + 					//  借据是否结清
				"        <JIEJU_END_DATE>20190101</JIEJU_END_DATE>\n" +  //借据到期日
				"    </Body>\n" +
				"</In>32E5677A7BF5B546\n";
		String req = HttpsUtils.sendByHttp(xml, url);
		System.out.println(req);
	}

	private static String objToXML(){
		DealerXMLIn xmlIn = new DealerXMLIn();

		XMLHeadDto headDto = new XMLHeadDto();
		headDto.setChnlNo("123");
		headDto.setFTranCode("371"); //交易码
		headDto.setTranDateTime("2018/01/01");
		headDto.setTrmSeqNum("G371");
		Dealer dealer = new Dealer();

		dealer.setCUST_NO("123"); //客户编号
		dealer.setCUST_NAME("357a25ee"); //客户名称
		dealer.setCUST_SORT_NAME("wkm"); //客户简称
		dealer.setLICENCE_NO("1234"); //营业执照
		dealer.setCUSTOMER_CODE("1234"); //统一社会代码
		dealer.setHUAWEI_EXPERIENCE_STORE_NUMBER("22"); //华为体验店数量
		dealer.setSTORE_NUMBER("22"); //门店数量
		dealer.setLEGAL_COMMISS("wkm"); //
		dealer.setCERTIFICATE_TYPE("1");
		dealer.setCERTIFICATE_NUMBER("3625199705207816");
		dealer.setOWNER_PHONENO("123456");
		dealer.setBRACH_COMPANY("SHANGHAI"); //分公司
		dealer.setMANAGE_PROVINCE("sh"); //经营省份
		dealer.setMANAGE_CITY("sh"); //经营地市
		dealer.setCUSTOMER_TYPE("1"); //客户分类
		dealer.setTRADING_VOLUME_YEAR("354"); //近2年年化交易额
		dealer.setTRADING_VOLUME_MONTH(354/24+""); //近2年月均交易额
		dealer.setTRADING_TIMES_YEAR("24"); //近2年年化交易频次
		dealer.setTRADING_TIMES_MONTH("1"); //近2年月均交易频次
		dealer.setSINGLE_TRADING_AMOUNT("123"); //近2年平均单笔交易额
		dealer.setHUAWEI_TRADING_VOLUME("151"); //近2年华为品牌交易额
		dealer.setHUAWEI_TRADING_VOLUME_MONTH("22"); //近2年华为品牌月均交易额
		dealer.setHUAWEI_BRAND_RATE("1"); //近2年华为品牌提货占比
		xmlIn.setHead(headDto);
		xmlIn.setBody(dealer);
		String xml = "";

		JaxbUtil ju2 = null;
		try {
			ju2 = new JaxbUtil(DealerXMLIn.class);
			xml = ju2.toXml(xmlIn,"ISO-8859-1","<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
//			result = ju2.toXml(ret,"UTF-8","<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
			System.out.println();
			System.out.println(xml);
			System.out.println();

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xml;
	}

	private static String retXml(){
		return "000313<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" +
				"<In>\n" +
				"    <Head>\n" +
				"        <ChnlNo>123</ChnlNo>\n" +
				"        <FTranCode>G371</FTranCode>\n" +
				"        <TrmSeqNum>G371</TrmSeqNum>\n" +
				"        <TranDateTime>2018/01/01</TranDateTime>\n" +
				"    </Head>\n" +
				"    <Body>\n" +
				"        <BRACH_COMPANY>SHANGHAI</BRACH_COMPANY>\n" +
				"        <CERTIFICATE_NUMBER>3625199705207816</CERTIFICATE_NUMBER>\n" +
				"        <CERTIFICATE_TYPE>1</CERTIFICATE_TYPE>\n" +
				"        <CUSTOMER_CODE>2236</CUSTOMER_CODE>\n" +
				"        <CUSTOMER_TYPE>1</CUSTOMER_TYPE>\n" +
				"        <CUST_NAME>357a25ee</CUST_NAME>\n" +
				"        <CUST_NO>2236</CUST_NO>\n" +
				"        <CUST_SORT_NAME>王XX</CUST_SORT_NAME>\n" +
				"        <HUAWEI_BRAND_RATE>1</HUAWEI_BRAND_RATE>\n" +
				"        <HUAWEI_EXPERIENCE_STORE_NUMBER>22</HUAWEI_EXPERIENCE_STORE_NUMBER>\n" +
				"        <HUAWEI_TRADING_VOLUME>151</HUAWEI_TRADING_VOLUME>\n" +
				"        <HUAWEI_TRADING_VOLUME_MONTH>22</HUAWEI_TRADING_VOLUME_MONTH>\n" +
				"        <LEGAL_COMMISS>wkm</LEGAL_COMMISS>\n" +
				"        <LICENCE_NO>1234</LICENCE_NO>\n" +
				"        <MANAGE_CITY>sh</MANAGE_CITY>\n" +
				"        <MANAGE_PROVINCE>sh</MANAGE_PROVINCE>\n" +
				"        <OWNER_PHONENO>123456</OWNER_PHONENO>\n" +
				"        <SINGLE_TRADING_AMOUNT>123</SINGLE_TRADING_AMOUNT>\n" +
				"        <STORE_NUMBER>22</STORE_NUMBER>\n" +
				"        <TRADING_TIMES_MONTH>1</TRADING_TIMES_MONTH>\n" +
				"        <TRADING_TIMES_YEAR>24</TRADING_TIMES_YEAR>\n" +
				"        <TRADING_VOLUME_MONTH>14</TRADING_VOLUME_MONTH>\n" +
				"        <TRADING_VOLUME_YEAR>354</TRADING_VOLUME_YEAR>\n" +
				"    </Body>\n" +
				"</In>32E5677A7BF5B546\n";
	}

	private String addMax(String xml){
		String ret  =  "000313" + xml + "32E5677A7BF5B546";
		return ret;
	}


	@Test
	public void testG371Signature() throws Exception {
		int j = 0;
		while (j<50){
			Thread thread = new Thread(new Runnable(){
				private int i = 0;
				@Override
				public void run() {
				/*	for (i = 0; i < 5; i++) {
						System.out.println(Thread.currentThread().getName() + " " + i);
					}*/
//					System.out.println(Thread.currentThread().getName());

				}
			});
			thread.start();
			j++;
		}

	}




    final static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args){
        CountDownLatch latch=new CountDownLatch(1);//模拟5人并发请求，用户钱包

        for(int i=0;i<5;i++){//模拟5个用户
            AnalogUser analogUser = new AnalogUser("user"+i,"58899dcd-46b0-4b16-82df-bdfd0d953bfb","1","20.024",latch);
            analogUser.start();
        }
        latch.countDown();//计数器減一  所有线程释放 并发访问。
        System.out.println("所有模拟请求结束  at "+sdf.format(new Date()));

    }

    static class AnalogUser extends Thread{
        String workerName;//模拟用户姓名
        String openId;
        String openType;
        String amount;
        CountDownLatch latch;

        public AnalogUser(String workerName, String openId, String openType, String amount,
                          CountDownLatch latch) {
            super();
            this.workerName = workerName;
            this.openId = openId;
            this.openType = openType;
            this.amount = amount;
            this.latch = latch;
        }
        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                latch.await(); //一直阻塞当前线程，直到计时器的值为0
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            post();//发送post 请求



        }

        public void post(){
            String result = "";
            System.out.println("模拟用户： "+workerName+" 开始发送模拟请求  at "+sdf.format(new Date()));
            String url = "http://192.168.99.17:8080/tochinacscs";
            String xml = retXml();
            result = HttpsUtils.sendByHttp(xml, url);
            System.out.println(Thread.currentThread().getName()+" : "+result);  System.out.println("操作结果："+result);
            System.out.println("模拟用户： "+workerName+" 模拟请求结束  at "+sdf.format(new Date()));
        }

    }

}