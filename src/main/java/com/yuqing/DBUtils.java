package com.yuqing;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
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

			ResultSet rs = statement.executeQuery(sql); // with(nolock) will allow dirty read
			while (rs.next()) {
				System.out.println("Name: " + rs.getString("textData"));
			}

			rs.close();
		}
	}

	public static void printAllRecords(Connection connection, String log) throws SQLException {
		printRecord(connection, "select * from SavedData", log);
	}
}
