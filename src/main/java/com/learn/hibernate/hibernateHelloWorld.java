package com.learn.hibernate;

import com.learn.hibernate.mapping.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class hibernateHelloWorld {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("databases.xml");

		SessionFactory factory = (SessionFactory) context.getBean("sessionFactory_1");
		Session session = factory.openSession();

		Student student = new Student("yuqing1");

		// this will set autocommit to false
		// AbstractLogicalConnectionImplementor.getConnectionForTransactionManagement().setAutoCommit( false );
//		Transaction transaction = session.beginTransaction();
		session.save(student);
		/**
		 * {@link org.hibernate.event.internal.DefaultSaveEventListener#performSaveOrUpdate}
		 *
		 * it is a AbstractEntityInsertAction EarlyInsert
		 *
		 * {@link org.hibernate.action.internal.EntityIdentityInsertAction#execute}
		 * -> {@link org.hibernate.persister.entity.AbstractEntityPersister#insert}
		 * -> TODO who sets sqlIdentityInsertString? in the persister???
		 *
		 */

		System.out.println("done!");
	}
}
