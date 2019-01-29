package com.xj.ptgd.common.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonMethods {

    /**
     * 检查是否包含MySql保留字
     * @param str
     * @return true 包含
     */
    public static boolean checkMySqlReservedWords(String str){
        String regEx = "[%^_\\[\\]]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     *  判断数字
     * @param str
     * @return true 数字
     */
    public static boolean isInteger(String str) {
        Pattern pattern  = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 将 str 去除前、后空格(全角、半角)空格，将文字中间多空格转为一个空格
     * @param str
     * @return
     */
    public static String trimAllBlanks(String str){

        String result = "";
        result = str.replaceAll("^[　*| *| *|\\s*]*", "").replaceAll("[　*| *| *|\\s*]*$", "");   // 去两边全角半角空格
        result = result.replaceAll("[\\s\\p{Zs}]"," "); // 将中间全角空格转为" "
        result = result.replaceAll("\\s+"," ");         // 将中间多个" " 转为一个 " "

        return result;
    }

    /**
     * 判断是否是日期类型 true 日期 yyyyMMdd ; false 其他日期格式
     * @param str
     * @return
     */
    public static boolean checkIsDate(String str){
        boolean bool = true;
        // 指定日期格式
        SimpleDateFormat  format = new SimpleDateFormat("yyyyMMdd");
        try{
            // 设置 lenient 为 false . 否则 SimpleDateFormat 会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        }catch (ParseException ex){
            // 抛出异常，说明格式错误
            bool = false;
        }
        return bool;
    }

    /**
     * 判断是否是日期类型 true 日期 yyyyMMddHHmmss ; false 其他日期格式
     * @param str
     * @return
     */
    public static boolean checkIsAllDate(String str){
        boolean bool = true;
        // 指定日期格式
        SimpleDateFormat  format = new SimpleDateFormat("yyyyMMddHHmmss");
        try{
            format.parse(str);
        }catch (ParseException ex){
            // 抛出异常，说明格式错误
            bool = false;
        }
        return bool;
    }

    /**
     * 日期比较大小
     * @param str1 起始日期
     * @param str2 结束日期
     * @return true 起始日期(20180829) 结束日期(20180929)
     */
    public static boolean compareDate(String str1,String str2) {
        // 指定日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try{
            Date date1 = simpleDateFormat.parse(str1);
            Date date2 = simpleDateFormat.parse(str2);
            if (date1.getTime() > date2.getTime()){
                return false;
            }else return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 数据库入库日期 约定的格式：yyyy-MM-dd   yyyy-MM-dd HH:mm:ss
     * @param geShi
     * @return
     */
    public static String changeDateFmt(String str,String startGeShi,String geShi){
        SimpleDateFormat formatter = new SimpleDateFormat(startGeShi);
        try {
            formatter.setLenient(false);
            Date newDate = formatter.parse(str);
            formatter = new SimpleDateFormat(geShi);
            return formatter.format(newDate);
        }catch (Exception e){
            return "";
        }
    }

    /**
     * 生成下一个编号
     */
    public static String idPrimaryNextNumber(String maxSerialNo,String noFmt,String dateFmt) {
        String id = null;
        String title = null;
        Date date = new Date();
        if (dateFmt.contains("'")){
            String reg = "\'";
            String [] str= dateFmt.split(reg);
            title = str[1];
            dateFmt = str[2];
        }
        SimpleDateFormat formatter = new SimpleDateFormat(dateFmt);
        String old = null;
        if (title != null){
            old = maxSerialNo.substring(title.length(),title.length()+8);
        }else    old = maxSerialNo.substring(0,8);

        String now = formatter.format(date);
        int count = noFmt.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("0");
        }
        DecimalFormat df = new DecimalFormat(sb.toString());
        if (!now.equals(old) || maxSerialNo == null || "".equals(maxSerialNo)){
            id = now + df.format(1 + Integer.parseInt(sb.toString()));       // 格式(00000001)  每日第一个号码
        } else {
            if (title != null){
                id = now + df.format(1 + Integer.parseInt(maxSerialNo.substring(title.length()+8, title.length()+8 + count)));
            }else    id = now + df.format(1 + Integer.parseInt(maxSerialNo.substring(8, 8 + count)));
        }
        if (title!=null){   //  'title'
            id = title + id;
        }

        return id;
    }
}
