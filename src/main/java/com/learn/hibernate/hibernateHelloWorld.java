package com.learn.hibernate;

import com.learn.DBUtils;
import com.learn.hibernate.mapping.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class hibernateHelloWorld {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("databases.xml");
		DBUtils.setContext(context);

		Session session = DBUtils.getSession(true);
		// get connection ((SessionImpl)getSession()).connection()

		Student student = new Student("yuqing1");

		// this will set autocommit to false
		// AbstractLogicalConnectionImplementor.getConnectionForTransactionManagement().setAutoCommit( false );
		Transaction transaction = session.beginTransaction();
		session.save(student);

		try {
			session.createNativeQuery("alter table Student alter column name varchar(2)").executeUpdate(); //this will rollback everything!
		} catch(Exception e) {
			System.out.println("error happens!");
		}
		transaction.commit();

		System.out.println("done!");
	}
}
