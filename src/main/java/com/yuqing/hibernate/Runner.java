package com.yuqing.hibernate;

import com.yuqing.DBUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//TODO refactor this
public class Runner {
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("databases.xml");
		DBUtils.setContext(context);

		DBUtils.getSession(true);

		HibernateTransactionExamples.readUncommitted2();
//		example e = (example) context.getBean("example");
//		e.saveInTransaction();
	}
}
