package com.learn;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	private static ConfigurableApplicationContext context;

    public static Connection getConnection(boolean autoCommit, int isolationLevel) throws SQLException {
        Connection con = getConnection(autoCommit);
        con.setTransactionIsolation(isolationLevel);

        return con;
    }

    public static Connection getConnection(boolean autoCommit) throws SQLException {
        Connection con = getConnection();
        con.setAutoCommit(autoCommit);

        return con;
    }

    public static Connection getConnection() throws SQLException {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("databases.xml");
        DriverManagerDataSource datasource = (DriverManagerDataSource) context.getBean("dataSource");
        return datasource.getConnection();
    }

    public static void runSql(Connection connection, String sql) throws SQLException {
        Statement statement = connection.createStatement();

        try {
            statement.execute(sql);
        } finally {
            statement.close();
        }
    }

    public static void printRecord(Connection connection, String sql, String log) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            if (log != null) {
                System.out.println(log);
            }

            ResultSet rs = statement.executeQuery(sql); // (NOLOCK) hint downgrades the reader to read uncommitted isolation level but transactions are still in play
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("textData"));
            }

            rs.close();
        }
    }

    public static void printAllRecords(Connection connection, String log) throws SQLException {
        printRecord(connection, "select * from SavedData", log);
    }

	/**
	 * spring transaction helper methods
	 */
	public static <T> T doInTransaction(TransactionCallback<T> callback, int isoLevel, int propagation) {
		TransactionTemplate newTemplate = (TransactionTemplate) context.getBean("transactionTemplate");

		newTemplate.setPropagationBehavior(propagation);
		newTemplate.setIsolationLevel(isoLevel);

		return newTemplate.execute(callback);
	}

	/**
	 * helper method for hibernate
	 */
	public static void setContext(ConfigurableApplicationContext ctx) {
		context = ctx;
	}

	public static JdbcTemplate getJdbcTemplate() {
		return (JdbcTemplate) context.getBean("jdbcTemplate_1");
	}

	/**
	 *	Return existing session if there is. If not, return a new session.
	 */
	public static Session getSession() {
		//if do it in a tx, then don't need to call openSession(), spring tx will open session
		SessionFactory factory = (SessionFactory) context.getBean("sessionFactory_1");
		Session session = factory.getCurrentSession();

		return session;
	}
}
