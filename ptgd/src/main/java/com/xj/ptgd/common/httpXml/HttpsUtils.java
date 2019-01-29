package com.xj.ptgd.common.httpXml;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpsUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpsUtils.class);
    static String Charset = "UTF-8";
    static CloseableHttpClient httpClient;
    static CloseableHttpResponse httpResponse;

    public static CloseableHttpClient createSSLClientDefault() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();

    }

    /**
     * 发送https请求
     *
//     * @param jsonPara
     * @throws Exception
     */
    public static String sendByHttp(String params, String url) {
        try {
            HttpPost httpPost = new HttpPost(url);
//            httpPost.addHeader("",);
            httpPost.addHeader("Content-type","application/xml;charset=gb18030");
            StringEntity body  = new StringEntity(params,"gb18030");
            logger.info("创建请求httpPost-URL={},params={}", url, params,body);
            httpPost.setEntity(body);
            httpClient = HttpsUtils.createSSLClientDefault();
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                String jsObject = EntityUtils.toString(httpEntity, Charset);
                return jsObject;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                httpResponse.close();
                httpClient.close();
                logger.info("请求流关闭完成");
            } catch (IOException e) {
                logger.info("请求流关闭出错");
                e.printStackTrace();
            }
        }
    }



    /**
     * 发送http请求
     *
     * @throws Exception
     */
    public static String doHttpPostAction(Map<String, String> paramMap , String url) throws Exception{
        String charset = "gb18030";
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String str = null;

        // 获取连接对象
        httpClient = HttpClients.createDefault();
        httpPost = new HttpPost(url);

        // 设置参数
        List<NameValuePair> list = new ArrayList<>();
        Iterator iterator = paramMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> entry = (Map.Entry<String,String>) iterator.next();
            list.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
        }
        if (list.size()>0){
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list,charset);
            httpPost.setEntity(urlEncodedFormEntity);
        }
        HttpResponse response = httpClient.execute(httpPost);
        if (response != null){
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null){
                str = EntityUtils.toString(resEntity,charset);
            }
        }

        return str;
    }
}
