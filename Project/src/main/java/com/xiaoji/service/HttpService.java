package com.xiaoji.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@Service
public class HttpService {
    public Map<String, Object> https(Map<String, String[]> data, String requestUrl) {
        Map<String, Object> httpsResult = null;
        InputStream is = null;
        OutputStream os = null;

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoInput(true);
            if (data != null && !data.isEmpty()) {
                conn.setDoOutput(true);
            } else {
                conn.setDoOutput(false);
            }
            conn.setRequestProperty("Content-type", "application/json");
            conn.setRequestMethod("POST");

            if (data != null && !data.isEmpty()) {
                os = conn.getOutputStream();
                JSONObject jsondata = (JSONObject) JSON.toJSON(data);

                os.write(jsondata.toJSONString().getBytes());
                os.flush();
            }

            is = conn.getInputStream();
            String result = getContent(is, "utf-8");
            JSONObject jsonResult = JSON.parseObject(result);
            httpsResult = jsonResult.toJavaObject(Map.class);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return httpsResult;
    }

    public static String getContent(InputStream is, String charset) {
        String pageString = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        StringBuffer sb = null;
        try {
            isr = new InputStreamReader(is, charset);
            br = new BufferedReader(isr);
            sb = new StringBuffer();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            pageString = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            sb = null;
        }

        return pageString;
    }
}
