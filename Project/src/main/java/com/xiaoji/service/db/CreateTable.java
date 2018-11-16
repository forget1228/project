package com.xiaoji.service.db;

public class CreateTable extends AbstractSql {

	public CreateTable() {
		//initDdl();
	}

	private void initDdl() {
		ddl.add(
				"CREATE TABLE IF NOT EXISTS `abk_recording` (\n" +
				"  `abk_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',\n" +
				"  `template_id` varchar(20) DEFAULT NULL COMMENT 'templateID',\n" +
				"  `template_name` varchar(50) DEFAULT NULL COMMENT 'template名',\n" +
				"  `template_generator` varchar(50) DEFAULT NULL,\n" +
				"  `template_file` varchar(150) DEFAULT NULL COMMENT 'template文件地址',\n" +
				"  `sa_prefix` varchar(50) DEFAULT NULL COMMENT '短应用程序码',\n" +
				"  `group_name` varchar(50) DEFAULT NULL COMMENT '短应用程序名',\n" +
				"  `class_name` varchar(50) DEFAULT NULL COMMENT '短应用程序名',\n" +
				"  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入库日期',\n" +
				"  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',\n" +
				"  PRIMARY KEY (`abk_id`)\n" +
				") ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8"
		);
	}
}
