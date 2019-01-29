package com.xiaoji.service.impl;

import com.xiaoji.dao.BbgMapper;
import com.xiaoji.model.BbgTax;
import com.xiaoji.model.BbgWage;
import com.xiaoji.service.ProjectService;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService{
    Logger logger = LoggerFactory.getLogger(ProjectService.class);

    @Resource
    private BbgMapper bbgMapper;

    @Override
    public List<Map<String,Object>> findBbgByDate(String date) {
        List<Map<String,Object>> list = bbgMapper.findBbgByDate(date);
        Map<String,Object> map = bbgMapper.findSUMBbgByDate(date);
        if (map.get("wage_base_total") != null || map.get("company_total") != null) list.add(bbgMapper.findSUMBbgByDate(date));
        return list;
    }

    @Override
    @Transactional
    public String insertBbg(List<BbgWage> wageList, List<BbgTax> taxList, String time, String version){
        try {
            if ("0".equals(version)) {  // 覆盖
                Set<String> wage_date = wageList.stream().map(BbgWage::getWage_date).collect(Collectors.toSet());
                for (String i : wage_date) {
                    bbgMapper.deleteBbgWageByWageDate(i);
                }
                Set<String> tax_date = taxList.stream().map(BbgTax::getTax_date).collect(Collectors.toSet());
                for (String i : tax_date) {
                    bbgMapper.deleteBbgWageByTaxDate(i);
                }
                logger.info("清除数据成功");
            }
            for (BbgWage  bbgWage: wageList) {
                bbgMapper.insertBbgWage(bbgWage);
            }
            for (BbgTax  bbgTax: taxList) {
                bbgMapper.insertBbgTax(bbgTax);
            }
            logger.info("数据入库成功");
        }catch (Exception ex){
            ex.printStackTrace();
            if (ex instanceof BadSqlGrammarException){/*  数据库语法错误 */
                logger.info("数据库语法错误:   BadSqlGrammarException");
            }else if (ex instanceof MyBatisSystemException){    /* 数据库连接异常*/
                logger.info("数据库连接异常:    MyBatisSystemException");
            }
            return "fail";
        }
        return "success";
    }
}
