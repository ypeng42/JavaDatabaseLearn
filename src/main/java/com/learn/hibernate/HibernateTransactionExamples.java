package com.learn.hibernate;

import com.learn.DBUtils;
import com.learn.hibernate.mapping.Student;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.transaction.TransactionDefinition;

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
	 * TODO. single tx single jdbctemplate
	 * TODO. single tx single hibernate
	 * TODO. single tx hibernate + jdbc
	 * TODO. nested tx (no new) -> show no new session created
	 * TODO. nested tx (require new) -> show steps new session created, current session suspended
	 * {@link org.springframework.transaction.support.AbstractPlatformTransactionManager#cleanupAfterCompletion} resume
	 *
	 */
	public static void demo() {
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

			Session session2 = DBUtils.getSession();
			return null;
		}, TransactionDefinition.ISOLATION_READ_COMMITTED, TransactionDefinition.PROPAGATION_REQUIRED);

		System.out.println("done!");
	}
}
