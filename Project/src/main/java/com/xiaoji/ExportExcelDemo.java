package com.xiaoji;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xiaoji.model.SheetData;
import com.xiaoji.util.CommonUtil;
import com.xiaoji.util.ExcelUtil;

import java.text.SimpleDateFormat;
import java.util.*;

public class ExportExcelDemo {

    public static void main(String[] args) throws Exception {

        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        try{

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
    }

    public static String str(){
        return "[" +
                "{\"demandName\":\"name0\", " +
                "\"data\":[\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"\",\"dBDesign\":0.0,\"gongNeng\":\"质量保证\",\"test\":0.0,\"lJDesign\":0.0,\"numbers\":0.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":0.0,\"demandNo\":\"20181022103534772001\",\"gongnegQD\":\"\",\"exploit\":0.0,\"zgspgId\":150,\"ziGongNeng\":\"项目管理\",\"createDate\":\"2018-10-22 10:35:34.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"\",\"dBDesign\":0.0,\"gongNeng\":\"质量保证\",\"test\":3.0,\"lJDesign\":0.0,\"numbers\":3.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":0.0,\"demandNo\":\"20181022103534772001\",\"gongnegQD\":\"\",\"exploit\":0.0,\"zgspgId\":151,\"ziGongNeng\":\"集成测试\",\"createDate\":\"2018-10-22 10:35:34.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"\",\"dBDesign\":0.0,\"gongNeng\":\"质量保证\",\"test\":0.0,\"lJDesign\":0.0,\"numbers\":0.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":0.0,\"demandNo\":\"20181022103534772001\",\"gongnegQD\":\"\",\"exploit\":0.0,\"zgspgId\":152,\"ziGongNeng\":\"业务测试\",\"createDate\":\"2018-10-22 10:35:34.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"\",\"dBDesign\":0.0,\"gongNeng\":\"质量保证\",\"test\":0.0,\"lJDesign\":0.0,\"numbers\":0.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":0.0,\"demandNo\":\"20181022103534772001\",\"gongnegQD\":\"\",\"exploit\":0.0,\"zgspgId\":153,\"ziGongNeng\":\"版本文档准备\",\"createDate\":\"2018-10-22 10:35:34.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"\",\"dBDesign\":0.0,\"gongNeng\":\"质量保证\",\"test\":0.0,\"lJDesign\":0.0,\"numbers\":0.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":0.0,\"demandNo\":\"20181022103534772001\",\"gongnegQD\":\"\",\"exploit\":0.0,\"zgspgId\":154,\"ziGongNeng\":\"版本测试支持\",\"createDate\":\"2018-10-22 10:35:34.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"\",\"dBDesign\":0.0,\"gongNeng\":\"质量保证\",\"test\":3.0,\"lJDesign\":0.0,\"numbers\":3.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":0.0,\"demandNo\":\"20181022103534772001\",\"gongnegQD\":\"\",\"exploit\":0.0,\"zgspgId\":155,\"ziGongNeng\":\"版本上线后跟踪支持\",\"createDate\":\"2018-10-22 10:35:34.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"高\",\"dBDesign\":0.0,\"gongNeng\":\"功能1\",\"test\":0.0,\"lJDesign\":0.0,\"numbers\":2.25,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":1.0,\"demandNo\":\"20181022103534772001\",\"gongnegQD\":\"清单\",\"exploit\":1.25,\"zgspgId\":156,\"ziGongNeng\":\"子功能1\",\"createDate\":\"2018-10-22 10:35:34.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"中\",\"dBDesign\":0.0,\"gongNeng\":\"\",\"test\":0.0,\"lJDesign\":0.0,\"numbers\":5.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":0.0,\"demandNo\":\"20181022103534772001\",\"gongnegQD\":\"1.aaaaaaa\n2.bbbb\n3.cccccccc\",\"exploit\":5.0,\"zgspgId\":157,\"ziGongNeng\":\"子功能2\",\"createDate\":\"2018-10-22 10:35:34.0\"}" +
                ",{\"dBDesign\":0.0,\"gongNeng\":\"合计\",\"test\":6.0,\"lJDesign\":0.0,\"numbers\":13.25,\"hMDesign\":0.0,\"exploit\":6.25,\"demand\":1.0}" +
                "],"+
                "\"sheetName\":\"0中国\", \"code\":\"0000\", \"title\":\"000000\"}," +
                "{\"demandName\":\"犀语科技《OCR产品》前端需求详细工时统计\",\"data\":[\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"\",\"dBDesign\":0.0,\"gongNeng\":\"质量保证\",\"test\":0.0,\"lJDesign\":0.0,\"numbers\":0.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":0.0,\"demandNo\":\"20181016181411995001\",\"gongnegQD\":\"\",\"exploit\":0.0,\"zgspgId\":165,\"ziGongNeng\":\"项目管理\",\"createDate\":\"2018-10-17 17:56:56.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"\",\"dBDesign\":0.0,\"gongNeng\":\"质量保证\",\"test\":0.0,\"lJDesign\":0.0,\"numbers\":0.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":0.0,\"demandNo\":\"20181016181411995001\",\"gongnegQD\":\"\",\"exploit\":0.0,\"zgspgId\":166,\"ziGongNeng\":\"集成测试\",\"createDate\":\"2018-10-17 17:56:56.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"\",\"dBDesign\":0.0,\"gongNeng\":\"质量保证\",\"test\":0.0,\"lJDesign\":0.0,\"numbers\":0.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":0.0,\"demandNo\":\"20181016181411995001\",\"gongnegQD\":\"\",\"exploit\":0.0,\"zgspgId\":167,\"ziGongNeng\":\"业务测试\",\"createDate\":\"2018-10-17 17:56:56.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"\",\"dBDesign\":0.0,\"gongNeng\":\"质量保证\",\"test\":0.0,\"lJDesign\":0.0,\"numbers\":0.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":0.0,\"demandNo\":\"20181016181411995001\",\"gongnegQD\":\"\",\"exploit\":0.0,\"zgspgId\":168,\"ziGongNeng\":\"版本文档准备\",\"createDate\":\"2018-10-17 17:56:56.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"\",\"dBDesign\":0.0,\"gongNeng\":\"质量保证\",\"test\":0.0,\"lJDesign\":0.0,\"numbers\":0.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":0.0,\"demandNo\":\"20181016181411995001\",\"gongnegQD\":\"\",\"exploit\":0.0,\"zgspgId\":169,\"ziGongNeng\":\"版本测试支持\",\"createDate\":\"2018-10-17 17:56:56.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"\",\"dBDesign\":0.0,\"gongNeng\":\"质量保证\",\"test\":0.0,\"lJDesign\":0.0,\"numbers\":0.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":0.0,\"demandNo\":\"20181016181411995001\",\"gongnegQD\":\"\",\"exploit\":0.0,\"zgspgId\":170,\"ziGongNeng\":\"版本上线后跟踪支持\",\"createDate\":\"2018-10-17 17:56:56.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"低\",\"dBDesign\":0.0,\"gongNeng\":\"需求开发准备工作\",\"test\":0.25,\"lJDesign\":0.0,\"numbers\":0.75,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":0.0,\"demandNo\":\"20181016181411995001\",\"gongnegQD\":\"需求分析，需求详细工时统计，搭建项目工程\",\"exploit\":0.5,\"zgspgId\":171,\"ziGongNeng\":\"\",\"createDate\":\"2018-10-17 17:56:56.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"低\",\"dBDesign\":0.0,\"gongNeng\":\"文件上传页\",\"test\":0.5,\"lJDesign\":0.0,\"numbers\":2.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":0.0,\"demandNo\":\"20181016181411995001\",\"gongnegQD\":\"1.画文件上传页面\\n2.文件上传插件的应用\\n3.相关后台接口的对接\",\"exploit\":1.5,\"zgspgId\":172,\"ziGongNeng\":\"\",\"createDate\":\"2018-10-17 17:56:56.0\"}" +
                ",{\"dBDesign\":0.0,\"gongNeng\":\"合计\",\"test\":0.75,\"lJDesign\":0.0,\"numbers\":2.75,\"hMDesign\":0.0,\"exploit\":2.0,\"demand\":0.0}" +
                "]," +
                "\"sheetName\":\"11111\"},\n" +
                "{\"demandName\":\"aaa\",\"data\":[\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"\",\"dBDesign\":2.0,\"gongNeng\":\"质量保证\",\"test\":2.0,\"lJDesign\":2.0,\"numbers\":12.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":2.0,\"demand\":2.0,\"demandNo\":\"20181017152050858001\",\"gongnegQD\":\"\",\"exploit\":2.0,\"zgspgId\":180,\"ziGongNeng\":\"项目管理\",\"createDate\":\"2018-10-17 16:42:42.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"\",\"dBDesign\":0.0,\"gongNeng\":\"质量保证\",\"test\":0.0,\"lJDesign\":0.0,\"numbers\":3.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":3.0,\"demandNo\":\"20181017152050858001\",\"gongnegQD\":\"\",\"exploit\":0.0,\"zgspgId\":181,\"ziGongNeng\":\"集成测试\",\"createDate\":\"2018-10-17 16:42:42.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"\",\"dBDesign\":0.0,\"gongNeng\":\"质量保证\",\"test\":0.0,\"lJDesign\":3.0,\"numbers\":7.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":4.0,\"demandNo\":\"20181017152050858001\",\"gongnegQD\":\"\",\"exploit\":0.0,\"zgspgId\":182,\"ziGongNeng\":\"业务测试\",\"createDate\":\"2018-10-17 16:42:42.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"\",\"dBDesign\":4.0,\"gongNeng\":\"质量保证\",\"test\":0.0,\"lJDesign\":0.0,\"numbers\":9.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":5.0,\"demandNo\":\"20181017152050858001\",\"gongnegQD\":\"\",\"exploit\":0.0,\"zgspgId\":183,\"ziGongNeng\":\"版本文档准备\",\"createDate\":\"2018-10-17 16:42:42.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"\",\"dBDesign\":0.0,\"gongNeng\":\"质量保证\",\"test\":0.0,\"lJDesign\":0.0,\"numbers\":11.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":6.0,\"demandNo\":\"20181017152050858001\",\"gongnegQD\":\"\",\"exploit\":5.0,\"zgspgId\":184,\"ziGongNeng\":\"版本测试支持\",\"createDate\":\"2018-10-17 16:42:42.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"\",\"dBDesign\":0.0,\"gongNeng\":\"质量保证\",\"test\":6.0,\"lJDesign\":0.0,\"numbers\":13.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.0,\"demand\":7.0,\"demandNo\":\"20181017152050858001\",\"gongnegQD\":\"\",\"exploit\":0.0,\"zgspgId\":185,\"ziGongNeng\":\"版本上线后跟踪支持\",\"createDate\":\"2018-10-17 16:42:42.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"低\",\"dBDesign\":1.0,\"gongNeng\":\"bbb\",\"test\":1.0,\"lJDesign\":1.0,\"numbers\":6.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":1.0,\"demand\":1.0,\"demandNo\":\"20181017152050858001\",\"gongnegQD\":\"bbb\",\"exploit\":1.0,\"zgspgId\":186,\"ziGongNeng\":\"bbb\",\"createDate\":\"2018-10-17 16:42:42.0\"},\n" +
                "{\"emailNo\":\"bfd4fcc775494a51bb676e47039ed4c7\",\"complexity\":\"中\",\"dBDesign\":0.5,\"gongNeng\":\"ccc\",\"test\":0.5,\"lJDesign\":0.5,\"numbers\":3.0,\"remark\":\"\",\"updateTime\":\"2018-10-22 18:33:51.0\",\"hMDesign\":0.5,\"demand\":0.5,\"demandNo\":\"20181017152050858001\",\"gongnegQD\":\"ccc\",\"exploit\":0.5,\"zgspgId\":187,\"ziGongNeng\":\"ccc\",\"createDate\":\"2018-10-17 16:42:42.0\"}" +
                //",{\"dBDesign\":7.5,\"gongNeng\":\"合计\",\"test\":9.5,\"lJDesign\":6.5,\"numbers\":64.0,\"hMDesign\":3.5,\"exploit\":8.5,\"demand\":28.5}
                "]" +
                "}" +
                "]";
    }
}
