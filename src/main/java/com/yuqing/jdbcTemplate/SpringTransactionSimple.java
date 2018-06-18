package com.yuqing.jdbcTemplate;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class SpringTransactionSimple {
	public static void main(String[] args) throws SQLException {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("databases.xml");
		TransactionClass transactionHolder = (TransactionClass) context.getBean("transactionHolder");

		transactionHolder.doInTransaction();
//		transactionHolder.doDirectly();
	}
}
