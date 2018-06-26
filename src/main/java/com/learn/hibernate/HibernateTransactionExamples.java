package com.learn.hibernate;

import com.learn.DBUtils;
import com.learn.hibernate.mapping.Student;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.transaction.TransactionDefinition;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class HibernateTransactionExamples {
	/**
	 * Read hibernate doc "Transaction and Concurrency", how to get session can be plugable
	 * http://docs.jboss.org/hibernate/orm/5.3/userguide/html_single/Hibernate_User_Guide.html#transactions
	 *
	 * SpringSessionContext is responsible to return the current hibernate session. That's how spring can manage
	 * hibernate transaction.
	 *
	 * Read {@link SpringSessionContext#currentSession}:
	 * You will get a SessionHolder that is bind to the session factory, which will give you a hiberate session. This
	 * hibernate session is already made ready by spring's transaction manager.
	 *
	 */
	public static void newTransactionNested() {
		// Create
		Session sessionIni = DBUtils.getSession(true);

		// a new session will be opened for each transaction???? obtainSessionFactory().openSession() hibernate Tx manager line 456
		// txObject.setSession will wrap the session in a session holder

		// tx sync manager To be used by resource management code but not by typical application code!!

		//look for how hibernate tx manager call TransactionSynchronizationManager.bindResource

		// do in new session will lead to hibernate tx manager call doBegin() which will open a new hibernate session

		// same thing how jdbctemplate gets its datasource, from tx sync manager
		DBUtils.doInTransaction(status -> {
			//TODO read SpringSessionContext, key to how session is created etc. It calls TransactionSynchronizationManager.getResource(this.sessionFactory);!
			Session session = DBUtils.getSession(false);
			// Spring TX should set auto commit to false, but since we create session, auto commit is true here.
			session.save(new Student("test student"));

			// is there a new connection created for new transaction?? this shouldn't work!
			// a new connection is created and the previous connection haven't commit
			try {
				DBUtils.doInTransaction(s -> {
					Session session2 = DBUtils.getSession(false);
					session2.save(new Student("yuqing2"));
					throw new RuntimeException("asdasd");
				}, TransactionDefinition.ISOLATION_READ_COMMITTED, TransactionDefinition.PROPAGATION_REQUIRES_NEW);
			} catch	(Exception e) {

			}

			return null;
		}, TransactionDefinition.ISOLATION_READ_COMMITTED, TransactionDefinition.PROPAGATION_REQUIRED);


		System.out.println("done!");
	}

	public static void readUncommitted2() {
		Session session = DBUtils.getSession(true);

		Student student = new Student("yuqing");
		student.setAge(20);

		DBUtils.doInTransaction(status -> {

			session.save(student);

			// is there a new connection created for new transaction?? this shouldn't work!
			// a new connection is created and the previous connection haven't commit
			DBUtils.doInTransaction(s -> {

				Student s2 = readStudentByName(session, "yuqing");
				if (s2 != null) {
					System.out.println(s2.getName());
				} else {
					System.out.println("student doesn't exist!");
				}
				return null;
			}, TransactionDefinition.ISOLATION_READ_COMMITTED, TransactionDefinition.PROPAGATION_REQUIRES_NEW);

			return null;
		}, TransactionDefinition.ISOLATION_READ_COMMITTED, TransactionDefinition.PROPAGATION_REQUIRED);


		System.out.println("done!");
	}

	private static Student readStudentByName(Session session, String name) {
		CriteriaBuilder builder = session.getCriteriaBuilder();

		CriteriaQuery<Student> query = builder.createQuery(Student.class);
		Root<Student> root = query.from(Student.class);
		query.select(root);
		query.where(builder.equal(root.get("name"), name));

		return session.createQuery(query).uniqueResult();
	}
}
