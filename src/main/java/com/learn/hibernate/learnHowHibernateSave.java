package com.learn.hibernate;

import com.learn.hibernate.mapping.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.event.internal.DefaultSaveEventListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class learnHowHibernateSave {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("databases.xml");
		SessionFactory factory = (SessionFactory) context.getBean("sessionFactory_1");
		Session session = factory.openSession();
		Student student = new Student("yuqing1");

		// this will set autocommit to false
		// AbstractLogicalConnectionImplementor.getConnectionForTransactionManagement().setAutoCommit( false );
//		Transaction transaction = session.beginTransaction();

		// Everything happens in this single line
		session.save(student);


		/**
		 * 1. session.save() -> fires event {@link org.hibernate.internal.SessionImpl#fireSave}, there is only 1 listeners (DefaultSaveEventListener)
		 * 2. do a bunch of things and then -> {@link DefaultSaveEventListener#performSaveOrUpdate}
		 *
		 * 3. {@link DefaultSaveEventListener#entityIsTransient} -> save with generated id bla bla bla ->
		 * 4. {@link org.hibernate.event.internal.AbstractSaveEventListener#performSave}
		 *
		 * 5. {@link org.hibernate.event.internal.AbstractSaveEventListener#addInsertAction} to ActionQueue, right after adding to queue, it will be executed
		 *
		 * 6. callback -> {@link org.hibernate.action.internal.EntityIdentityInsertAction#execute}
		 *
		 * 7. actually does the work -> {@link org.hibernate.persister.entity.AbstractEntityPersister#insert}, the sql is ALREADY HERE!!!
		 * a.k.a sqlIdentityInsertString
		 *
		 * 8. During {@link org.hibernate.persister.entity.AbstractEntityPersister#doLateInit}, the insert sql is already set up in {@link org.hibernate.persister.entity.AbstractEntityPersister#generateIdentityInsertString}
		 */

		System.out.println("done!");
	}
}
