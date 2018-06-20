package com.yuqing.hibernate;

import com.yuqing.DBUtils;
import com.yuqing.hibernate.mapping.Student;
import org.hibernate.Session;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class hibernateHelloWorld {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("databases.xml");
		DBUtils.setContext(context);

		Session session = DBUtils.getSession(true);
		// get connection ((SessionImpl)getSession()).connection()

		Student student = new Student();
		student.setName("yuqing1");

		session.save(student);
		System.out.println("done!");
	}
}
