package com.xj.ptgd.controller;

import com.xj.ptgd.service.impl.ScheduledServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.xj.ptgd.common.util.MacUtil.addLen;

@Component
public class ScheduledController {

    @Autowired
    ScheduledServiceImpl scheduledService;

    @Scheduled(cron = "0 0 2 * * ?")  //每天凌晨两点
    public void pushDataScheduled() {
        String tranDateTime = "";
        String operationDate = "";
        Date date = new Date();
        // 指定日期格式
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
        tranDateTime = fmt.format(date);
        System.out.println("++++++++++++++++++++定时任务++++++++++DateTime："+tranDateTime);
        fmt = new SimpleDateFormat("yyyyMMdd");
        operationDate = fmt.format(date);

        Send9000 send9000 = new Send9000();
        String xml =
                "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>" +
                        "<In>" +
                        "<Head>" +
                        "<ChnlNo>303</ChnlNo>" +
                        "<FTranCode>9000</FTranCode>" +
                        "<InstID>03030005</InstID>" +
                        "<TrmSeqNum>99999999</TrmSeqNum>" +
                        "<TranDateTime>"+tranDateTime+"</TranDateTime>" +
                        "<ErrCode></ErrCode>" +
                        "</Head>" +
                        "<Body>" +
                        "<operationDate>"+operationDate+"</operationDate>" +
                        "</Body>" +
                        "</In>";
        xml = addLen(xml);
        send9000.StartXML(xml);
        System.out.println( "++++++++++++++++++++定时任务++++++++++END");
    }
}
