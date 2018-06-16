package com.yuqing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcSimpleWrite {
	private static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String DB_URL = "jdbc:sqlserver://localhost:1433;database=myTest";

	//  Database credentials
	private static final String USER = "test";
	private static final String PASS = "123";

	public static void main(String[] args) throws ClassNotFoundException {
		// load jdbc driver
		Class.forName(JDBC_DRIVER);

		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement()) {

			conn.setAutoCommit(false);

			stmt.execute("insert into people (person_id, name) values (2, 'yuqing')");

			conn.commit(); // if don't call this, insert is not committed

		} catch (SQLException se) {
			se.printStackTrace();
		}
		System.out.println("\nGoodbye!");
	}
}
