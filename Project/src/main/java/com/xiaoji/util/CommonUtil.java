package com.xiaoji.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoji.model.SheetData;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xiaoji.configuration.config.YmlConfig.ABL_PATH;

public class CommonUtil {

    /**
     * 根据“文件名的后缀”获取文件内容类型（而非根据File.getContentType()读取的文件类型）
     * @param returnFileName 带验证的文件名
     * @return 返回文件类型
     */
    public static String getContentType(String returnFileName) {
        String contentType = "application/octet-stream";
        if (returnFileName.lastIndexOf(".") < 0)
            return contentType;
        returnFileName = returnFileName.toLowerCase();
        returnFileName = returnFileName.substring(returnFileName.lastIndexOf(".") + 1);
        if (returnFileName.equals("html") || returnFileName.equals("htm") || returnFileName.equals("shtml")) {
            contentType = "text/html";
        } else if (returnFileName.equals("apk")) {
            contentType = "application/vnd.android.package-archive";
        } else if (returnFileName.equals("sis")) {
            contentType = "application/vnd.symbian.install";
        } else if (returnFileName.equals("sisx")) {
            contentType = "application/vnd.symbian.install";
        } else if (returnFileName.equals("exe")) {
            contentType = "application/x-msdownload";
        } else if (returnFileName.equals("msi")) {
            contentType = "application/x-msdownload";
        } else if (returnFileName.equals("css")) {
            contentType = "text/css";
        } else if (returnFileName.equals("xml")) {
            contentType = "text/xml";
        } else if (returnFileName.equals("gif")) {
            contentType = "image/gif";
        } else if (returnFileName.equals("jpeg") || returnFileName.equals("jpg")) {
            contentType = "image/jpeg";
        } else if (returnFileName.equals("js")) {
            contentType = "application/x-javascript";
        } else if (returnFileName.equals("atom")) {
            contentType = "application/atom+xml";
        } else if (returnFileName.equals("rss")) {
            contentType = "application/rss+xml";
        } else if (returnFileName.equals("mml")) {
            contentType = "text/mathml";
        } else if (returnFileName.equals("txt")) {
            contentType = "text/plain";
        } else if (returnFileName.equals("jad")) {
            contentType = "text/vnd.sun.j2me.app-descriptor";
        } else if (returnFileName.equals("wml")) {
            contentType = "text/vnd.wap.wml";
        } else if (returnFileName.equals("htc")) {
            contentType = "text/x-component";
        } else if (returnFileName.equals("png")) {
            contentType = "image/png";
        } else if (returnFileName.equals("tif") || returnFileName.equals("tiff")) {
            contentType = "image/tiff";
        } else if (returnFileName.equals("wbmp")) {
            contentType = "image/vnd.wap.wbmp";
        } else if (returnFileName.equals("ico")) {
            contentType = "image/x-icon";
        } else if (returnFileName.equals("jng")) {
            contentType = "image/x-jng";
        } else if (returnFileName.equals("bmp")) {
            contentType = "image/x-ms-bmp";
        } else if (returnFileName.equals("svg")) {
            contentType = "image/svg+xml";
        } else if (returnFileName.equals("jar") || returnFileName.equals("var")
                || returnFileName.equals("ear")) {
            contentType = "application/java-archive";
        } else if (returnFileName.equals("doc")) {
            contentType = "application/msword";
        } else if (returnFileName.equals("pdf")) {
            contentType = "application/pdf";
        } else if (returnFileName.equals("rtf")) {
            contentType = "application/rtf";
        } else if (returnFileName.equals("xls")) {
            contentType = "application/vnd.ms-excel";
        } else if (returnFileName.equals("ppt")) {
            contentType = "application/vnd.ms-powerpoint";
        } else if (returnFileName.equals("7z")) {
            contentType = "application/x-7z-compressed";
        } else if (returnFileName.equals("rar")) {
            contentType = "application/x-rar-compressed";
        } else if (returnFileName.equals("swf")) {
            contentType = "application/x-shockwave-flash";
        } else if (returnFileName.equals("rpm")) {
            contentType = "application/x-redhat-package-manager";
        } else if (returnFileName.equals("der") || returnFileName.equals("pem")
                || returnFileName.equals("crt")) {
            contentType = "application/x-x509-ca-cert";
        } else if (returnFileName.equals("xhtml")) {
            contentType = "application/xhtml+xml";
        } else if (returnFileName.equals("zip")) {
            contentType = "application/zip";
        } else if (returnFileName.equals("mid") || returnFileName.equals("midi")
                || returnFileName.equals("kar")) {
            contentType = "audio/midi";
        } else if (returnFileName.equals("mp3")) {
            contentType = "audio/mpeg";
        } else if (returnFileName.equals("ogg")) {
            contentType = "audio/ogg";
        } else if (returnFileName.equals("m4a")) {
            contentType = "audio/x-m4a";
        } else if (returnFileName.equals("ra")) {
            contentType = "audio/x-realaudio";
        } else if (returnFileName.equals("3gpp")
                || returnFileName.equals("3gp")) {
            contentType = "video/3gpp";
        } else if (returnFileName.equals("mp4")) {
            contentType = "video/mp4";
        } else if (returnFileName.equals("mpeg")
                || returnFileName.equals("mpg")) {
            contentType = "video/mpeg";
        } else if (returnFileName.equals("mov")) {
            contentType = "video/quicktime";
        } else if (returnFileName.equals("flv")) {
            contentType = "video/x-flv";
        } else if (returnFileName.equals("m4v")) {
            contentType = "video/x-m4v";
        } else if (returnFileName.equals("mng")) {
            contentType = "video/x-mng";
        } else if (returnFileName.equals("asx") || returnFileName.equals("asf")) {
            contentType = "video/x-ms-asf";
        } else if (returnFileName.equals("wmv")) {
            contentType = "video/x-ms-wmv";
        } else if (returnFileName.equals("avi")) {
            contentType = "video/x-msvideo";
        }
        return contentType;
    }

    public static SheetData[] getExcelData(JSONArray jsonArray, SheetData... sds){
        for (int i = 0; i < jsonArray.size(); i++) {
            Map<String, Object> twoMap = jsonArray.getJSONObject(i);
            SheetData sheetData = new SheetData();
            for (Object two : twoMap.entrySet()) {
                String key = ((Map.Entry) two).getKey().toString();
                String value = ((Map.Entry) two).getValue().toString();
                if ("sheetName".equals(key)) {
                    sheetData.setSheetName(value);
                }else if ("data".equals(key)) {
                    //JSONArray twoJsonArray = (JSONArray) JSONArray.parse(value);
                    //sheetData.addDatas(twoJsonArray.toJavaList(List.class));
                    //System.out.println(twoJsonArray.toJavaList(List.class));
                    List lisMap = (List) twoMap.get(key);
                    List list = new ArrayList();
                    for (int d = 0 ;d<lisMap.size();d++){
                        list.add(lisMap.get(d));
                    }
                    sheetData.addDatas(list);
                }else if ("rowName".equals(key)) {
                    sheetData.setRowName(value.split(","));
                }else {
                    sheetData.put(key,value);
                }
            }
            sds[i] = sheetData;
        }
        return sds;
    }

    /**
     * 检查文件是否存在
     * @param path
     * @return
     */
    public static boolean fileExists(String path){
        // 检测文件是否存在
        File file = new File(path); // 获取操作对象
        // 判断父目录是否存在，不存在则创建
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdir();
        }
        // 判断当前文件是否存在
        if (!file.exists()){
            return false;
        }else {
            return true;
        }
    }

    /**
     * 判断是否有该文件存在，没有文件创建，有文件时删除再创建
     * @param path
     * @return
     * @throws Exception
     */
    public static File getFile(String path)throws Exception{
        File file = new File(path);// 获取操作对象
        // 判断父目录是否存在
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdir();
        }
        // 判断当前文件是否存在
        if (!file.exists()){
            file.createNewFile();
        }else {
            file.delete();
            file.createNewFile();
        }
        return file;
    }

    /**
     * 从实体中解析出字段数据
     * @param data  可能为pojo或者map 从field中解析
     * @param field 字段名称
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Object getValue(Object data, String field) {
        if (data instanceof Map) {
            Map map = (Map) data;
            return map.get(field);
        }
        try {
            String method = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
            Method m = data.getClass().getMethod(method, null);
            if (m != null) {
                return m.invoke(data, null);
            }
        } catch (Exception e) {
            System.out.println("data invoke error , data:" + data + " , key:" + field);
            return null;
        }
        return null;
    }

    /**
     * 判断是否为数字
     * @param v
     * @return
     */
    public static boolean isNumber(Object v) {
        if (v == null) return false;
        if (v instanceof Number) {
            return true;
        } else if (v.toString().matches("^\\d+$")) {
            return true;
        } else if (v.toString().matches("^-?\\d+\\.?\\d+$")) {
            return true;
        } else {
            try {
                Double.parseDouble(v.toString());
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    /**
     * 返回 #{} 或者 ${} 中包含的值
     * @param str
     * @return eg:#{name} ${ages}
     */
    public static String[] getWildcard(String str) {
        List<String> list = new ArrayList<String>();
        int start = 0;
        while (start < str.length() && start >= 0) {
            start = str.indexOf("{", start);
            int end = str.indexOf("}", start);
            if (start > 0) {
                String wc = str.substring(start - 1, end + 1);
                list.add(wc);
            }
            if (start < 0) break;
            start = end + 1;
        }
        return list.toArray(new String[0]);
    }
}