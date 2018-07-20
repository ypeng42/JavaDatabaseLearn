package com.learn.hibernate;

import com.learn.DBUtils;
import com.learn.hibernate.mapping.Student;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class HibernateTransactionExamples {
	/**
	 * How tx manager, spring, hibernate session works together:
	 *
	 * 1.
	 * LocalSessionFactoryBean is used here, when building a SessionFactory, {@link LocalSessionFactoryBuilder} will set CURRENT_SESSION_CONTEXT_CLASS to SpringSessionContext
	 * so when we call factory.getCurrentSession() the call will be delegated to {@link SpringSessionContext#currentSession}
	 *
	 * For more info: http://docs.jboss.org/hibernate/orm/5.3/userguide/html_single/Hibernate_User_Guide.html#transactions
	 *
	 * 2.
	 * if use jdbcTemplate or plain jdbc call
	 * Need to get connection through {@link DataSourceUtils#getConnection} (jdbcTemplate calls this internally)
	 * the connection comes from connection of the session of the current thread, so even if there is only jdbc call, hibernate's tx call will affect plain jdbc, since they share the same connection
	 *
	 * 3.
	 * 1 session (connection) within the same propagation level, when a new level starts, new session is created.
	 *
	 * session is bind to session factory
	 * connection is bind to datasource
	 *
	 */
	public static void newTransactionNested() {
		JdbcTemplate jdbcTemplate = DBUtils.getJdbcTemplate();

		DBUtils.doInTransaction(status -> {
			Session session = DBUtils.getSession();
			session.save(new Student("test student"));

			try {
				DBUtils.doInTransaction(s -> {
					Session session2 = DBUtils.getSession();
					session2.save(new Student("yuqing2"));
					jdbcTemplate.execute("insert into Student (name, age) values ('jdbc test', 22)");
					return null;
				}, TransactionDefinition.ISOLATION_READ_COMMITTED, TransactionDefinition.PROPAGATION_REQUIRES_NEW);
			} catch	(Exception e) {}

			return null;
		}, TransactionDefinition.ISOLATION_READ_COMMITTED, TransactionDefinition.PROPAGATION_REQUIRED);

		System.out.println("done!");
	}
}
