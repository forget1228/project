package com.xiaoji.model;

public class BbgWage {
    private String wage_no;                  // 序号
    private String wage_dept;                // 部门职务
    private String user_id;                  // 用户ID
    private String wage_name;                // 姓名
    private String wage_joinDt;              // 当岗日期
    private String wage_phone;               // 手机号码
    private String wage_workCount;           // 出勤
    private String wage_moneyDay;            // 日薪
    private String wage_baseMoney;            // 基本工资
    private String wage_subsidy;             // 岗位补贴
    private String wage_benefits;            // 项目补助
    private String wage_meal;                 // 餐补
    private String wage_else;                 // 其他
    private String wage_leave;                // 请假
    private String wage_baseTotal;            // 小计
    private String wage_taxPension;           // 税—养老金
    private String wage_taxMedical;           // 税—医疗
    private String wage_taxUnemployment;     // 税—失业
    private String wage_taxAccumulation ;    // 税—公积金
    private String wage_taxSalary;            // 税—计税工资
    private String wage_taxTone;              // 税—个调税
    private String wage_taxElse;              // 税—其他
    private String wage_total;                // 总计
    private String wage_date;                 // 日期
    private String wage_update;               // 修改日期

    @Override
    public String toString() {
        return "BbgWage{" +
                "wage_no='" + wage_no + '\'' +
                ", wage_dept='" + wage_dept + '\'' +
                ", user_id='" + user_id + '\'' +
                ", wage_name='" + wage_name + '\'' +
                ", wage_joinDt='" + wage_joinDt + '\'' +
                ", wage_phone='" + wage_phone + '\'' +
                ", wage_workCount='" + wage_workCount + '\'' +
                ", wage_moneyDay='" + wage_moneyDay + '\'' +
                ", wage_baseMoney='" + wage_baseMoney + '\'' +
                ", wage_subsidy='" + wage_subsidy + '\'' +
                ", wage_benefits='" + wage_benefits + '\'' +
                ", wage_meal='" + wage_meal + '\'' +
                ", wage_else='" + wage_else + '\'' +
                ", wage_leave='" + wage_leave + '\'' +
                ", wage_baseTotal='" + wage_baseTotal + '\'' +
                ", wage_taxPension='" + wage_taxPension + '\'' +
                ", wage_taxMedical='" + wage_taxMedical + '\'' +
                ", wage_taxUnemployment='" + wage_taxUnemployment + '\'' +
                ", wage_taxAccumulation='" + wage_taxAccumulation + '\'' +
                ", wage_taxSalary='" + wage_taxSalary + '\'' +
                ", wage_taxTone='" + wage_taxTone + '\'' +
                ", wage_taxElse='" + wage_taxElse + '\'' +
                ", wage_total='" + wage_total + '\'' +
                ", wage_date='" + wage_date + '\'' +
                ", wage_update='" + wage_update + '\'' +
                '}';
    }

    public String getWage_no() {
        return wage_no;
    }

    public void setWage_no(String wage_no) {
        this.wage_no = wage_no;
    }

    public String getWage_dept() {
        return wage_dept;
    }

    public void setWage_dept(String wage_dept) {
        this.wage_dept = wage_dept;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getWage_name() {
        return wage_name;
    }

    public void setWage_name(String wage_name) {
        this.wage_name = wage_name;
    }

    public String getWage_joinDt() {
        return wage_joinDt;
    }

    public void setWage_joinDt(String wage_joinDt) {
        this.wage_joinDt = wage_joinDt;
    }

    public String getWage_phone() {
        return wage_phone;
    }

    public void setWage_phone(String wage_phone) {
        this.wage_phone = wage_phone;
    }

    public String getWage_workCount() {
        return wage_workCount;
    }

    public void setWage_workCount(String wage_workCount) {
        this.wage_workCount = wage_workCount;
    }

    public String getWage_moneyDay() {
        return wage_moneyDay;
    }

    public void setWage_moneyDay(String wage_moneyDay) {
        this.wage_moneyDay = wage_moneyDay;
    }

    public String getWage_baseMoney() {
        return wage_baseMoney;
    }

    public void setWage_baseMoney(String wage_baseMoney) {
        this.wage_baseMoney = wage_baseMoney;
    }

    public String getWage_subsidy() {
        return wage_subsidy;
    }

    public void setWage_subsidy(String wage_subsidy) {
        this.wage_subsidy = wage_subsidy;
    }

    public String getWage_benefits() {
        return wage_benefits;
    }

    public void setWage_benefits(String wage_benefits) {
        this.wage_benefits = wage_benefits;
    }

    public String getWage_meal() {
        return wage_meal;
    }

    public void setWage_meal(String wage_meal) {
        this.wage_meal = wage_meal;
    }

    public String getWage_else() {
        return wage_else;
    }

    public void setWage_else(String wage_else) {
        this.wage_else = wage_else;
    }

    public String getWage_leave() {
        return wage_leave;
    }

    public void setWage_leave(String wage_leave) {
        this.wage_leave = wage_leave;
    }

    public String getWage_baseTotal() {
        return wage_baseTotal;
    }

    public void setWage_baseTotal(String wage_baseTotal) {
        this.wage_baseTotal = wage_baseTotal;
    }

    public String getWage_taxPension() {
        return wage_taxPension;
    }

    public void setWage_taxPension(String wage_taxPension) {
        this.wage_taxPension = wage_taxPension;
    }

    public String getWage_taxMedical() {
        return wage_taxMedical;
    }

    public void setWage_taxMedical(String wage_taxMedical) {
        this.wage_taxMedical = wage_taxMedical;
    }

    public String getWage_taxUnemployment() {
        return wage_taxUnemployment;
    }

    public void setWage_taxUnemployment(String wage_taxUnemployment) {
        this.wage_taxUnemployment = wage_taxUnemployment;
    }

    public String getWage_taxAccumulation() {
        return wage_taxAccumulation;
    }

    public void setWage_taxAccumulation(String wage_taxAccumulation) {
        this.wage_taxAccumulation = wage_taxAccumulation;
    }

    public String getWage_taxSalary() {
        return wage_taxSalary;
    }

    public void setWage_taxSalary(String wage_taxSalary) {
        this.wage_taxSalary = wage_taxSalary;
    }

    public String getWage_taxTone() {
        return wage_taxTone;
    }

    public void setWage_taxTone(String wage_taxTone) {
        this.wage_taxTone = wage_taxTone;
    }

    public String getWage_taxElse() {
        return wage_taxElse;
    }

    public void setWage_taxElse(String wage_taxElse) {
        this.wage_taxElse = wage_taxElse;
    }

    public String getWage_total() {
        return wage_total;
    }

    public void setWage_total(String wage_total) {
        this.wage_total = wage_total;
    }

    public String getWage_date() {
        return wage_date;
    }

    public void setWage_date(String wage_date) {
        this.wage_date = wage_date;
    }

    public String getWage_update() {
        return wage_update;
    }

    public void setWage_update(String wage_update) {
        this.wage_update = wage_update;
    }
}
