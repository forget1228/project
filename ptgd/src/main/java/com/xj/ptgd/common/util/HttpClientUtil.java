package com.xj.ptgd.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

 /**
  * HttpClientUtil http请求工具类
  * @author wkm
  * @since 2018/7/31
  */
public class HttpClientUtil {
	private final static String Charset = "UTF-8";
	
	public static String doHttpPostAction(String url,  String qtype,  Map<String, String> params) throws Exception {
		String res = "";
		BufferedReader bufferedReader = null;
		PrintWriter printWriter = null;
		StringBuffer responseResult = new StringBuffer();
		
		//拼接URL
		String URL = String.format("%s/%s", url, qtype);
		
		try {
			URL realUrl = new URL(URL);
			HttpURLConnection httpConnection = (HttpURLConnection) realUrl.openConnection();
			
			// 参数按Key排序
			String content = UrlUtil.paramsSortString(params);
			
			if (content != null && !content.isEmpty()) {
				
				httpConnection.setDoInput(true);
				httpConnection.setDoOutput(true);
				httpConnection.setConnectTimeout(6000);
				httpConnection.setReadTimeout(60000);
				httpConnection.setRequestMethod("POST");
				httpConnection.setRequestProperty("Content-Length", String.valueOf(content.getBytes().length));
				httpConnection.setUseCaches(false);
				System.out.println(">>>>>>>>" + "发起http post请求@@	———— 	请求参数--->>" + content + "	*******	  请求url------>>" + url + "<<<<<<<<");
				
				
				printWriter = new PrintWriter(new OutputStreamWriter(httpConnection.getOutputStream(), Charset));
				printWriter.write(content);
				printWriter.flush();
			}
			int responseCode = httpConnection.getResponseCode();
			
			// 根据ResponseCode判断连接是否成功
			if (responseCode == HttpURLConnection.HTTP_OK) {
				InputStream urlStream = httpConnection.getInputStream();
				bufferedReader = new BufferedReader(new InputStreamReader(urlStream, Charset));
				String sCurrentLine = "";

				while ((sCurrentLine = bufferedReader.readLine()) != null) {
					responseResult.append(sCurrentLine);
				}
				res = responseResult.toString();
				System.out.println(">>>>>" + " http post 请求成功" + "<<<<<");
			} else {
				System.out.println(" http post请求 连接异常,连接返回码:" + responseCode);
			}

		} catch (Exception e) {
		
		}

		return res;
	}

}
