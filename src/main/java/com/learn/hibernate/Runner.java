package com.learn.hibernate;

import com.learn.DBUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Runner {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("databases.xml");
		DBUtils.setContext(context);

		HibernateTransactionExamples.demo();
	}
}
