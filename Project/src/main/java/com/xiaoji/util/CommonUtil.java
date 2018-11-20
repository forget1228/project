package com.xiaoji.util;

import com.alibaba.fastjson.JSONObject;
import com.xiaoji.model.SheetData;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommonUtil {

    public static SheetData getData(SheetData sheetData, JSONObject data) {
        for (Object s : data.entrySet()){
            if (s.toString().contains("[")) {
                //接着进行取list值
                String key = (String) ((Map.Entry)s).getKey();
                List lisMap = new ArrayList();
                lisMap = (List) data.get(key);
                List list = new ArrayList();
                for (int i= 0 ;i<lisMap.size();i++){
                    list.add(lisMap.get(i));
                }
                sheetData.addDatas(list);
            }else {
                sheetData.put(((Map.Entry)s).getKey().toString(),((Map.Entry) s).getValue());
            }
            System.out.println(((Map.Entry)s).getKey()+" ====== "+((Map.Entry)s).getValue());
        }
        return sheetData;
    }

    /**
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static File getFile(String path)throws Exception{
        File file=new File(path);//获取操作对象
        //判断父目录是否存在
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdir();
        }
        //判断当前文件是否存在
        if (!file.exists()){
            file.createNewFile();
        }else {
            file.delete();
            file.createNewFile();
        }
        System.out.println("路径 path :" + file);
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