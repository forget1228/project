package com.xiaoji.service.db;

public class CreateTable extends AbstractSql {

	public CreateTable() {
		initDdl();
	}

	private void initDdl() {
		ddl.add(
				"CREATE TABLE IF NOT EXISTS `bbg_wage` (\n" +
				"  `wage_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工资ID',\n" +
				"  `wage_no` varchar(50) DEFAULT NULL COMMENT '序号',\n" +
				"  `wage_dept` varchar(50) DEFAULT NULL COMMENT '部门职务',\n" +
				"  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',\n" +
				"  `wage_name` varchar(50) DEFAULT NULL COMMENT '姓名',\n" +
				"  `wage_join_dt` varchar(20) DEFAULT NULL COMMENT '当岗日期',\n" +
				"  `wage_phone` varchar(20) DEFAULT NULL COMMENT '手机号码',\n" +
				"  `wage_work_count` varchar(20) DEFAULT NULL COMMENT '出勤',\n" +
				"  `wage_money_day` varchar(20) DEFAULT NULL COMMENT '日薪',\n" +
				"  `wage_base_money` varchar(20) DEFAULT NULL COMMENT '基本工资',\n" +
				"  `wage_subsidy` varchar(20) DEFAULT NULL COMMENT '岗位补贴',\n" +
				"  `wage_benefits` varchar(20) DEFAULT NULL COMMENT '项目补助',\n" +
				"  `wage_meal` varchar(20) DEFAULT NULL COMMENT '餐补',\n" +
				"  `wage_else` varchar(20) DEFAULT NULL COMMENT '其他',\n" +
				"  `wage_leave` varchar(20) DEFAULT NULL COMMENT '请假',\n" +
				"  `wage_base_total` varchar(20) DEFAULT NULL COMMENT '小计',\n" +
				"  `wage_tax_pension` varchar(20) DEFAULT NULL COMMENT '税—养老金',\n" +
				"  `wage_tax_medical` varchar(20) DEFAULT NULL COMMENT '税—医疗',\n" +
				"  `wage_tax_unemployment` varchar(20) DEFAULT NULL COMMENT '税—失业',\n" +
				"  `wage_tax_accumulation` varchar(20) DEFAULT NULL COMMENT '税—公积金',\n" +
				"  `wage_tax_salary` varchar(20) DEFAULT NULL COMMENT '税—计税工资',\n" +
				"  `wage_tax_tone` varchar(20) DEFAULT NULL COMMENT '税—个调税',\n" +
				"  `wage_tax_else` varchar(20) DEFAULT NULL COMMENT '税—其他',\n" +
				"  `wage_total` varchar(20) DEFAULT NULL COMMENT '总计',\n" +
				"  `wage_date` varchar(20) DEFAULT NULL COMMENT '日期',\n" +
				"  `wage_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',\n" +
				"  PRIMARY KEY (`wage_id`)\n" +
				") ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8"
		);
		ddl.add(
				"CREATE TABLE IF NOT EXISTS `bbg_tax` (\n" +
				"  `tax_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '社保ID',\n" +
				"  `tax_no` varchar(50) DEFAULT NULL COMMENT '序号',\n" +
				"  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',\n" +
				"  `tax_name` varchar(50) DEFAULT NULL COMMENT '姓名',\n" +
				"  `tax_house_hold` varchar(50) DEFAULT NULL COMMENT '户口性质',\n" +
				"  `tax_min_base_payment` varchar(50) DEFAULT NULL COMMENT '最低缴费基数',\n" +
				"  `company_pension` varchar(20) DEFAULT NULL COMMENT '公司—养老金20%',\n" +
				"  `company_medical` varchar(20) DEFAULT NULL COMMENT '公司—医疗金9.5%',\n" +
				"  `company_unemployment` varchar(20) DEFAULT NULL COMMENT '公司—失业金0.5%',\n" +
				"  `company_fertility` varchar(20) DEFAULT NULL COMMENT '公司—生育1%',\n" +
				"  `company_injury` varchar(20) DEFAULT NULL COMMENT '公司—工伤0.1%',\n" +
				"  `company_total` varchar(20) DEFAULT NULL COMMENT '公司—合计32.2%',\n" +
				"  `personal_pension` varchar(20) DEFAULT NULL COMMENT '个人—养老金8%',\n" +
				"  `personal_medical` varchar(20) DEFAULT NULL COMMENT '个人—医疗金2%',\n" +
				"  `personal_unemployment` varchar(20) DEFAULT NULL COMMENT '个人—失业金0.5%',\n" +
				"  `personal_toatl` varchar(20) DEFAULT NULL COMMENT '个人—合计10.5%',\n" +
				"  `tax_total` varchar(20) DEFAULT NULL COMMENT '总计42.7%',\n" +
				"  `tax_payment` varchar(20) DEFAULT NULL COMMENT '补缴',\n" +
				"  `tax_remark` varchar(150) DEFAULT NULL COMMENT '备注',\n" +
				"  `tax_date` varchar(20) DEFAULT NULL COMMENT '日期',\n" +
				"  `tax_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',\n" +
				"  PRIMARY KEY (`tax_id`)\n" +
				") ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8"
		);
	}
}
