package com.xiaoji.model;

public class BbgTax {
    private String tax_no;                  // 序号
    private String tax_name;                // 姓名
    private String tax_household;          // 户口性质
    private String tax_minBasePayment;    // 最低缴费基数
    private String company_pension;        // 公司—养老金20%
    private String company_medical;        // 公司—医疗金9.5%
    private String company_unemployment;  // 公司—失业金0.5%
    private String company_fertility;      // 公司—生育1%
    private String company_injury;         // 公司—工伤0.1%
    private String company_total;          // 公司—合计32.2%
    private String personal_pension;       // 个人—养老金8%
    private String personal_medical;       // 个人—医疗金2%
    private String personal_unemployment; // 个人—失业金0.5%
    private String personal_toatl;         // 个人—合计10.5%
    private String tax_total;               // 总计42.7%
    private String tax_payment;             // 补缴
    private String tax_remark;              // 备注
    private String tax_date;                // 日期
    private String tax_update;              // 修改日期

    @Override
    public String toString() {
        return "BbgTax{" +
                "tax_no='" + tax_no + '\'' +
                ", tax_name='" + tax_name + '\'' +
                ", tax_household='" + tax_household + '\'' +
                ", tax_minBasePayment='" + tax_minBasePayment + '\'' +
                ", company_pension='" + company_pension + '\'' +
                ", company_medical='" + company_medical + '\'' +
                ", company_unemployment='" + company_unemployment + '\'' +
                ", company_fertility='" + company_fertility + '\'' +
                ", company_injury='" + company_injury + '\'' +
                ", company_total='" + company_total + '\'' +
                ", personal_pension='" + personal_pension + '\'' +
                ", personal_medical='" + personal_medical + '\'' +
                ", personal_unemployment='" + personal_unemployment + '\'' +
                ", personal_toatl='" + personal_toatl + '\'' +
                ", tax_total='" + tax_total + '\'' +
                ", tax_payment='" + tax_payment + '\'' +
                ", tax_remark='" + tax_remark + '\'' +
                '}';
    }

    public String getTax_no() {
        return tax_no;
    }

    public void setTax_no(String tax_no) {
        this.tax_no = tax_no;
    }

    public String getTax_name() {
        return tax_name;
    }

    public void setTax_name(String tax_name) {
        this.tax_name = tax_name;
    }

    public String getTax_household() {
        return tax_household;
    }

    public void setTax_household(String tax_household) {
        this.tax_household = tax_household;
    }

    public String getTax_minBasePayment() {
        return tax_minBasePayment;
    }

    public void setTax_minBasePayment(String tax_minBasePayment) {
        this.tax_minBasePayment = tax_minBasePayment;
    }

    public String getCompany_pension() {
        return company_pension;
    }

    public void setCompany_pension(String company_pension) {
        this.company_pension = company_pension;
    }

    public String getCompany_medical() {
        return company_medical;
    }

    public void setCompany_medical(String company_medical) {
        this.company_medical = company_medical;
    }

    public String getCompany_unemployment() {
        return company_unemployment;
    }

    public void setCompany_unemployment(String company_unemployment) {
        this.company_unemployment = company_unemployment;
    }

    public String getCompany_fertility() {
        return company_fertility;
    }

    public void setCompany_fertility(String company_fertility) {
        this.company_fertility = company_fertility;
    }

    public String getCompany_injury() {
        return company_injury;
    }

    public void setCompany_injury(String company_injury) {
        this.company_injury = company_injury;
    }

    public String getCompany_total() {
        return company_total;
    }

    public void setCompany_total(String company_total) {
        this.company_total = company_total;
    }

    public String getPersonal_pension() {
        return personal_pension;
    }

    public void setPersonal_pension(String personal_pension) {
        this.personal_pension = personal_pension;
    }

    public String getPersonal_medical() {
        return personal_medical;
    }

    public void setPersonal_medical(String personal_medical) {
        this.personal_medical = personal_medical;
    }

    public String getPersonal_unemployment() {
        return personal_unemployment;
    }

    public void setPersonal_unemployment(String personal_unemployment) {
        this.personal_unemployment = personal_unemployment;
    }

    public String getPersonal_toatl() {
        return personal_toatl;
    }

    public void setPersonal_toatl(String personal_toatl) {
        this.personal_toatl = personal_toatl;
    }

    public String getTax_total() {
        return tax_total;
    }

    public void setTax_total(String tax_total) {
        this.tax_total = tax_total;
    }

    public String getTax_payment() {
        return tax_payment;
    }

    public void setTax_payment(String tax_payment) {
        this.tax_payment = tax_payment;
    }

    public String getTax_remark() {
        return tax_remark;
    }

    public void setTax_remark(String tax_remark) {
        this.tax_remark = tax_remark;
    }

    public String getTax_date() {
        return tax_date;
    }

    public void setTax_date(String tax_date) {
        this.tax_date = tax_date;
    }

    public String getTax_update() {
        return tax_update;
    }

    public void setTax_update(String tax_update) {
        this.tax_update = tax_update;
    }
}
