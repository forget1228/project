package com.xj.ptgd.common.result;

import com.xj.ptgd.entity.body.Error;
import com.xj.ptgd.common.logs.LogUtils;
import com.xj.ptgd.common.util.JaxbUtil;
import com.xj.ptgd.entity.base.XMLBaseDto;
import com.xj.ptgd.entity.base.XMLHeadDto;
import org.slf4j.Logger;

import javax.xml.bind.JAXBException;

/**
  * ResultUtil
  * @author wkm
  * @since 2018/7/30
  */
public class ResultUtil {
    static Logger log = LogUtils.getExceptionLogger();
    public static String getResult (Object ret,Class<?>... types) {
        String result = null;
        try {
            JaxbUtil ju2 =new JaxbUtil(types);
            result = ju2.toXml(ret,"UTF-8","<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
        }catch (JAXBException e){
            log.error("注：xml对相应类处理异常:请核对实体类数据及映射Bean" +
                    "\n数据对象: ("+ret.getClass().getName()+")"+
                    "\n映射Bean: ("+types[0].getName()+")");
            e.printStackTrace();
            throw new RuntimeException();
        }
        return result;
    }

    public static String setHeadAndGetResult (XMLHeadDto headDto, XMLBaseDto ret, Class<?>... types) {
        String result = null;
        ret.setHead(headDto);
        try {
            JaxbUtil ju2 =new JaxbUtil(types);
            result = ju2.toXml(ret,"ISO-8859-1","<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
        }catch (JAXBException e){
            log.error("注：xml对相应类处理异常:请核对实体类数据及映射Bean" +
                    "\n数据对象: ("+ret.getClass().getName()+")"+
                    "\n映射Bean: ("+types[0].getName()+")");
            e.printStackTrace();
            throw new RuntimeException();
        }
        return result;
    }
}