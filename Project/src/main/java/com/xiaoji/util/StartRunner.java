package com.xiaoji.util;

import com.xiaoji.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value=1)
public class StartRunner implements ApplicationRunner {

	@Autowired
	private DatabaseService databaseService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Database init ...");
		this.databaseService.initDatabase();
	}

}
