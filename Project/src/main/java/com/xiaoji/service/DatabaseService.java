package com.xiaoji.service;

import com.xiaoji.dao.DatabaseMapper;
import com.xiaoji.service.db.AbstractSql;
import com.xiaoji.service.db.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DatabaseService {

	@Autowired
	private DatabaseMapper databaseMapper;
	
	public void initDatabase() {
		AbstractSql sql = new CreateTable();
		execute(sql.getDdl());
	}
	
	@Transactional
	public void execute(List<String> sqls) {
		for (String sql : sqls) {
			this.databaseMapper.execute(sql);
		}
	}
}
