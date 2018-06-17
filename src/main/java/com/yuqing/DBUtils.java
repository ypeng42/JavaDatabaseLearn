package com.yuqing;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	public static Connection getConnection() {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("databases.xml");
		DriverManagerDataSource datasource = (DriverManagerDataSource) context.getBean("dataSource");
		Connection con = null;
		try {
			con = datasource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}

	public static void runSql(Connection connection, String sql) throws SQLException {
		Statement statement = connection.createStatement();

		try {
			statement.execute(sql);
		} finally {
			statement.close();
		}
	}

	public static void printAllRecords(Connection connection, String log) throws SQLException {
		Statement statement = connection.createStatement();

		try {
			ResultSet rs = statement.executeQuery("select * from SavedData");
			System.out.println(log);
			while (rs.next()) {
				System.out.println("Name: " + rs.getString("textData"));
			}

			rs.close();
		} finally {
			statement.close();
		}
	}
}
