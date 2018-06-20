package com.yuqing.hibernate;

import com.yuqing.DBUtils;
import com.yuqing.hibernate.mapping.Student;
import org.hibernate.Session;
import org.springframework.transaction.TransactionDefinition;

public class HibernateTransactionExamples {
	public static void readUncommitted() {

		Session session = DBUtils.getSession(true);

		Student student = new Student();
		student.setName("yuqing1");

		// this will set autocommit to false
		// AbstractLogicalConnectionImplementor.getConnectionForTransactionManagement().setAutoCommit( false );
		DBUtils.doInTransaction(status -> {

			session.save(student);

			Student s = session.get(Student.class, 1l);
			if (s != null) {
				System.out.println(s.getName());
			} else {
				System.out.println("student doesn't exist!");
			}

			return null;
		}, TransactionDefinition.ISOLATION_READ_COMMITTED, TransactionDefinition.PROPAGATION_REQUIRED);


		System.out.println("done!");
	}

	public static void readUncommitted2() {
		Session session = DBUtils.getSession(true);

		Student student = new Student();
		student.setName("yuqing4");

		DBUtils.doInTransaction(status -> {

			session.save(student);

			// is there a new connection created for new transaction?? this shouldn't work!
			// a new connection is created and the previous connection haven't commit
			DBUtils.doInTransaction(s -> {

				Student s2 = session.get(Student.class, 1l);
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
}
