package com.xiaoji.util;

import com.xiaoji.model.BbgTax;
import com.xiaoji.model.BbgWage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;

public class ReaderExcel {
    Logger logger = LoggerFactory.getLogger(ReaderExcel.class);

    final static String excel2003L =".xls";    //2003- 版本的excel
    final static String excel2007U =".xlsx";   //2007+ 版本的excel

    /**
     * 描述：获取IO流中的数据，组装成MessageResult<List<BbgWage>>对象
     * @param in,fileName
     * @return
     * @throws IOException
     */
    public MessageResult<List<BbgWage>> getWageExcel(InputStream in, String fileName){
        List<BbgWage> list = new ArrayList<BbgWage>();
        logger.info("文件名:" + fileName);
        Sheet sheet = null;
        Row row = null;
        String sheetName = "";
        try {
            // 创建Excel工作薄
            Workbook work = this.getWorkbook(in,fileName);
            if(null == work){
                return ResultResponse.makeErrRsp("Excel工作薄为空！");
            }
            // 遍历Excel中所有的sheet
            for (int i = 0; i < work.getNumberOfSheets(); i++) {
                sheet = work.getSheetAt(i);
                sheetName = sheet.getSheetName();

                if (sheet.getLastRowNum() == 0  &&  sheet.getPhysicalNumberOfRows() == 0) {
                    continue;
                }
                if (sheetName.contains(".") && sheetName.length() <= 7 && sheetName.length() > 4) {
                    String[] t = sheetName.split("\\.");
                    int num = Integer.parseInt(t[1]);
                    if (num < 10) {
                        t[1] = "0" + num;
                    }
                    sheetName = t[0] + "-" + t[1];
                }else {
                    sheetName = null;
                    //return ResultResponse.makeErrRsp(" 文件读取失败，请确认此文件sheet名称是否符合规范，例‘2018.1’或‘2018.11");
                }
                if (sheetName == null) continue;

                logger.info("sheetName:"+sheetName);
                // 遍历当前sheet中的所有行
                for (int r = 5; r <= sheet.getLastRowNum(); r++) {
                    row = sheet.getRow(r);
                    // 如果当前行没有数据，跳出循环
                    if (row == null || getCellValue(row.getCell(2)) == null ) {
                        continue;
                    }
                    Object wage_no = getCellValue(row.getCell(0));                  // 序号
                    Object wage_dept = getCellValue(row.getCell(1));                // 部门职务
                    Object wage_name = getCellValue(row.getCell(2));                // 姓名
                    Object wage_joinDt = getCellValue(row.getCell(3));              // 当岗日期
                    Object wage_phone = getCellValue(row.getCell(4));               // 手机号码
                    Object wage_workCount = getCellValue(row.getCell(5));           // 出勤
                    Object wage_moneyDay = getCellValue(row.getCell(6));            // 日薪
                    Object wage_baseMoney = getCellValue(row.getCell(7));            // 基本工资
                    Object wage_subsidy = getCellValue(row.getCell(8));             // 岗位补贴
                    Object wage_benefits = getCellValue(row.getCell(9));            // 项目补助
                    Object wage_meal = getCellValue(row.getCell(10));                 // 餐补
                    Object wage_else = getCellValue(row.getCell(11));                 // 其他
                    Object wage_leave = getCellValue(row.getCell(12));                // 请假
                    Object wage_baseTotal = getCellValue(row.getCell(13));            // 小计
                    Object wage_taxPension = getCellValue(row.getCell(14));           // 税—养老金
                    Object wage_taxMedical = getCellValue(row.getCell(15));           // 税—医疗
                    Object wage_taxUnemployment = getCellValue(row.getCell(16));     // 税—失业
                    Object wage_taxAccumulation = getCellValue(row.getCell(17));    // 税—公积金
                    Object wage_taxSalary = getCellValue(row.getCell(18));            // 税—计税工资
                    Object wage_taxTone = getCellValue(row.getCell(19));              // 税—个调税
                    Object wage_taxElse = getCellValue(row.getCell(20));              // 税—其他
                    Object wage_total = getCellValue(row.getCell(21));                // 总计

                    BbgWage info = new BbgWage();
                    info.setWage_date(sheetName);
                    info.setWage_no(wage_no == null ? null:wage_no.toString());                  // 序号
                    info.setWage_dept(wage_dept == null ? null:wage_dept.toString());                // 部门职务
                    info.setWage_name(wage_name == null ? null:wage_name.toString());                // 姓名
                    info.setWage_joinDt(wage_joinDt == null ? null:wage_joinDt.toString());              // 当岗日期
                    info.setWage_phone(wage_phone == null ? null:wage_phone.toString());               // 手机号码
                    info.setWage_workCount(wage_workCount == null ? null:wage_workCount.toString());           // 出勤
                    info.setWage_moneyDay(wage_moneyDay == null ? null:wage_moneyDay.toString());            // 日薪
                    info.setWage_baseMoney(wage_baseMoney == null ? null:wage_baseMoney.toString());            // 基本工资
                    info.setWage_subsidy(wage_subsidy == null ? null:wage_subsidy.toString());             // 岗位补贴
                    info.setWage_benefits(wage_benefits == null ? null:wage_benefits.toString());            // 项目补助
                    info.setWage_meal(wage_meal == null ? null:wage_meal.toString());                // 餐补
                    info.setWage_else(wage_else == null ? null:wage_else.toString());                 // 其他
                    info.setWage_leave(wage_leave == null ? null:wage_leave.toString());                // 请假
                    info.setWage_baseTotal(wage_baseTotal == null ? null:wage_baseTotal.toString());            // 小计
                    info.setWage_taxPension(wage_taxPension == null ? null:wage_taxPension.toString());           // 税—养老金
                    info.setWage_taxMedical(wage_taxMedical == null ? null:wage_taxMedical.toString());           // 税—医疗
                    info.setWage_taxUnemployment(wage_taxUnemployment == null ? null:wage_taxUnemployment.toString());     // 税—失业
                    info.setWage_taxAccumulation(wage_taxAccumulation == null ? null:wage_taxAccumulation.toString());    // 税—公积金
                    info.setWage_taxSalary(wage_taxSalary == null ? null:wage_taxSalary.toString());            // 税—计税工资
                    info.setWage_taxTone(wage_taxTone == null ? null:wage_taxTone.toString());              // 税—个调税
                    info.setWage_taxElse(wage_taxElse == null ? null:wage_taxElse.toString());              // 税—其他
                    info.setWage_total(wage_total == null ? null:wage_total.toString());                // 总计

                    list.add(info);
                    //logger.info(info.toString());
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
            return ResultResponse.makeErrRsp(" 文件读取失败，请确认此文件是否为工资信息文件");
        }
        if(list.size() == 0){
            return ResultResponse.makeErrRsp(" 文件读取失败，请确认此工资信息文件是否有数据");
        }
        return ResultResponse.makeOKRsp(list);
    }

    /**
     * 描述：获取IO流中的数据，组装成MessageResult<List<BbgTax>>对象
     * @param in,fileName
     * @return
     * @throws IOException
     */
    public MessageResult<List<BbgTax>> getTaxExcel(InputStream in, String fileName){
        List<BbgTax> list = new ArrayList<BbgTax>();
        logger.info("文件名:" + fileName);
        Sheet sheet = null;
        Row row = null;
        String sheetName = "";

        try {
            // 创建Excel工作薄
            Workbook work = this.getWorkbook(in,fileName);
            if(null == work){
                return ResultResponse.makeErrRsp("Excel工作薄为空！");
            }
            // 遍历Excel中所有的sheet
            for (int i = 0; i < work.getNumberOfSheets(); i++) {
                sheet = work.getSheetAt(i);
                sheetName = sheet.getSheetName();

                if (sheet.getLastRowNum() == 0  &&  sheet.getPhysicalNumberOfRows() == 0) {
                    continue;
                }
                if (sheetName.contains(".") && sheetName.length() <= 7 && sheetName.length() > 5) {
                    String[] t = sheetName.split("\\.");
                    int num = Integer.parseInt(t[1]);
                    if (num < 10) {
                        t[1] = "0" + num;
                    }
                    sheetName = t[0] + "-" + t[1];
                }else {
                    sheetName = null;
                    //return ResultResponse.makeErrRsp(" 文件读取失败，请确认社保信息文件sheet名称是否符合规范，例‘2018.1’或‘2018.11");
                }
                if (sheetName == null) continue;
                logger.info("sheetName:"+sheetName);
                // 遍历当前sheet中的所有行
                for (int j = 3; j <= sheet.getLastRowNum(); j++) {
                    row = sheet.getRow(j);
                    // 如果当前行没有数据，跳出循环
                    if (row == null || getCellValue(row.getCell(1)) == null ) {
                        continue;
                    }
                    Object tax_no = getCellValue(row.getCell(0));
                    Object tax_name = getCellValue(row.getCell(1));
                    Object tax_household = getCellValue(row.getCell(2));
                    Object tax_minBasePayment = getCellValue(row.getCell(3));
                    Object company_pension = getCellValue(row.getCell(4));
                    Object company_medical = getCellValue(row.getCell(5));
                    Object company_unemployment = getCellValue(row.getCell(6));
                    Object company_fertility = getCellValue(row.getCell(7));
                    Object company_injury = getCellValue(row.getCell(8));
                    Object company_total = getCellValue(row.getCell(9));
                    Object personal_pension = getCellValue(row.getCell(10));
                    Object personal_medical = getCellValue(row.getCell(11));
                    Object personal_unemployment = getCellValue(row.getCell(12));
                    Object personal_total = getCellValue(row.getCell(13));
                    Object tax_total = getCellValue(row.getCell(14));
                    Object tax_payment = getCellValue(row.getCell(15));
                    Object tax_remark = getCellValue(row.getCell(16));

                    BbgTax info = new BbgTax();
                    info.setTax_date(sheetName);
                    info.setTax_no(tax_no == null ? null:tax_no.toString());                  // 序号
                    info.setTax_name(tax_name == null ? null:tax_name.toString());                // 姓名
                    info.setTax_household(tax_household == null ? null:tax_household.toString());           // 户口性质
                    info.setTax_minBasePayment(tax_minBasePayment == null ? null:tax_minBasePayment.toString());      // 最低缴费基数
                    info.setCompany_pension(company_pension == null ? null:company_pension.toString());         // 公司—养老金20%
                    info.setCompany_medical(company_medical == null ? null:company_medical.toString());         // 公司—医疗金9.5%
                    info.setCompany_unemployment(company_unemployment == null ? null:company_unemployment.toString());    // 公司—失业金0.5%
                    info.setCompany_fertility(company_fertility == null ? null:company_fertility.toString());       // 公司—生育1%
                    info.setCompany_injury(company_injury == null ? null:company_injury.toString());          // 公司—工伤0.1%
                    info.setCompany_total(company_total == null ? null:company_total.toString());           // 公司—合计32.2%
                    info.setPersonal_pension(personal_pension == null ? null:personal_pension.toString());       // 个人—养老金8%
                    info.setPersonal_medical(personal_medical == null ? null:personal_medical.toString());       // 个人—医疗金2%
                    info.setPersonal_unemployment(personal_unemployment == null ? null:personal_unemployment.toString());  // 个人—失业金0.5%
                    info.setPersonal_toatl(personal_total == null ? null:personal_total.toString());         // 个人—合计10.5%
                    info.setTax_total(tax_total == null ? null:tax_total.toString());              // 总计42.7%
                    info.setTax_payment(tax_payment == null ? null:tax_payment.toString());            // 补缴
                    info.setTax_remark(tax_remark== null ? null:tax_remark.toString());             // 备注

                    list.add(info);
                    //logger.info(info.toString());
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
            return ResultResponse.makeErrRsp(" 文件读取失败，请确认此文件是否为社保信息文件");
        }
        if(list.size() == 0){
            return ResultResponse.makeErrRsp(" 文件读取失败，请确认此社保信息文件是否有数据");
        }
        return ResultResponse.makeOKRsp(list);
    }

    /**
     * 描述：根据文件后缀，自适应上传文件的版本
     * @param inStr,fileName
     * @return
     * @throws Exception
     */
    public  Workbook getWorkbook(InputStream inStr, String fileName) throws Exception{
        Workbook wb = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if(excel2003L.equals(fileType)){
            wb = new HSSFWorkbook(inStr);  //2003-
        }else if(excel2007U.equals(fileType)){
            wb = WorkbookFactory.create(inStr); /*new XSSFWorkbook(inStr); */ //2007+
        }else{
            throw new Exception("解析的文件格式有误！");
        }
        return wb;
    }

    /**
     * 描述：对表格中数值进行格式化
     * @param cell
     * @return
     */
    public  Object getCellValue(Cell cell){
        Object value = null;
        DecimalFormat df = new DecimalFormat("0");  // 格式化number String字符
        DecimalFormat df2 = new DecimalFormat("0.00");  // 格式化数字

        if (cell == null)   return null;

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:    // 0 数值与日期
                if (DateUtil.isCellDateFormatted(cell)) {
                    value = String.valueOf(cell.getDateCellValue());
                } else {
                    value = df.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_STRING:     // 1 字符串
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_FORMULA:     // 2 公式型
                try {
                    value = df2.format(cell.getNumericCellValue());
                } catch (IllegalStateException e) {
                    value = String.valueOf(cell.getRichStringCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:    // 4 布尔型
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:  // 3 空值
                break;
            default:
                break;
        }
        return value;
    }
}
