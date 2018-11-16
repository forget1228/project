package com.xiaoji;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestClassG {

    @Test
    public void test(){
        String tranDateTime = "";
        String operationDate = "";
        Date date = new Date();
        // 指定日期格式
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
        tranDateTime = fmt.format(date);
        System.out.println("DateTime"+tranDateTime);
        fmt = new SimpleDateFormat("yyyyMMdd");
        operationDate = fmt.format(date);
        System.out.println(operationDate);
    }

    @Test
    public void upload(){
        //http://127.0.0.1:8081/abk/project/upload
    }

}
