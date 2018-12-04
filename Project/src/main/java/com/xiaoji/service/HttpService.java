package com.xiaoji.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Service
public class HttpService {
    private static final int TIME_OUT = 8 * 1000;                               // 超时时间
    private static final String CHARSET = "utf-8";                              // 编码格式
    private static final String PREFIX = "--";                                  // 前缀
    private static final String BOUNDARY = "letv";                              // 边界标识 随机生成
    private static final String CONTENT_TYPE = "multipart/form-data";        // 内容类型
    private static final String LINE_END = "\r\n";                              // 换行


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



    public Map<String, Object> uploadFile(File file,Map<String, String> data, String requestUrl) {
        Map<String, Object> httpsResult = null;
        InputStream is = null;
        DataOutputStream dos = null;

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            if (data != null && !data.isEmpty()) {
                conn.setDoOutput(true);
            } else {
                conn.setDoOutput(false);
            }

            // 设置请求头参数
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE+";boundary=" + BOUNDARY);

            /**
             * 请求体
             */
            // 上传参数
            dos = new DataOutputStream(conn.getOutputStream());
            //getStrParams()为一个
            dos.writeBytes(getStrParams(data).toString() );
            dos.flush();

            // 文件上传
            StringBuilder fileSb = new StringBuilder();
            fileSb.append(PREFIX)
                    .append(BOUNDARY)
                    .append(LINE_END)
                    /**
                     * 这里重点注意： name 里面的值为服务端需要的 key 只有这个key 才可以得到对应的文件
                     * filename 是文件的名字，包含后缀名的 比如:abc.png
                     */
                    .append("Content-Disposition: form-data; name=\"file\"; filename=\""
                            + file.getName() + "\"" + LINE_END)
                    .append("Content-Type: image/jpg" + LINE_END) // 此处的ContentType不同于 请求头 中Content-Type
                    .append("Content-Transfer-Encoding: 8bit" + LINE_END)
                    .append(LINE_END);// 参数头设置完以后需要两个换行，然后才是参数内容
            dos.writeBytes(fileSb.toString());
            dos.flush();

            is = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1){
                dos.write(buffer,0,len);
            }
            is.close();
            dos.writeBytes(LINE_END);


            // 请求结束标志
            dos.writeBytes(PREFIX + BOUNDARY + PREFIX + LINE_END);
            dos.flush();
            dos.close();
            System.out.println( "postResponseCode() = "+conn.getResponseCode() );

            // 读取服务器返回信息
            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                String result = getContent(is, "utf-8");
                JSONObject jsonResult = JSON.parseObject(result);
                httpsResult = jsonResult.toJavaObject(Map.class);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return httpsResult;
    }

    /**
     * 对post参数进行编码处理
     * */
    private static StringBuilder getStrParams(Map<String,String> strParams){
        StringBuilder strSb = new StringBuilder();
        for (Map.Entry<String, String> entry : strParams.entrySet() ){
            strSb.append(PREFIX)
                    .append(BOUNDARY)
                    .append(LINE_END)
                    .append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINE_END)
                    .append("Content-Type: text/plain; charset=" + CHARSET + LINE_END)
                    .append("Content-Transfer-Encoding: 8bit" + LINE_END)
                    .append(LINE_END)// 参数头设置完以后需要两个换行，然后才是参数内容
                    .append(entry.getValue())
                    .append(LINE_END);
        }
        return strSb;
    }
}
