package com.xj.ptgd.common.util;

 /**
  * Transcoding 编码格式处理
  * https://blog.csdn.net/54powerman/article/details/77575656
  * @author wkm
  * @since 2018/8/8
  */
public class Transcoding {

    public static String ISOToUTF(String iosString){
        String retUTF = "";
        if(iosString==null) return retUTF;
        try {
            // 解码
            retUTF = new String(iosString.getBytes("iso8859-1"), "UTF-8"); // 你好
        }catch (Exception e){
            e.printStackTrace();

        }
        return retUTF;
    }

    public static String UTFToISO(String utfString){
        String retISO = null;
        if(utfString==null) return retISO;
        try {
            retISO = new String(utfString.getBytes("UTF-8"), "ISO-8859-1"); // 你好
        }catch (Exception e){
            e.printStackTrace();

        }
        return retISO;
    }

    public static String ToISO(String utfString){
        String retISO = null;
        if(utfString==null) return retISO;
        try {
            // 编码
            byte[] iso = utfString.getBytes("ISO8859-1");
            // 解码
            retISO = new String(iso, "ISO8859-1"); // 你好
        }catch (Exception e){
            e.printStackTrace();

        }
        return retISO;
    }

    public static String StringForTranscodingType(String sb){
        String ret = null;
        try {
            String iso8859 = new String(sb.toString().getBytes("iso8859-1"));
            String gbk = new String(sb.toString().getBytes("gbk"));
            String utf8 = new String(sb.toString().getBytes("utf-8"));
            if(iso8859.equals(sb.toString())){
                System.out.println("iso8859");
                ret = "iso8859";
            }else  if(gbk.equals(sb.toString())){
                System.out.println("gbk");
                ret = "gbk";
            }else  if(utf8.equals(sb.toString())){
                System.out.println("utf8");
                ret = "utf8";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }


    private String StringToUTF(String str){
        String retUft = null;
        try {
            String clientStr = new String(str.getBytes("UTF-8"), "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return retUft;
    }
}
